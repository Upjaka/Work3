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

    public static GameWindow gameWindow;
    public static GameField gameField;
    public static Image field;
    private static Image frame;
    private static Image whiteChip;
    private static Image blackChip;


    public static void openGameWindow() throws IOException {
        BufferedImage bufferedImage = ImageIO.read(new File("src\\main\\resources\\game_dask.png"));
        field = bufferedImage.getScaledInstance(520, 520, 1);
        BufferedImage bufferedImage1 = ImageIO.read(new File("src\\main\\resources\\whiteChip.png"));
        whiteChip = bufferedImage1.getScaledInstance(65, 65, 1);
        BufferedImage bufferedImage2 = ImageIO.read(new File("src\\main\\resources\\blackChip.png"));
        blackChip = bufferedImage2.getScaledInstance(65, 65, 1);
        BufferedImage bufferedImage3 = ImageIO.read(new File("src\\main\\resources\\frame.png"));
        frame = bufferedImage3.getScaledInstance(644, 644, 1);
        gameWindow = new GameWindow();
        gameWindow.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        gameWindow.setLocation(200, 100);
        gameWindow.setSize(1024, 720);
        gameWindow.setResizable(false);
        gameField = new GameField();

        gameField.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                System.out.println(e.getX() + " " + e.getY());
                if (Reversy.turn.canDoTurn(e.getX(), e.getY())) {
                    Reversy.turn.nextTurn(e.getX(), e.getY());
                }
            }
        });

        gameWindow.add(gameField);
        gameWindow.setVisible(true);
    }



    public static class GameField extends JPanel {

        private static void paintChips(Graphics g) {
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    if (Reversy.field.getField()[i][j] == -1) {
                        g.drawImage(blackChip, 262 + 65 * j,
                                80 + 65 * i, null);
                    }   else {
                        if (Reversy.field.getField()[i][j] == 1) {
                            g.drawImage(whiteChip, 262 + 65 * j,
                                    80 + 65 * i, null);
                        }
                    }
                }
            }
        }

        private static void paintGameField(Graphics g) {
            g.drawImage(frame, 200, 18, null);
            g.drawImage(field, 262, 80, null);

        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            paintGameField(g);
            paintChips(g);
        }

    }

}
