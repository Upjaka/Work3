package work3.View;

import javax.swing.*;
import java.awt.*;

import static work3.View.Main.controller;

public class InfoBoard extends JPanel {
    private static JTextArea scoreBoard;
    private static JTextArea turnBoard;

    public InfoBoard() {
        scoreBoard = new JTextArea(5, 7);
        scoreBoard.setEditable(false);
        scoreBoard.setFont(new Font("Dialog", Font.PLAIN, 20));

        turnBoard = new JTextArea(5, 7);
        turnBoard.setEditable(false);
        turnBoard.setFont(new Font("Dialog", Font.PLAIN, 20));

        this.updateBoards();

        this.add(scoreBoard, BorderLayout.CENTER);
        this.add(turnBoard, BorderLayout.AFTER_LAST_LINE);

        this.setVisible(true);
    }

    public void updateBoards() {
        scoreBoard.setText("Black: " + controller.getPlayerScore(-1) + "\n" +
                "White: " + controller.getPlayerScore(1));
        if (controller.getPlayer() == -1) turnBoard.setText("Turn: Black");
        else turnBoard.setText("Turn: White");
    }
}
