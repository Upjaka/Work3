package work3.View;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static work3.View.Main.controller;

public class GameOver extends JPanel {
    private static int winner = controller.getField().identifyWinner();
    private static Image gameOverImage;
    private static Image whiteWinImage;
    private static Image blackWinImage;

    public GameOver() throws IOException{
        BufferedImage bufferedImage4 = ImageIO.read(new File("src\\main\\resources\\game_over.jpg"));
        gameOverImage = bufferedImage4.getScaledInstance(458, 458, 1);
        BufferedImage bufferedImage5 = ImageIO.read(new File("src\\main\\resources\\whiteWin.png"));
        whiteWinImage = bufferedImage5.getScaledInstance(300, 80, 1);
        BufferedImage bufferedImage6 = ImageIO.read(new File("src\\main\\resources\\blackWin.png"));
        blackWinImage = bufferedImage6.getScaledInstance(300, 80, 1);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(gameOverImage, 283, 30, null);
        g.drawImage((winner == -1) ? blackWinImage : whiteWinImage, 362, 550, null);
    }
}