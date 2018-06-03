package work3.Controller;

import work3.Model.Field;
import work3.View.GameOver;

import java.awt.*;
import java.io.IOException;

import static work3.View.Game.gameField;
import static work3.View.Game.gameWindow;
import static work3.View.Game.infoBoard;
import static work3.View.Main.game;

public class Controller {
    private static Field field;


    public Controller() {
        field = new Field();
    }

    public void nextTurn(int i, int j) {

        field.setCellValue(i, j, field.player);

        field.updateField(i, j);

        changePlayer();

        game.updateScene();

        if (field.IsFull()) {
            try {
                gameWindow.remove(gameField);
                gameWindow.remove(infoBoard);
                GameOver gameOver = new GameOver();
                gameWindow.add(gameOver, BorderLayout.CENTER);

                gameWindow.setVisible(true);
            } catch (IOException e1) {
                e1.printStackTrace();
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
                    if (this.canDoTurn(i, j)) return false;
                }
            }
        }
        return true;
    }

    /**
     * Метод, определяющий, можно ли сделать ход по заданным координатам нажатия мыши.
     * Возвращает true, если ход возможен, и false, если ход невозможен.
     */
    public boolean canDoTurn(int i, int j) {

        if (field.getField()[j][i] != 0) {
            return false;
        } else {
            int[][] matrix = field.getField();

            field.setCellValue(i, j, field.player);

            Field field0 = new Field();
            int[][] matrix0 = new int[8][8];

            for (int i1 = 0; i1 < 8; i1++) {
                System.arraycopy(matrix[i1], 0, matrix0[i1], 0, 8);
            }

            field0.setField(matrix0);

            field0.updateField(i, j);

            if (field0.equals(field)) {
                field.setCellValue(i, j, 0);
                return false;
            } else {
                field.setCellValue(i, j, 0);
                return true;
            }
        }
    }

    /**
     * Метод, подсчитывающий количество очков каждого игрока.
     */
    public int getPlayerScore(int player) {
        int black = 0;
        int white = 0;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (field.getField()[i][j] == 1) {
                    white++;
                } else {
                    if (field.getField()[i][j] == -1) {
                        black++;
                    }
                }
            }
        }
        if (player == -1) return black;
        else return white;
    }

    public static Field getField() {
        return field;
    }

    public int getPlayer() {
        return field.player;
    }

    public static void changePlayer() {
        field.player *= -1;
    }
}
