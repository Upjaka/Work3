package work3.View;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class GameOver extends JPanel {

    private static Image gameOverImage;
    private static Image whiteWinImage;
    private static Image blackWinImage;

    public GameOver() throws IOException {
        final int gameOverSize = 458;
        final int winWidth = 300;
        final int winHeight = 80;

        BufferedImage bufferedImage4 = ImageIO.read(new File("src\\main\\resources\\game_over.jpg"));
        gameOverImage = bufferedImage4.getScaledInstance(gameOverSize, gameOverSize, 1);
        BufferedImage bufferedImage5 = ImageIO.read(new File("src\\main\\resources\\whiteWin.png"));
        whiteWinImage = bufferedImage5.getScaledInstance(winWidth, winHeight, 1);
        BufferedImage bufferedImage6 = ImageIO.read(new File("src\\main\\resources\\blackWin.png"));
        blackWinImage = bufferedImage6.getScaledInstance(winWidth, winHeight, 1);
    }

    @Override
    protected void paintComponent(Graphics g) {
        int winner = Main.getController().getField().identifyWinner();
        super.paintComponent(g);
        g.drawImage(gameOverImage, 283, 30, null);
        g.drawImage((winner == -1) ? blackWinImage : whiteWinImage, 362, 550, null);
    }
}