package test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Test {
    public static void main(String[] args) throws FileNotFoundException {
        // ARRAYS WITH USER INPUT
        /*
        Scanner in = new Scanner(System.in);

        System.out.print("Enter size of array: ");
        int size = in.nextInt();
        // arrays
        int[] grades = new int[size];  // preferred way, where [size] is the size of the array
        // int scores[];  // another way

        // from input
        System.out.println("Enter " + size + " grades below: ");
        for(int i = 0; i < size; i++) {
            grades[i] = in.nextInt();
        }

        // outputs
        for(int i = 0; i < size; i++) {
            System.out.print(grades[i] + " ");
        }
        System.out.print("\n");
        System.out.println(Arrays.toString(grades));  // because implicit is bad

        // close scan
        in.close();
         */

        // LISTS WITH FILE INPUT - because arrays are static
        Scanner in = new Scanner(new File("store.txt"));

        List<String> store = new ArrayList<String>();  // list is the interface, arraylist is the implementation
        // this whack

        while (in.hasNextLine()) {
            store.add(in.nextLine());
        }

        System.out.println(store);
        
        in.close();
    }
}
