package test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Test {
    public static void main(String[] args) { //throws FileNotFoundException {
        /*
        // ARRAYS WITH USER INPUT
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

        // LISTS WITH FILE INPUT - because arrays are static
        Scanner in = new Scanner(new File("store.txt"));
        ArrayList<String> store = new ArrayList<String>();  // list is the interface, arraylist is the implementation // this whack
        // ArrayList must meet all the requirements of List interface
        // This is a collection - such as lists, sets, maps
        while (in.hasNextLine()) {
            store.add(in.nextLine());
        }
        System.out.println(store);

        in.close();
         */

        LinkedList<String> names = new LinkedList<String>();
        // add is as append, push is to put at head
        names.push("Abbey");
        names.push("Bob");
        names.push("Carol");
        // L = [Carol, Bob, Abbey]

        // using an iterator and using WHILE
        Iterator<String> it = names.iterator();  // returns Iterator<E>, parameterise
        while (it.hasNext()) {  // print entry by entry with WHILE
            System.out.println(it.next());
        }

        /*ListIterator<String> lit = names.listIterator();
        lit.next(); lit.next(); lit.add("Zim");  // alt way of adding */

        names.add(2, "Zim");
        // enchanced for, FOREACH
        for(String s : names) {
            System.out.println(s);
        }

        /*System.out.println(names.pop());  // .remove() can also .pop()
        System.out.println(names.remove());  // but .remove can take an index for arg
        System.out.println(names.removeFirst());  // can also*/
    }
}
