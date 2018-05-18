package work3;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import static work3.GameWindow.gameField;
import static work3.GameWindow.gameWindow;
import static work3.Reversy.field;
import static work3.Reversy.player;
import static work3.Reversy.turn;

public class Turn {

    /**
     * Метод, определяющий, можно ли сделать ход по заданным координатам нажатия мыши.
     * Возвращает true, если ход возможен, и false, если ход невозможен.
     */
    public boolean canDoTurn(int x, int y) {
        /*
         * Начальное положени поля
         */
        final int x0 = 242;
        final int y0 = 60;

        if ((x < x0) || (y < y0) || (x > x0 + GameWindow.field.getWidth(null))
                || (y > y0 + GameWindow.field.getHeight(null))) return false;
        else {

        /*
        Определяем, каким строке и столбцу соответсвтует нажатие
        65 - развер одной клетки поля в пикселях
         */
            int i = (x - x0) / 65;
            int j = (y - y0) / 65;

            if (field.getField()[j][i] != 0) {
                return false;
            } else {
                int[][] matrix = field.getField();
                int[][] fieldBeforeTurn = new int[8][8];

                for (int i1 = 0; i1 < 8; i1++) {
                    System.arraycopy(matrix[i1], 0, fieldBeforeTurn[i1], 0, 8);
                }

                field.setCellValue(i, j, player);

                Field field0 = new Field();
                int[][] matrix0 = new int[8][8];

                for (int i1 = 0; i1 < 8; i1++) {
                    System.arraycopy(matrix[i1], 0, matrix0[i1], 0, 8);
                }

                field0.setField(matrix0);

                field.updateField(i, j);

                if (field0.equals(field)) {
                    field.setField(fieldBeforeTurn);
                    return false;
                } else {
                    field.setField(fieldBeforeTurn);
                    return true;
                }
            }
        }
    }

    /**
     * Метод, идентифицирующий патовую ситуацию.
     */
    private boolean isPat() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (turn.canDoTurn(265 + 65 * i,
                        82 + 65 * j)) return false;
            }
        }
        return true;
    }

    /**
     * Метод, который реализует ход игрока по заданным координатам нажатия мыши (в клетках игрового поля)
     */
    public void nextTurn(int x, int y) {
        /*
         * Начальное положени поля
         */
        final int x0 = 265;
        final int y0 = 82;


        /*
        Определяем, каким строке и столбцу соответсвтует нажатие
        65 - развер одной клетки поля в пикселях
         */
        int i = (x - x0) / 65;
        int j = (y - y0) / 65;

        field.setCellValue(i, j, player);

        field.updateField(i, j);

        gameWindow.remove(gameField);
        gameField = new GameWindow.GameField();
        gameWindow.add(gameField);
        gameField.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (turn.canDoTurn(e.getX(), e.getY())) {
                    turn.nextTurn(e.getX(), e.getY());
                } else if (turn.isPat()) {
                    player *= -1;
                    if (turn.isPat()) {
                        System.out.println(field.identifyWinner());
                        System.exit(0);
                    }
                }
            }
        });

        gameWindow.add(gameField);
        gameWindow.setVisible(true);

        player *= -1;

        if (field.IsFull()) {
            System.out.println(field.identifyWinner());
            System.exit(0);
        }
    }
}
