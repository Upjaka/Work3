package work3;

public class Turn {

    /**
     * Метод, определяющий, можно ли сделать ход по заданным координатам нажатия мыши.
     * Возвращает true, если ход возможен, и false, если ход невозможен.
     */
    public boolean canDoTurn(int x, int y) {
        /*
         * Начальное положени поля
         */
        int x0 = 242;
        int y0 = 60;

        if ((x < x0) || (y < y0) || (x > x0 + 560) || (y > y0 + 560)) return false;
        else {

        /*
        Определяем, каким строке и столбцу соответсвтует нажатие
        65 - развер одной клетки поля в пикселях
         */
            int i = (x - x0) / 65;
            int j = (y - y0) / 65;

            return (Reversy.field.getField()[i][j] == -1);
        }
    }

    public void nextTurn(int x, int y) {
        /*
         * Начальное положени поля
         */
        final int x0 = 242;
        final int y0 = 60;

        /*
        Определяем, каким строке и столбцу соответсвтует нажатие
        65 - развер одной клетки поля в пикселях
         */
        int i = (x - x0)/65;
        int j = (y - y0)/65;

        Reversy.field.setCellValue(i, j, Reversy.player);

        Reversy.field.updateField(i, j);

        Reversy.player *= -1;

        if (Reversy.field.IsFull()) {
            System.exit(0);
        }
    }
}
