package work3;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class GameWindow extends JFrame {

    private static GameWindow gameWindow;
    private static GameField gameField;
    private static Image field;
    private static Image whiteChip;
    private static Image blackChip;


    public static void openGameWindow() throws IOException {
        BufferedImage bufferedImage = ImageIO.read(new File("src\\main\\resources\\game_dask.png"));
        field = bufferedImage.getScaledInstance(560, 560, 1);
        BufferedImage bufferedImage1 = ImageIO.read(new File("src\\main\\resources\\whiteChip.png"));
        whiteChip = bufferedImage1.getScaledInstance(62, 62, 1);
        BufferedImage bufferedImage2 = ImageIO.read(new File("src\\main\\resources\\blackChip.png"));
        blackChip = bufferedImage2.getScaledInstance(62, 62, 1);
        gameWindow = new GameWindow();
        gameWindow.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        gameWindow.setLocation(200, 100);
        gameWindow.setSize(1024, 720);
        gameWindow.setResizable(false);
        gameField = new GameField();

        gameField.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (Reversy.turn.canDoTurn(e.getX(), e.getY())) {
                    Reversy.turn.nextTurn(e.getX(), e.getY());
                }
            }
        });

        gameWindow.add(gameField);
        gameWindow.setVisible(true);
    }

    private static void paintGameField(Graphics g) {
        g.drawImage(field, 242, 60, null);
    }

    private static class GameField extends JPanel {

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            paintGameField(g);
        }

    }
}
