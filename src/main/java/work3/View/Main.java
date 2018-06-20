package work3.View;

import work3.Controller.Controller;

import javax.swing.*;
import java.io.IOException;

public class Main {
    private static Controller controller;
    private static Game game;

    private Main() throws IOException {
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

    public static Controller getController() {
        return controller;
    }

    public static Game getGame() {
        return game;
    }

    public static void setController(Controller controller) {
        Main.controller = controller;
    }

    public static void setGame(Game game) {
        Main.game = game;
    }
}
