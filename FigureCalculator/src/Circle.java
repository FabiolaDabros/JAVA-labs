import java.util.InputMismatchException;
import java.util.Scanner;

public class Circle extends Figure
{
    private double radius;

    public Circle(double radius) {
        this.radius = radius;
    }

    @Override
    public double calculateArea() {
        return Math.PI*radius*radius;
    }

    @Override
    public double calculatePerimeter() {
        return 2*Math.PI*radius;
    }

    @Override
    public void print() {
        System.out.printf(" Information about the circle: \n Radius: %f\n", radius);
    }
}