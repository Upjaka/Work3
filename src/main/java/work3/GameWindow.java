package work3;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static work3.Reversy.winner;


public class GameWindow extends JFrame {

    public static int field_X = 267;
    public static int field_Y = 85;
    public static int field_Size = 521;
    private static int frame_Size = 628;
    private static int window_Width = 1024;
    private static int window_Heigth = 720;
    public static int chip_Size = 64;


    public static JFrame gameWindow;
    public static GameField gameField;
    public static Image fieldImage;
    private static Image frameImage;
    private static Image whiteChipImage;
    private static Image blackChipImage;
    private static Image gameOverImage;
    private static Image whiteWinImage;
    private static Image blackWinImage;


    public static void openGameWindow() throws IOException {
        BufferedImage bufferedImage = ImageIO.read(new File("src\\main\\resources\\game_dask.png"));
        fieldImage = bufferedImage.getScaledInstance(field_Size, field_Size, 1);
        BufferedImage bufferedImage1 = ImageIO.read(new File("src\\main\\resources\\whiteChip.png"));
        whiteChipImage = bufferedImage1.getScaledInstance(chip_Size, chip_Size, 1);
        BufferedImage bufferedImage2 = ImageIO.read(new File("src\\main\\resources\\blackChip.png"));
        blackChipImage = bufferedImage2.getScaledInstance(chip_Size, chip_Size, 1);
        BufferedImage bufferedImage3 = ImageIO.read(new File("src\\main\\resources\\frame.png"));
        frameImage = bufferedImage3.getScaledInstance(frame_Size, frame_Size, 1);
        BufferedImage bufferedImage4 = ImageIO.read(new File("src\\main\\resources\\game_over.jpg"));
        gameOverImage = bufferedImage4.getScaledInstance(458, 458, 1);
        BufferedImage bufferedImage5 = ImageIO.read(new File("src\\main\\resources\\whiteWin.png"));
        whiteWinImage = bufferedImage5.getScaledInstance(300, 80, 1);
        BufferedImage bufferedImage6 = ImageIO.read(new File("src\\main\\resources\\blackWin.png"));
        blackWinImage = bufferedImage6.getScaledInstance(300, 80, 1);

        gameWindow = new JFrame("Revercy -- Java");
        gameWindow.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        gameWindow.setLocation(200, 100);
        gameWindow.setSize(window_Width, window_Heigth);
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


    public static class GameField extends JPanel {

        private static void paintChips(Graphics g) {
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    if (Reversy.field.getField()[i][j] == -1) {
                        g.drawImage(blackChipImage, field_X + chip_Size * j,
                                field_Y + chip_Size * i, null);
                    } else {
                        if (Reversy.field.getField()[i][j] == 1) {
                            g.drawImage(whiteChipImage, field_X + chip_Size * j,
                                    field_Y + chip_Size * i, null);
                        }
                    }
                }
            }
        }

        private static void paintGameField(Graphics g) {
            g.drawImage(frameImage, 210, 26, null);
            g.drawImage(fieldImage, 262, 80, null);

        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            paintGameField(g);
            paintChips(g);
        }

    }

    public static class GameOver extends JPanel {

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(gameOverImage, 283, 30, null);
            if (winner == -1) {
                g.drawImage(blackWinImage, 362, 550, null);
            } else {
                g.drawImage(whiteWinImage, 362, 550, null);
            }
        }

    }

}
