package work3.Model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FieldTest {
    private Field field = new Field();

    @Test
    void isFull() {
        assertEquals(field.isFull(), false);
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                 field.setCellValue(i, j, -1);
            }
        }
        assertEquals(field.isFull(), true);
    }

    @Test
    void identifyWinner() {
        field = new Field();

        assertEquals(field.identifyWinner(), 0);

        field.setCellValue(0,0, -1);

        assertEquals(field.identifyWinner(), -1);

        field.setCellValue(0, 1, 1);
        field.setCellValue(0, 2, 1);

        assertEquals(field.identifyWinner(), 1);
    }

    @Test
    void nextTurn() {
        field = new Field();

        Field expectedField = new Field();
        expectedField.setCellValue(2, 4, -1);
        expectedField.setCellValue(3, 4, -1);

        field.nextTurn(2, 4);

        assertEquals(field.equals(expectedField), true);
    }

    @Test
    void canDoTurn() {
        field = new Field();

        assertEquals(field.canDoTurn(0, 0), false);
        assertEquals(field.canDoTurn(3, 4), false);
        assertEquals(field.canDoTurn(2, 3), false);
        assertEquals(field.canDoTurn(2, 4), true);
    }

    @Test
    void isPat() {
        field = new Field();

        assertEquals(field.isPat(), false);

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                field.setCellValue(i, j, 1);
            }
        }

        assertEquals(field.isPat(), true);

        field.setCellValue(3, 4, 0);

        assertEquals(field.isPat(), true);
    }

    @Test
    void equals1() {
        field = new Field();

        assertEquals(field.equals(field), true);
        assertEquals(field.equals(new int[8][8]), false);

        Field secondField = new Field();

        assertEquals(field.equals(secondField), true);

        secondField.setCellValue(0, 0, -1);

        assertEquals(field.equals(secondField), false);
    }
}