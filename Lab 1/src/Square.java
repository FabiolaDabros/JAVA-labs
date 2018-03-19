import java.util.InputMismatchException;
import java.util.Scanner;

public class Square extends Figure
{
    private double sideA;

    public Square(double sideA) {
        this.sideA = sideA;
    }

    @Override
    public double calculateArea() {
        return sideA*sideA;
    }

    @Override
    public double calculatePerimeter() {
        return 4*sideA;
    }

    @Override
    public void print() {
        System.out.println(" Information about the square: \n Side A : "+ sideA);
    }
}
