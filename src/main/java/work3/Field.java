package work3;

import static work3.Reversy.player;

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

        matrix[3][4] = 1;
        matrix[4][3] = 1;
        matrix[3][3] = -1;
        matrix[4][4] = -1;

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

        if (i < 7) {
            i++;
            while (i < 8) {
                if (field[j0][i] == 0) break;
                if (field[j0][i] == player) {
                    for (int index = i0 + 1; index < i; index++) {
                        field[j0][index] = player;
                    }
                }
                i++;
            }
        }
        i = i0;
        if (i > 0) {
            i--;
            while (i > -1) {
                if (field[j0][i] == 0) break;
                if (field[j0][i] == player) {
                    for (int index = i0 - 1; index > i; index--) {
                        field[j0][index] = player;
                    }
                }
                i--;
            }
        }
        if (j < 7) {
            j++;
            while (j < 8) {
                if (field[j][i0] == 0) break;
                if (field[j][i0] == player) {
                    for (int index = j0 + 1; index < j; index++) {
                        field[index][i0] = player;
                    }
                }
                j++;
            }
        }
        j = j0;
        if (j > 0) {
            j--;
            while (j > -1) {
                if (field[j][i0] == 0) break;
                if (field[j][i0] == player) {
                    for (int index = j0 - 1; index > j; index--) {
                        field[index][i0] = player;
                    }
                }
                j--;
            }
        }
    }

    /**
     * Метод, определяющий победителя.
     */
    public int identifyWinner() {
        int black = 0;
        int white = 0;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (field[i][j] == 1) {
                    white++;
                } else {
                    if (field[i][j] == -1) {
                        black++;
                    }
                }
            }
        }
        if (black > white) return -1;
        else if (black != white) return 1;
        else return 0;
    }

    /**
     * Геттер конфигурации игрового поля.
     */
    public int[][] getField() {
        return field;
    }

    /**
     * Сеттер конфигурации игрового поля.
     */
    public void setField(int[][] field) {
        this.field = field;
    }

    /**
     * Переопределение метода equals для проверки объектов класса на содержимое.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null || obj.getClass() != this.getClass())
            return false;
        else if (this == obj)
            return true;
        else {
            Field other = (Field) obj;
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    if (this.getField()[i][j] != other.getField()[i][j]) return false;
                }
            }
            return true;
        }
    }
}
