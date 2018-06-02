package work3;

import java.io.IOException;

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
        gameWindow.gameOver();
    }
}