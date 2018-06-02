package work3;

import javafx.scene.layout.Border;

import java.awt.*;

import static work3.GameWindow.*;
import static work3.Reversy.field;
import static work3.Reversy.player;
import static work3.Reversy.turn;
import static work3.Reversy.endGame;

public class Turn {

    /**
     * Метод, определяющий, можно ли сделать ход по заданным координатам нажатия мыши.
     * Возвращает true, если ход возможен, и false, если ход невозможен.
     */
    public boolean canDoTurn(int i, int j) {

        if (field.getField()[j][i] != 0) {
            return false;
        } else {
            int[][] matrix = field.getField();
            int[][] fieldBeforeTurn = new int[8][8];

            for (int i1 = 0; i1 < 8; i1++) {
                System.arraycopy(matrix[i1], 0, fieldBeforeTurn[i1], 0, 8);
            }

            field.setCellValue(j, i, player);

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

    /**
     * Метод, идентифицирующий патовую ситуацию.
     */
    public boolean isPat() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (field.getField()[i][j] == 0) {
                    if (turn.canDoTurn(i, j)) return false;
                }
            }
        }
        return true;
    }

    /**
     * Метод, который реализует ход игрока по заданным координатам нажатия мыши (в клетках игрового поля)
     */
    public void nextTurn(int i, int j) {

        field.setCellValue(j, i, player);

        field.updateField(i, j);

        gameField.repaint();

        player *= -1;


        if (field.IsFull()) {
            endGame();
        }
    }
}
