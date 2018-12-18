package work3.Controller;

import work3.Model.Field;
import work3.View.GameOver;
import work3.View.Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class Controller {
    private Field field;

    private Timer timer;

    /**
     * Конструктор класса
     */
    public Controller() {
        field = new Field();
    }

    /**
     * Метод, совершающий ход.
     */
    public void nextTurn(int i, int j) {
        field.nextTurn(i, j);

        changePlayer();

        Main.getGame().updateScene();

        if (field.isFull()) {
            timer = new Timer(500, new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    try {
                        Main.getGame().gameWindow.remove(Main.getGame().gameField);
                        GameOver gameOver = new GameOver();
                        Main.getGame().gameWindow.add(gameOver, BorderLayout.CENTER);

                        Main.getGame().gameWindow.setVisible(true);
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                    timer.stop();
                }
            });
            timer.start();
        }
    }

    public Field getField() {
        return field;
    }

    public int getPlayer() {
        return field.getPlayer();
    }

    public void changePlayer() {
        field.setPlayer(field.getPlayer() * -1);
    }

    /**
     * Метод, подсчитывающий количество очков каждого игрока.
     */
}
