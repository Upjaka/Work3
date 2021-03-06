package work3.Model;

/**
 * Класс представляющий игровое поле.
 * Хранит конфигурацию поля в двумерном массиве:
 * 0 - клетка пустая, 1 - в клете стоит фишка белого цвета,
 * -1 - в клетке стоит фишка черного цвета.
 */
public class Field {
    private int player = -1;
    private int[][] field;

    public int getPlayerScore(int player) {
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
        if (player == -1) return black;
        else return white;
    }

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
     * Метод, меняющий конфигурацию игрового поля во время совершения хода.
     */
    public void nextTurn(int i, int j) {
        this.setCellValue(i, j, this.player);

        this.updateField(i, j);
    }

    /**
     * Метод, меняющий значние в заданной ячейке.
     */
    public void setCellValue(int i, int j, int value) {
        this.field[j][i] = value;
    }

    /**
     * Вспомогательный метод для nextTurn, который переворачивает фишки в "закрытых" рядах.
     */
    private void updateField(int i, int j) {
        final int i0 = i;
        final int j0 = j;

        boolean flagDiagonal = true;
        boolean flagLine = true;

        while (i < 7) {
            i++;
            if (field[j0][i] == 0) flagLine = false;
            else if ((field[j0][i] == player) && flagLine) {
                this.cellPainting(i0, j0, i, j0);
                flagLine = false;
            }
            if (j0 + i - i0 < 8) {
                if (field[j0 + i - i0][i] == 0) flagDiagonal = false;
                else if ((field[j0 + i - i0][i] == player) && flagDiagonal) {
                    this.cellPainting(i0, j0, i, j0 + i - i0);
                    flagDiagonal = false;
                }
                if ((!flagDiagonal) && !flagLine) break;
            }
        }
        flagLine = true;
        flagDiagonal = true;
        i = i0;
        while (i > 0) {
            i--;
            if (field[j0][i] == 0) flagLine = false;
            else if ((field[j0][i] == player) && flagLine) {
                this.cellPainting(i, j0, i0, j0);
                flagLine = false;
            }
            if (j0 - (i0 - i) > -1) {
                if (field[j0 - (i0 - i)][i] == 0) flagDiagonal = false;
                else if ((field[j0 - (i0 - i)][i] == player) && flagDiagonal) {
                    this.cellPainting(i, j0 - (i0 - i), i0, j0);
                    flagDiagonal = false;
                }
                if ((!flagDiagonal) && !flagLine) break;
            }
        }
        flagLine = true;
        flagDiagonal = true;
        while (j < 7) {
            j++;
            if (field[j][i0] == 0) flagLine = false;
            else if ((field[j][i0] == player) && flagLine) {
                this.cellPainting(i0, j0, i0, j);
                flagLine = false;
            }
            if (i0 - (j - j0) > -1) {
                if (field[j][i0 - (j - j0)] == 0) flagDiagonal = false;
                else if ((field[j][i0 - (j - j0)] == player) && flagDiagonal) {
                    this.cellPainting(i0, j0, i0 - (j - j0), j);
                    flagDiagonal = false;
                }
                if ((!flagDiagonal) && !flagLine) break;
            }
        }
        flagLine = true;
        flagDiagonal = true;
        j = j0;
        while (j > 0) {
            j--;
            if (field[j][i0] == 0) flagLine = false;
            else if ((field[j][i0] == player) && flagLine) {
                this.cellPainting(i0, j, i0, j0);
                flagLine = false;
            }
            if (i0 + j0 - j < 8) {
                if (field[j][i0 + j0 - j] == 0) flagDiagonal = false;
                else if ((field[j][i0 + j0 - j] == player) && flagDiagonal) {
                    this.cellPainting(i0 + j0 - j, j, i0, j0);
                    flagDiagonal = false;
                }
                if ((!flagDiagonal) && !flagLine) break;
            }
        }
    }

    /**
     * Метод, закрашивающий ячейки от (i0, j0) до (i, j).
     */
    private void cellPainting(int i0, int j0, int i, int j) {
        if (i0 + j0 == i + j) {
            if (i0 < i) {
                for (int index = i0 + 1; index < i; index++) {
                    field[j + i - index][index] = player;
                }
            } else {
                for (int index = j0 + 1; index < j; index++) {
                    field[index][i + j - index] = player;
                }
            }

        } else if (i0 - j0 == i - j) {
            if (i0 < i) {
                for (int index = i0 + 1; index < i; index++) {
                    field[j0 + index - i0][index] = player;
                }
            } else {
                for (int index = j0 + 1; index < j; index++) {
                    field[index][i0 + index - i0] = player;
                }
            }
        } else if (j0 == j) {
            for (int index = i0 + 1; index < i; index++) {
                field[j][index] = player;
            }
        } else if (i0 == i) {
            for (int index = j0 + 1; index < j; index++) {
                field[index][i0] = player;
            }
        }
    }

    /**
     * Метод, проверяющий, является ли поле заполненным.
     * Возвращает true, если поле не заполненно,
     * возвращает false, если поле заполненно.
     */
    public boolean isFull() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (field[i][j] == 0) return false;
            }
        }
        return true;
    }

    /**
     * Метод, определяющий, можно ли сделать ход по заданным координатам нажатия мыши.
     * Возвращает true, если ход возможен, и false, если ход невозможен.
     */
    public boolean canDoTurn(int i, int j) {
        final int i0 = i;
        final int j0 = j;

        boolean flagLineOtherChip = false;
        boolean flagDiagonalOtherChip = false;
        boolean flagDiagonal = true;
        boolean flagLine = true;

        while (i < 7) {
            i++;
            if (field[j0][i] == 0) flagLine = false;
            else if (field[j0][i] == player * -1) flagLineOtherChip = true;
            else if (field[j0][i] == player && !flagLineOtherChip) flagLine = false;
            else if ((field[j0][i] == player) && flagLine && flagLineOtherChip) return true;
            if (j0 + i - i0 < 8) {
                if (field[j0 + i - i0][i] == 0) flagDiagonal = false;
                else if (field[j0 + i - i0][i] == player * -1) flagDiagonalOtherChip = true;
                else if (field[j0 + i - i0][i] == player && !flagDiagonalOtherChip) flagDiagonal = false;
                else if ((field[j0 + i - i0][i] == player) && flagDiagonal && flagDiagonalOtherChip) return true;
                if ((!flagDiagonal) && !flagLine) break;
            }
        }
        flagDiagonalOtherChip = false;
        flagLineOtherChip = false;
        flagLine = true;
        flagDiagonal = true;
        i = i0;
        while (i > 0) {
            i--;
            if (field[j0][i] == 0) flagLine = false;
            else if (field[j0][i] == player * -1) flagLineOtherChip = true;
            else if (field[j0][i] == player && !flagLineOtherChip) flagLine = false;
            else if ((field[j0][i] == player) && flagLine && flagLineOtherChip) return true;
            if (j0 - (i0 - i) > -1) {
                if (field[j0 - (i0 - i)][i] == 0) flagDiagonal = false;
                else if (field[j0 - (i0 - i)][i] == player && !flagDiagonalOtherChip) flagDiagonal = false;
                else if (field[j0 - (i0 - i)][i] == player * -1) flagDiagonalOtherChip = true;
                else if ((field[j0 - (i0 - i)][i] == player) && flagDiagonal && flagDiagonalOtherChip) return true;
                if ((!flagDiagonal) && !flagLine) break;
            }
        }
        flagDiagonalOtherChip = false;
        flagLineOtherChip = false;
        flagLine = true;
        flagDiagonal = true;
        while (j < 7) {
            j++;
            if (field[j][i0] == 0) flagLine = false;
            else if ((field[j][i0] == player) && !flagLineOtherChip) flagLine = false;
            else if (field[j][i0] == player * -1) flagLineOtherChip = true;
            else if ((field[j][i0] == player) && flagLine && flagLineOtherChip) return true;
            if (i0 - (j - j0) > -1) {
                if (field[j][i0 - (j - j0)] == 0) flagDiagonal = false;
                else if ((field[j][i0 - (j - j0)] == player) && !flagDiagonalOtherChip) flagDiagonal = false;
                else if (field[j][i0 - (j - j0)] == player * -1) flagDiagonalOtherChip = true;
                else if ((field[j][i0 - (j - j0)] == player) && flagDiagonal && flagDiagonalOtherChip) return true;
                if ((!flagDiagonal) && !flagLine) break;
            }
        }
        flagDiagonalOtherChip = false;
        flagLineOtherChip = false;
        flagLine = true;
        flagDiagonal = true;
        j = j0;
        while (j > 0) {
            j--;
            if (field[j][i0] == 0) flagLine = false;
            else if (field[j][i0] == player * -1) flagLineOtherChip = true;
            else if ((field[j][i0] == player) && !flagLineOtherChip) flagLine = false;
            else if ((field[j][i0] == player) && flagLine && flagLineOtherChip) return true;
            if (i0 + j0 - j < 8) {
                if (field[j][i0 + j0 - j] == 0) flagDiagonal = false;
                else if (field[j][i0 + j0 - j] == player * -1) flagDiagonalOtherChip = true;
                else if ((field[j][i0 + j0 - j] == player) && !flagDiagonalOtherChip) flagDiagonal = false;
                else if ((field[j][i0 + j0 - j] == player) && flagDiagonal && flagDiagonalOtherChip) return true;
                if ((!flagDiagonal) && !flagLine) break;
            }
        }
        return false;
    }

    /**
     * Метод, идентифицирующий патовую ситуацию.
     */
    public boolean isPat() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (field[i][j] == 0) {
                    if (this.canDoTurn(i, j)) return false;
                }
            }
        }
        return true;
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

    public int getPlayer() {
        return player;
    }

    public void setPlayer(int player) {
        this.player = player;
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
            final Field other = (Field) obj;
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    if (this.getField()[i][j] != other.getField()[i][j]) return false;
                }
            }
            return true;
        }
    }
}
