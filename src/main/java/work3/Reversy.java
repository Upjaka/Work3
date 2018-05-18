package work3;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

import static work3.GameWindow.gameField;
import static work3.GameWindow.gameWindow;

public class Reversy {

    /*
    Список глобальных переменных
     */
    public static Field field = new Field();
    public static Turn turn = new Turn();
    public static int player = -1;
    public static int winner;

    public static void main(String[] args) throws IOException {
        GameWindow.openGameWindow();
    }

    public static void endGame() {
        winner = field.identifyWinner();
        gameWindow.remove(gameField);
        GameWindow.GameOver gameOver = new GameWindow.GameOver();
        gameOver.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                System.exit(0);
            }
        });
        gameWindow.add(gameOver);
        gameWindow.setVisible(true);
    }
}