package work3;

import java.io.File;
import java.io.IOException;

public class Reversy {
    public static void main(String[] args) throws IOException {
        GameWindow.openGameWindow();
        Field field = new Field();

        /**
         * Переменная, показывающая, чей сейчас ход.
         * 1 - хоядт белые, 2 - ходят черные.
         */
        int player = 2;

        Turn turn = new Turn();

        while (field.IsFull()) {
            turn.nextTurn(field, player);
        }
    }
}