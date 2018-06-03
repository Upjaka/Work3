package work3.View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

import static work3.View.GameField.fieldImage;
import static work3.View.Main.controller;

public class Game {
    public JFrame gameWindow;
    public GameField gameField;
    public InfoBoard infoBoard;
    public GameOver gameOver;

    public Timer timer;

    private final int field_X = 140;
    private final int field_Y = 85;
    private final int chip_Size = 64;
    private final int field_Size = 521;
    private final int frame_Size = 628;

    public Game() throws IOException {
        final int window_Height = 720;
        final int window_Width = 1024;

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
                       infoBoard.updateBoards();
                        timer = new Timer(500, new ActionListener() {
                            public void actionPerformed(ActionEvent e) {
                                controller.changePlayer();
                                infoBoard.updateBoards();
                                timer.stop();
                            }
                        });
                        if (controller.isPat()) {
                            timer = new Timer(500, new ActionListener() {
                                public void actionPerformed(ActionEvent e) {
                                    try {
                                        gameOver = new GameOver();
                                    } catch (IOException e1) {
                                        e1.printStackTrace();
                                    }
                                    gameWindow.remove(gameField);
                                    gameWindow.add(gameOver, BorderLayout.CENTER);

                                    gameWindow.setVisible(true);
                                    timer.stop();
                                }
                            });
                            timer.start();
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
