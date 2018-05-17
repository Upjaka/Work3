package work3;

public class Turn {

    /**
     * Метод, определяющий, можно ли сделать ход по заданным координатам нажатия мыши.
     * Возвращает true, если ход возможен, и false, если ход невозможен.
     */
    public boolean canDoTurn(int x, int y) {
        int[][] matrix = Reversy.field.getField();
        /*
         * Начальное положени поля
         */
        int x0 = 242;
        int y0 = 60;

        /*
        Определяем, каким строке и столбцу соответсвтует нажатие
        70 - развер одной клетки поля в пикселях
         */
        int i = (x - x0)/70;
        int j = (y - y0)/70;

        return (matrix[i][j] == 0);
    }

    public void nextTurn(int x, int y) {
        /*
         * Начальное положени поля
         */
        int x0 = 242;
        int y0 = 60;

        /*
        Определяем, каким строке и столбцу соответсвтует нажатие
        70 - развер одной клетки поля в пикселях
         */
        int i = (x - x0)/70;
        int j = (y - y0)/70;

        Reversy.field.setCellValue(i, j, Reversy.player);
        Reversy.player = (Reversy.player + 1) % 2;

        Reversy.field.updateField(i, j);
        if (Reversy.field.IsFull()) {
            System.exit(0);
        }
    }
}
