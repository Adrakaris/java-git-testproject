package mazesolver;

import java.util.Arrays;

public class Solver {
    public static void main(String[] args){

    }

    public static void arrEx() {
        // example of nd arrays
        int[][] data = {
                {1,2,3,4},
                {5,6,7,8},
                {9,10,11,12},
        };
        System.out.println(data[1][2]);  // returns 7
        System.out.println(Arrays.deepToString(data));  // prints array
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[i].length; j++) {
                System.out.print(data[i][j] + " ");  // prints numbers in order
            }
        }
        // BUT DID YOU KNOW
        // you can foreach. And IntelliJ apparently recognises data and puts the foreach iterator as datum
        // What fucking black magic is this
        // Props to you, JB
    }
}
