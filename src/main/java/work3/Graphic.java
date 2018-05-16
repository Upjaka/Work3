package work3;

import javax.swing.*;
import java.awt.*;

public class Graphic {

    public static class GameField extends JPanel {

        private int[][] field;

        public GameField() {
            int[][] matrix = new int[8][8];
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    matrix[i][j] = 0;
                }
            }
            matrix[4][5] = 2;
            matrix[5][4] = 2;
            matrix[4][4] = 1;
            matrix[5][5] = 1;
            this.field = matrix;
        }

        public static void paintGameField(Graphics g) {
            g.fillRect(212, 60, 600, 600);

        }

        @Override
        protected void paintComponent(java.awt.Graphics g) {
            super.paintComponent(g);
            GameField.paintGameField(g);
        }

    }

    public void openGameWindow() {
        JFrame gameWindow = new JFrame("Revercy");
        gameWindow.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        gameWindow.setLocation(200, 100);
        gameWindow.setSize(1024, 720);
        gameWindow.setResizable(false);
        GameField gameField = new GameField();
        gameWindow.add(gameField);
        gameWindow.setVisible(true);
    }
}