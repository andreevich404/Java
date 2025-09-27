import geometry2d.Circle;
import geometry2d.Rectangle;
import geometry3d.Cylinder;
import exceptions.InvalidFigureException;
import exceptions.InvalidDimensionException;

public class Main {

    public static void main(String[] args) {
        System.out.println("=== Тест 1: Круг и цилиндр ===");
        try {
            Circle circle = new Circle(5.0);
            System.out.println("Создан: " + circle);
            System.out.printf("Площадь круга: %.2f\n", circle.area());
            System.out.printf("Периметр круга: %.2f\n", circle.perimeter());

            Cylinder cylinder = new Cylinder(circle, 10.0);
            System.out.println("Создан: " + cylinder);
            System.out.printf("Объем цилиндра: %.2f\n", cylinder.volume());
        } catch (Exception e) {
            System.out.println("Ошибка: " + e.getMessage());
        }

        System.out.println("\n=== Тест 2: Прямоугольник и цилиндр ===");
        try {
            Rectangle rectangle = new Rectangle(3.0, 4.0);
            System.out.println("Создан: " + rectangle);
            System.out.printf("Площадь прямоугольника: %.2f\n", rectangle.area());
            System.out.printf("Периметр прямоугольника: %.2f\n", rectangle.perimeter());

            Cylinder cylinder = new Cylinder(rectangle, 7.0);
            System.out.println("Создан: " + cylinder);
            System.out.printf("Объем цилиндра: %.2f\n", cylinder.volume());
        } catch (Exception e) {
            System.out.println("Ошибка: " + e.getMessage());
        }

        System.out.println("\n=== Тест 3: Несколько фигур ===");
        try {
            Circle smallCircle = new Circle(2.5);
            Rectangle largeRectangle = new Rectangle(8.0, 6.0);

            Cylinder cylinder1 = new Cylinder(smallCircle, 5.0);
            Cylinder cylinder2 = new Cylinder(largeRectangle, 3.0);

            System.out.println("Цилиндр 1: " + cylinder1);
            System.out.printf("Объем 1: %.2f\n", cylinder1.volume());

            System.out.println("Цилиндр 2: " + cylinder2);
            System.out.printf("Объем 2: %.2f\n", cylinder2.volume());
        } catch (Exception e) {
            System.out.println("Ошибка: " + e.getMessage());
        }

        System.out.println("\n=== Тест 4: Обработка исключений ===");

        System.out.println("Попытка создать круг с отрицательным радиусом:");
        try {
            Circle invalidCircle = new Circle(-5.0);
        } catch (InvalidFigureException e) {
            System.out.println("Поймано InvalidFigureException: " + e.getMessage());
        }

        System.out.println("Попытка создать прямоугольник с нулевой стороной:");
        try {
            Rectangle invalidRect = new Rectangle(0.0, 5.0);
        } catch (InvalidFigureException e) {
            System.out.println("Поймано InvalidFigureException: " + e.getMessage());
        }

        System.out.println("Попытка создать цилиндр с отрицательной высотой:");
        try {
            Circle circle = new Circle(5.0);
            Cylinder invalidCylinder = new Cylinder(circle, -2.0);
        } catch (InvalidDimensionException e) {
            System.out.println("Поймано InvalidDimensionException: " + e.getMessage());
        }

        System.out.println("Попытка создать цилиндр с нулевой высотой:");
        try {
            Rectangle rect = new Rectangle(3.0, 4.0);
            Cylinder invalidCylinder = new Cylinder(rect, 0.0);
        } catch (InvalidDimensionException e) {
            System.out.println("Поймано InvalidDimensionException: " + e.getMessage());
        }

        System.out.println("\n=== Тест 5: Различные вычисления ===");
        try {
            Circle circle1 = new Circle(1.0);  // Маленький круг
            Circle circle2 = new Circle(10.0); // Большой круг

            Rectangle rect1 = new Rectangle(2.0, 2.0); // Квадрат
            Rectangle rect2 = new Rectangle(1.0, 10.0); // Вытянутый прямоугольник

            System.out.println("Сравнение площадей:");
            System.out.printf("Круг r=1.0: площадь=%.2f, периметр=%.2f\n",
                    circle1.area(), circle1.perimeter());
            System.out.printf("Круг r=10.0: площадь=%.2f, периметр=%.2f\n",
                    circle2.area(), circle2.perimeter());
            System.out.printf("Квадрат 2x2: площадь=%.2f, периметр=%.2f\n",
                    rect1.area(), rect1.perimeter());
            System.out.printf("Прямоугольник 1x10: площадь=%.2f, периметр=%.2f\n",
                    rect2.area(), rect2.perimeter());

            System.out.println("\nСравнение объемов цилиндров:");
            Cylinder cyl1 = new Cylinder(circle1, 5.0);
            Cylinder cyl2 = new Cylinder(rect2, 5.0);
            System.out.printf("Цилиндр (круг r=1.0, h=5.0): объем=%.2f\n", cyl1.volume());
            System.out.printf("Цилиндр (прямоугольник 1x10, h=5.0): объем=%.2f\n", cyl2.volume());

        } catch (Exception e) {
            System.out.println("Ошибка: " + e.getMessage());
        }

        System.out.println("\n=== Тест 6: toString() представление ===");
        try {
            Circle circle = new Circle(7.5);
            Rectangle rectangle = new Rectangle(4.2, 3.1);
            Cylinder cylinder = new Cylinder(circle, 12.3);

            System.out.println("Circle toString(): " + circle);
            System.out.println("Rectangle toString(): " + rectangle);
            System.out.println("Cylinder toString(): " + cylinder);

        } catch (Exception e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
    }
}