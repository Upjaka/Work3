package work3;

/**
 * Класс представляющий игровое поле.
 * Хранит конфигурацию поля в двумерном массиве:
 * 0 - клетка пустая, 1 - в клете стоит фишка белого цвета,
 * -1 - в клетке стоит фишка черного цвета.
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
        matrix[3][4] = -1;
        matrix[4][3] = -1;
        matrix[3][3] = 1;
        matrix[4][4] = 1;
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
        final int i0 = i;
        final int j0 = j;

        if (i < 7){
            i++;
            while (i < 8) {
                if (field[i][j0] == 0) break;
                if (field[i][j0] == Reversy.player) {
                    for (int index = i0 + 1; index < i; index++) {
                        field[index][j0] = Reversy.player;
                    }
                }
                i++;
            }
        }
        i = i0;
        if (i > 1){
            i--;
            while (i > 0) {
                if (field[i][j0] == 0) break;
                if (field[i][j0] == Reversy.player) {
                    for (int index = i0 - 1; index > i; index--) {
                        field[index][j0] = Reversy.player;
                    }
                }
                i--;
            }
        }
        if (j < 7){
            j++;
            while (j < 8) {
                if (field[i0][j] == 0) break;
                if (field[i0][j] == Reversy.player) {
                    for (int index = j0 + 1; index < j; index++) {
                        field[i0][index] = Reversy.player;
                    }
                }
                j++;
            }
        }
        j = j0;
        if (j < 1){
            j--;
            while (j < 0) {
                if (field[i0][j] == 0) break;
                if (field[i0][j] == Reversy.player) {
                    for (int index = j0 - 1; index > j; index--) {
                        field[i0][index] = Reversy.player;
                    }
                }
                j--;
            }
        }

        for (int index0 = 0; index0 < 8; index0++) {
            for (int index1 = 0; index1 < 8; index1++) {
                if (this.field[index0][index1] == -1) {
                    System.out.print(this.field[index0][index1] + " ");
                } else {
                    System.out.print(" " + this.field[index0][index1] + " ");
                }
            }
            System.out.println();
        }
        System.out.println();

        GameWindow.gameWindow.add(new GameWindow.GameField());
    }

    /**
     * Геттер конфигурации игрового поля.
     */
    public int[][] getField() {
        return field;
    }

    @Override
    public String toString() {
        String result = "";
        StringBuilder str = new StringBuilder(result);
        for (int index0 = 0; index0 < 8; index0++) {
            for (int index1 = 0; index1 < 8; index1++) {
                if (this.field[index0][index1] == -1) {
                    str.append(this.field[index0][index1]).append(" ");
                } else {
                    str.append(" ").append(this.field[index0][index1]).append(" ");
                }
            }
            str.append("\n");
        }
        result = str.toString();
        return result;
    }
}
