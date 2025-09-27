public class Balance {
    private int leftWeight;
    private int rightWeight;

    public Balance() {
        this.leftWeight = 0;
        this.rightWeight = 0;
    }

    public void addRight(int weight) {
        if (weight < 0) {
            throw new IllegalArgumentException("Вес не может быть отрицательным");
        }
        rightWeight += weight;
    }

    public void addLeft(int weight) {
        if (weight < 0) {
            throw new IllegalArgumentException("Вес не может быть отрицательным");
        }
        leftWeight += weight;
    }

    public void result() {
        if (leftWeight == rightWeight) {
            System.out.println("=");
        }

        if (leftWeight > rightWeight) {
            System.out.println("L");
        }

        if (rightWeight > leftWeight) {
            System.out.println("R");
        }
    }
}
