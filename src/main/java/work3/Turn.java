package work3;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import static work3.GameWindow.*;
import static work3.Reversy.field;
import static work3.Reversy.player;
import static work3.Reversy.turn;
import static work3.Reversy.winner;
import static work3.Reversy.endGame;

public class Turn {

    /**
     * Метод, определяющий, можно ли сделать ход по заданным координатам нажатия мыши.
     * Возвращает true, если ход возможен, и false, если ход невозможен.
     */
    public boolean canDoTurn(int x, int y) {

        if ((x < field_X) || (y < field_Y) || (x > field_X + GameWindow.field.getWidth(null))
                || (y > field_Y + GameWindow.field.getHeight(null))) return false;
        else {

            int i = (x - field_X) / chip_Size;
            int j = (y - field_Y) / chip_Size;

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
                if (field.getField()[i][j] == 0) {
                    if (turn.canDoTurn(field_X + chip_Size * i,
                            field_Y + chip_Size * j)) return false;
                }
            }
        }
        return true;
    }

    /**
     * Метод, который реализует ход игрока по заданным координатам нажатия мыши (в клетках игрового поля)
     */
    public void nextTurn(int x, int y) {

        int i = (x - field_X) / chip_Size;
        int j = (y - field_Y) / chip_Size;

        field.setCellValue(i, j, player);

        field.updateField(i, j);

        gameWindow.remove(gameField);
        gameField = new GameWindow.GameField();
        gameField.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (turn.canDoTurn(e.getX(), e.getY())) {
                    turn.nextTurn(e.getX(), e.getY());
                } else if (turn.isPat()) {
                    player *= -1;
                    if (turn.isPat()) {
                        endGame();
                    }
                }
            }
        });

        gameWindow.add(gameField);
        gameWindow.setVisible(true);

        player *= -1;

        if (field.IsFull()) {
            endGame();
        }
    }
}
