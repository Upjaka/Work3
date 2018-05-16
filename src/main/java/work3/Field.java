package work3;

/**
 * Класс представляющий игровое поле.
 * Хранит конфигурацию поля в двумерном массиве:
 * 0 - клетка пустая, 1 - в клете стоит фишка белого цвета,
 * 2 - в клетке стоит фишка черного цвета.
 */
public class Field {
    private int[][] field;

    /**
     * Конструктор класса создает игровое поле в его начальном состоянии:
     * все клетки пустые, кроме центральных четырех.
     */
    public Field() {
        int[][] matrix = new int[8][8];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                matrix[i][j] = 0;
            }
        }
        matrix[4][5] = 2;
        matrix[5][4] = 2;
        matrix[4][4] = 1;
        matrix[5][5] = 1;
        this.field = matrix;
    }

    /**
     * Метод, меняющий значние в заданной ячейке.
     */
    public void setCellValue(int x, int y, int value) {
        this.field[x][y] = value;
    }

    /**
     * Геттер конфигурации игрового поля.
     */
    public int[][] getField() {
        return field;
    }
}
