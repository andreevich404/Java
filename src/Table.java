public class Table {
    private int[][] data;
    private int rows;
    private int cols;

    public Table(int rows, int cols) {
        if (rows <= 0 || cols <= 0) {
            throw new IllegalArgumentException("Размеры таблицы должны быть положительными");
        }
        this.rows = rows;
        this.cols = cols;
        this.data = new int[rows][cols];
    }

    private void checkBounds(int row, int col) {
        if (row < 0 || row >= rows || col < 0 || col >= cols) {
            throw new IllegalArgumentException("Некорректные координаты");
        }
    }

    public int getValue(int row, int col) {
        checkBounds(row, col);
        return data[row][col];
    }

    public void setValue(int row, int col, int value) {
        checkBounds(row, col);
        data[row][col] = value;
    }

    public int rows() {
        return rows;
    }

    public int cols() {
        return cols;
    }

    public double average() {
        if (rows == 0 || cols == 0) return 0;
        double sum = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                sum += data[i][j];
            }
        }
        return sum / (rows * cols);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                sb.append(data[i][j]).append(" ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}
