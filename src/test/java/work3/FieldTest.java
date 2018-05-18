package work3;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FieldTest {

    @Test
    void setCellValue() {
    }

    @Test
    void isFull() {
        Field field = new Field();

        assertEquals(field.IsFull(), false);

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                field.setCellValue(i, j, 1);
            }
        }
        assertEquals(field.IsFull(), true);
    }

    @Test
    void updateField() {
    }

    @Test
    void getField() {
    }

    @Test
    void equals() {
        Field field1 = new Field();
        Field field2 = new Field();

        assertEquals(field1.equals(field2), true);

        field1.setCellValue(1, 2, 1);
        assertEquals(field1.equals(field2), false);

        field2.setCellValue(1, 2, 1);
        assertEquals(field1.equals(field2), true);
    }
}