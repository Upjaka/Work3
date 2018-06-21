package work3.View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

public class Game {
    public JFrame gameWindow;
    public GameField gameField;
    private InfoBoard infoBoard;

    private Timer timer;

    public Game() throws IOException {
        final int fieldX = 140;
        final int fieldY = 85;
        final int chipSize = 64;
        final int windowHeight = 720;
        final int windowWidth = 1024;
        final int fieldSize = 521;

        gameWindow = new JFrame("Revercy -- Java");
        gameWindow.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        gameWindow.setLocation(200, 100);
        gameWindow.setSize(windowWidth, windowHeight);
        gameWindow.setResizable(false);

        gameField = new GameField();

        gameField.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (!(e.getX() < fieldX) && !(e.getY() < fieldY) &&
                        !(e.getX() > fieldX + fieldSize) &&
                        !(e.getY() > fieldY + fieldSize)) {
                    int i = (e.getX() - fieldX) / chipSize;
                    int j = (e.getY() - fieldY) / chipSize;
                    if (Main.getController().getField().canDoTurn(i, j)) {
                        Main.getController().nextTurn(i, j);
                    } else if (Main.getController().getField().isPat()) {
                        Main.getController().changePlayer();
                        infoBoard.updateBoards();
                        if (Main.getController().getField().isPat()) {
                            timer = new Timer(500, new ActionListener() {
                                public void actionPerformed(ActionEvent e) {
                                    try {
                                        GameOver gameOver = new GameOver();
                                        gameWindow.remove(gameField);
                                        gameWindow.add(gameOver, BorderLayout.CENTER);
                                    } catch (IOException e1) {
                                        e1.printStackTrace();
                                    }
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
