package work3.View;

import work3.Controller.Controller;

import javax.swing.*;
import java.io.IOException;

public class Main {
    public static Controller controller;
    public static Game game;

    public Main() throws IOException{
        controller = new Controller();
        game = new Game();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                try {
                    new Main();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
