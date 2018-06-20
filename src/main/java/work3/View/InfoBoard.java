package work3.View;

import work3.Controller.Controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class InfoBoard extends JPanel
        implements ActionListener {
    private static JTextArea scoreBoard;
    private static JTextArea turnBoard;

    public InfoBoard() {
        scoreBoard = new JTextArea(5, 9);
        scoreBoard.setEditable(false);
        scoreBoard.setFont(new Font("Dialog", Font.PLAIN, 20));

        turnBoard = new JTextArea(5, 9);
        turnBoard.setEditable(false);
        turnBoard.setFont(new Font("Dialog", Font.PLAIN, 20));

        this.updateBoards();

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        turnBoard.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.add(turnBoard);
        scoreBoard.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.add(scoreBoard);

        JButton restart = new JButton("Restart game");
        restart.setActionCommand("Restart the game");
        restart.addActionListener(this);

        restart.setAlignmentX(Component.CENTER_ALIGNMENT);

        this.add(restart);

        this.setVisible(true);
    }

    public void updateBoards() {
        scoreBoard.setText("Black: " + Main.getController().getPlayerScore(-1) + "\n" +
                "White: " + Main.getController().getPlayerScore(1));
        if (Main.getController().getPlayer() == -1) turnBoard.setText("Turn: Black");
        else turnBoard.setText("Turn: White");
    }

    public void actionPerformed(ActionEvent e) {
        try {
            Main.getGame().gameWindow.dispose();
            Main.setController(new Controller());
            Main.setGame(new Game());
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }
}
