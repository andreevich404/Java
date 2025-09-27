package geometry3d;
import geometry2d.Figure;
import exceptions.InvalidDimensionException;

public class Cylinder {
    private Figure base;
    private double height;

    public Cylinder(Figure base, double height) {
        if (height <= 0) throw new InvalidDimensionException("Высота должна быть положительной");
        this.base = base;
        this.height = height;
    }

    public double volume() {
        return base.area() * height;
    }

    @Override
    public String toString() {
        return String.format("Цилиндр(основание=%s, высота=%.2f)", base, height);
    }
}
