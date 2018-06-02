package work3.View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

import static work3.View.GameField.fieldImage;
import static work3.View.Main.controller;

public class Game {
    public static JFrame gameWindow;
    public static GameField gameField;
    public static InfoBoard infoBoard;

    private final int field_X = 140;
    private final int field_Y = 85;
    private final int chip_Size = 64;
    private final int field_Size = 521;
    private final int frame_Size = 628;
    private final int window_Width = 1024;
    private final int window_Height = 720;

    public Game() throws IOException {

        gameWindow = new JFrame("Revercy -- Java");
        gameWindow.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        gameWindow.setLocation(200, 100);
        gameWindow.setSize(window_Width, window_Height);
        gameWindow.setResizable(false);

        gameField = new GameField();

        gameField.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (!(e.getX() < field_X) && !(e.getY() < field_Y) &&
                        !(e.getX() > field_X + fieldImage.getWidth(null)) &&
                        !(e.getY() > field_Y + fieldImage.getHeight(null))) {
                    int i = (e.getX() - field_X) / chip_Size;
                    int j = (e.getY() - field_Y) / chip_Size;
                    if (controller.canDoTurn(i, j)) {
                        controller.nextTurn(i, j);
                    } else if (controller.isPat()) {
                        controller.setPlayer(controller.getPlayer() * -1);
                        if (controller.isPat()) {
                            try {
                                GameOver gameOver = new GameOver();
                                gameWindow.remove(gameField);
                                gameWindow.remove(infoBoard);
                                gameWindow.add(gameOver, BorderLayout.CENTER);
                            } catch (IOException e1) {
                                e1.printStackTrace();
                            }
                        }
                    }
                }
            }
        });

        gameWindow.add(gameField, BorderLayout.CENTER);

        infoBoard = new InfoBoard();
        gameWindow.add(infoBoard, BorderLayout.WEST);

        gameWindow.setVisible(true);
    }

    public void updateScene() {
        gameField.repaint();
        infoBoard.updateBoards();
    }
}
