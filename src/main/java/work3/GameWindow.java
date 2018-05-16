package work3;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static javax.imageio.ImageIO.read;

public class GameWindow extends JFrame {

    private static GameWindow gameWindow;
    private static Image field;


    public static void openGameWindow() throws IOException {
        BufferedImage bufferedImage = ImageIO.read(new File("src\\main\\resources\\field.jpg"));
        field = bufferedImage.getScaledInstance(600, 600, 1);
        gameWindow = new GameWindow();
        gameWindow.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        gameWindow.setLocation(200, 100);
        gameWindow.setSize(1024, 720);
        gameWindow.setResizable(false);
        GameField field = new GameField();
        gameWindow.add(field);
        gameWindow.setVisible(true);
    }

    private static  void paintGameWindow(Graphics g) {
        g.drawImage(field, 212, 40, null);
    }

    private static class GameField extends JPanel {

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            paintGameWindow(g);
        }
    }
}
