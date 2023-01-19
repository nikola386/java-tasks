import java.util.*;

/*
Create an interface Shape defining two methods calculateArea() and calculatePerimeter().
•	Create four classes implementing the Shape interface - Square, Rectangle, Triangle and Circle.
•	Implement calculateArea() and calculatePerimeter methods in all four classes.
•	Create a new class with methods that operate with Shapes. Implement the following methods:
    -	Method that takes a list of shapes and returns the sum of the area of the shapes.
    -	Method that takes a list of shapes and returns the shape with the biggest area.
    -	Method that takes a list of shapes and returns the sum of the perimeters of all shapes.
    -	Method that takes a list of shapes and returns the sum of the perimeters of all Circles.
•	Create a program with main method to test all Shape operation methods.
 */
class ShapeOps {
    public double sumOfAreas(List<Shape> shapes) {
        if(shapes.size() == 0) {
            return 0;
        }

        return shapes.stream().reduce(0.0, (subtotal, shape) -> subtotal + shape.calculateArea(), Double::sum);
    }

    public Shape biggestArea(List<Shape> shapes) {
        if(shapes.size() == 0) {
            return null;
        }

        return shapes
                .stream()
                .max(Comparator.comparing(Shape::calculateArea))
                .orElseThrow(NoSuchElementException::new);
    }

    public double sumOfPerimeters(List<Shape> shapes) {
        if(shapes.size() == 0) {
            return 0;
        }

        return shapes.stream().reduce(0.0, (subtotal, shape) -> subtotal + shape.calculatePerimeter(), Double::sum);
    }

    public double sumOfCirclePerimeters(List<Shape> shapes) {
        if(shapes.size() == 0) {
            return 0;
        }
        return shapes
                .stream()
                .filter((shape) -> shape instanceof Circle)
                .reduce(0.0, (subtotal, shape) -> subtotal + shape.calculatePerimeter(), Double::sum);
    }
}

public class Main {
    public static void main(String[] args) {
        List<Shape> shapes = new ArrayList<>();
        shapes.add(new Circle(10));
        shapes.add(new Circle(5));

        shapes.add(new Square(2));
        shapes.add(new Square(4));

        shapes.add(new Rectangle(2,3));
        shapes.add(new Rectangle(6,7));

        shapes.add(new Triangle(3,4,5));
        shapes.add(new Triangle(6,7,8));

        ShapeOps ops = new ShapeOps();
        System.out.printf("sumOfAreas: %f%n", ops.sumOfAreas(shapes));
        System.out.printf("sumOfPerimeters: %f%n", ops.sumOfPerimeters(shapes));
        System.out.printf("sumOfCirclePerimeters: %f%n", ops.sumOfCirclePerimeters(shapes));

        Shape biggestArea = ops.biggestArea(shapes);
        System.out.printf("biggestArea: %s - %f%n", biggestArea.getClass().getName(), biggestArea.calculateArea());
    }
}