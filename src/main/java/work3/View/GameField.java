package work3.View;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class GameField extends JPanel {
    private static int fieldX = 140;
    private static int chipSize = 64;

    private static Image fieldImage;
    private static Image frameImage;
    private static Image whiteChipImage;
    private static Image blackChipImage;

    public GameField() throws IOException {
        final int field_Size = 521;
        final int frame_Size = 628;

        BufferedImage bufferedImage = ImageIO.read(new File("src\\main\\resources\\game_dask.png"));
        fieldImage = bufferedImage.getScaledInstance(field_Size, field_Size, 1);
        BufferedImage bufferedImage1 = ImageIO.read(new File("src\\main\\resources\\whiteChip.png"));
        whiteChipImage = bufferedImage1.getScaledInstance(chipSize, chipSize, 1);
        BufferedImage bufferedImage2 = ImageIO.read(new File("src\\main\\resources\\blackChip.png"));
        blackChipImage = bufferedImage2.getScaledInstance(chipSize, chipSize, 1);
        BufferedImage bufferedImage3 = ImageIO.read(new File("src\\main\\resources\\frame.png"));
        frameImage = bufferedImage3.getScaledInstance(frame_Size, frame_Size, 1);
    }

    private static void paintChips(Graphics g) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (Main.getController().getField().getField()[j][i] != 0) {
                    int field_Y = 85;
                    g.drawImage(Main.getController().getField().getField()[j][i] == -1 ? blackChipImage :
                            whiteChipImage, fieldX + chipSize * i, field_Y + chipSize * j, null);
                }
            }
        }
    }

    private static void paintGameField(Graphics g) {
        g.drawImage(frameImage, fieldX - 55, 26, null);
        g.drawImage(fieldImage, fieldX - 5, 80, null);

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        paintGameField(g);
        paintChips(g);
    }

}
