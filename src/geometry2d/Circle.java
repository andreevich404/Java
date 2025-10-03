package geometry2d;
import exceptions.InvalidFigureException;

public class Circle implements Figure {
    private double radius;

    public Circle(double radius) {
        if (radius <= 0) throw new InvalidFigureException("Радиус должен быть положительным");
        this.radius = radius;
    }

    @Override
    public double area() {
        return Math.PI * radius * radius;
    }

    @Override
    public double perimeter() {
        return 2 * Math.PI * radius;
    }

    @Override
    public String toString() {
        return String.format("Круг(радиус =%.2f)", radius);
    }
}
