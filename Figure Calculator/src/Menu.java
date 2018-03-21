import javax.imageio.plugins.tiff.ExifInteroperabilityTagSet;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu {
    public static void main(String[] args) {

        Scanner scanner;
        int n=0, f=0;
        while(true) {
            System.out.println(" \n Please make a choice \n");
            System.out.println(" 1.Choose a figure. \n 2.Exit. \n");
            scanner = new Scanner(System.in);
            try {
                n = scanner.nextInt();
            }
            catch(InputMismatchException ime) {
                System.out.println(" You have to enter a number. ");
                continue;
            }
            switch (n) {
                case 1:
                    System.out.println(" 1.Circle \n 2.Triangle \n 3.Square \n 4.Exit");
                    try {
                        f = scanner.nextInt();
                    }
                    catch(InputMismatchException ime) {
                        System.out.println(" You have to enter a number. ");
                        continue;
                    }
                    switch (f) {
                        case 1:
                            System.out.println(" Please enter a data ");
                            try {
                                System.out.println(" Enter radius: ");
                                double rad = scanner.nextDouble();
                                if(rad >0) {
                                    Circle circle = new Circle(rad);
                                    System.out.println(" The perimeter of this circle is:  " + circle.calculatePerimeter());
                                    System.out.println(" The area of this circle is: " + circle.calculateArea());
                                    circle.print();
                                }
                                else
                                    System.out.println("You put '-' number");
                            }
                            catch (InputMismatchException ime) {
                                System.out.println(" Exception - wrong data format! ");
                                scanner.nextLine();
                            }

                            break;
                        case 2:
                            System.out.println(" Please enter a data ");
                            try {
                                System.out.println("Enter side A: ");
                                double sideATriangle = scanner.nextDouble();
                                System.out.println("Enter side B:  ");
                                double sideBTriangle = scanner.nextDouble();
                                System.out.println("Enter side C: ");
                                double sideCTriangle = scanner.nextDouble();
if (sideATriangle<0 || sideBTriangle<0 || sideCTriangle<0) {
    Triangle triangle = new Triangle(sideATriangle, sideBTriangle, sideCTriangle);
    if (triangle.isTriangle(sideATriangle, sideBTriangle, sideCTriangle)) {
        System.out.println(" The perimeter of this triangle is: " + triangle.calculatePerimeter());
        System.out.println(" The area of this triangle is: " + triangle.calculateArea());
        triangle.print();
    } else {
        System.out.println(" Can't build the triangle. ");
    }
}
else
    System.out.println("You put a minus number");
                            }
                            catch (InputMismatchException ime) {
                                System.out.println(" Exception - wrong data format! ");
                                scanner.nextLine();
                            }
                            break;

                        case 3:
                            System.out.println(" Please enter a data ");
                            try {
                                System.out.println(" Enter side A: ");
                                double sideA = scanner.nextDouble();
                                if(sideA>0) {
                                    Square square = new Square(sideA);
                                    System.out.println(" The perimeter of this square is: " + square.calculatePerimeter());
                                    System.out.println(" The area of this square is :  " + square.calculateArea());
                                    square.print();
                                }
                                else
                                    System.out.println("You put a minus number");
                            }
                            catch (InputMismatchException ime) {
                                System.out.println(" Exception - wrong data format! ");
                                scanner.nextLine();
                            }
                            break;

                        case 4:
                            System.exit(0);
                            break;
                        default:
                            System.out.println(" There is no other option. ");
                            break;
                    }
                    break;

                case 2:
                    System.exit(0);
                    break;

                default:
                    System.out.println(" There is no other option. ");
                    break;
            }
        }
    }
}
