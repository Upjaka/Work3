package work3;

import java.io.IOException;

public class Reversy {

    /*
    Список глобальных переменных
     */
    public static Field field = new Field();
    public static Turn turn = new Turn();
    public static int player = 0;

    public static void main(String[] args) throws IOException {
        GameWindow.openGameWindow();
    }
}