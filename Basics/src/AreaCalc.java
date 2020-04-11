import java.util.Scanner;

public class AreaCalc {
    // class method - main() is a special identifier like __main__() in py
    // void referrs to the return type - void means nothing
    // public is an access modifier - public private protected etc.
    // static means this method can be invoked without object creation - OOP stuff
    // params here are (String[] args), args we can pass in string array (as by String[])
    public static void main(String[] args) {
        // section for menu
        int choice = 0;
        // for menu loop
        while(true) {
            System.out.flush();  // cls
            displaymenu();
            Scanner input = new Scanner(System.in);  // i fucking hate java inputs
            System.out.print("Please enter selection: ");

            String selection = input.next();

            // selection
            if(selection.equals("1")) {
                System.out.print("Enter width: ");
                double w = input.nextDouble();
                System.out.print("Enter length: ");
                double l = input.nextDouble();
                double ans = square(w, l);
                System.out.println("Your answer is "+ans);
                System.out.println();

            } else if(selection.equals("2")) {
                System.out.print("Enter width: ");
                double w = input.nextDouble();
                System.out.print("Enter height: ");
                double h = input.nextDouble();
                double ans = triangle(w, h);
                System.out.println("Your answer is "+ans);
                System.out.println();

            } else if(selection.equals("3")) {
                System.out.print("Enter radius: ");
                double r = input.nextDouble();
                double ans = circle(r);
                System.out.printf("Your answer is %.3f", ans);
                System.out.println();

            } else if(selection.equals("E")) {
                break;
            } else {
                System.out.println("Invalid entry!");
            }
        }
    }

    // other class methods
    public static void displaymenu() {
        System.out.println("AREA CALCULATOR - MENU");
        System.out.println("[1] Rectangle");
        System.out.println("[2] Triangle");
        System.out.println("[3] Circle");
        System.out.println("[E] Exit");
        System.out.println();
    }

    public static double square(double width, double length) {
        return width * length;
    }

    public static double triangle(double width, double height) {
        return 0.5 * width * height;
    }

    public static double circle(double radius) {
        double pi = 22.0/7;  // approximation bc i cba
        return pi * radius * radius;
    }
}
