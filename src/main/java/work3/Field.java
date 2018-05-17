package work3;

/**
 * Класс представляющий игровое поле.
 * Хранит конфигурацию поля в двумерном массиве:
 * -1 - клетка пустая, 1 - в клете стоит фишка белого цвета,
 * 0 - в клетке стоит фишка черного цвета.
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
                matrix[i][j] = -1;
            }
        }
        matrix[4][5] = 0;
        matrix[5][4] = 0;
        matrix[4][4] = 1;
        matrix[5][5] = 1;
        this.field = matrix;
    }

    /**
     * Метод, меняющий значние в заданной ячейке.
     */
    public void setCellValue(int i, int j, int value) {
        this.field[j][i] = value;
    }

    /**
     * Метод, проверяющий, является ли поле заполненным.
     * Возвращает true, если поле не заполненно,
     * возвращает false, если поле заполненно.
     */
    public boolean IsFull() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (this.field[i][j] == 0) return false;
            }
        }
        return true;
    }

    /**
     * Метод, который переворачивает фишки в "закрытых" рядах.
     */
    public void updateField(int i, int j) {
        /*
        Фиксируем начальную клетку
         */
        int i0 = i;
        int j0 = j;

        if (i < 7){
            i++;
            while (i < 8) {
                if (field[i][j] == -1) break;
                if (field[i][j] == Reversy.player) {
                    for (int index = i0 + 1; index < i; index++) {
                        field[index][j] = Reversy.player;
                    }
                }
            }
        }
    }

    /**
     * Геттер конфигурации игрового поля.
     */
    public int[][] getField() {
        return field;
    }
}
