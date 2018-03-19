import java.util.InputMismatchException;
import java.util.Scanner;

public class Triangle extends Figure
{
    private double sideA;
    private double sideB;
    private double sideC;

    public Triangle(double sideA, double sideB, double sideC) {
        this.sideA = sideA;
        this.sideB = sideB;
        this.sideC = sideC;
    }


public boolean isTriangle(double a, double b, double c){
    if(a+b<c)
        return false;
    else if(b+c<a)
        return false;
    else if(c+a<b)
        return false;
    return true;
}

    @Override
    public double calculateArea() {
        double halfPerimeter=calculatePerimeter()/2;
        return Math.sqrt(halfPerimeter*(halfPerimeter-sideA)*(halfPerimeter-sideB)*(halfPerimeter-sideC));
    }

    @Override
    public double calculatePerimeter() {
        return sideA+sideB+sideC;
    }

    @Override
    public void print() {
        System.out.println("Information about the triangle: \n Side A: "+ sideA + "; Side B: " + sideB + "; Side C: "+sideC );
    }
}
