package mazesolver;

import java.util.Arrays;
import java.util.LinkedList;

public class Solver {

    // maze array
    static int[][] maze = {
            {1,1,1,1,0,1,1,1,0,1,0},
            {0,0,1,1,1,1,0,0,0,1,0},
            {0,0,0,1,0,1,1,1,1,1,1},
            {1,1,1,2,0,1,0,1,0,1,0},
            {0,0,1,0,1,0,0,0,1,1,0},
            {0,0,0,1,0,1,1,0,0,0,0}
    };
    // 0 wall 1 path 2 dest
    // start at [4][8]

    // pathstack
    static LinkedList<Position> path = new LinkedList<Position>();  // position is a custom type which needs its own class

    public static void main(String[] args){
        Position p = new Position(4, 8);
        path.push(p);  // must be static to do this and access it w/o creating Solver class

        // stepping
        while (true) {
            // .peek() looks at the head of the list
            assert path.peek() != null;
            int y = path.peek().y;
            int x = path.peek().x;
            maze[y][x] = 0;  // set start to 0
            // System.out.printf("Peeked Position: y %d x %d%n", y, x);

            // south
            if (isValid(y+1, x)) {
                if (maze[y+1][x] == 2) {
                    System.out.println("Moved south.\nWin!");
                    return;
                } else if (maze[y+1][x] == 1) {
                    path.push(new Position(y+1, x));
                    System.out.println("Moved south.");
                    continue;  // go to next iteration
                }
            }

            // west
            if (isValid(y, x-1)) {
                if (maze[y][x-1] == 2) {
                    System.out.println("Moved west.\nWin!");
                    return;
                } else if (maze[y][x-1] == 1) {
                    path.push(new Position(y, x-1));
                    System.out.println("Moved west.");
                    continue;  // go to next iteration
                }
            }

            //north
            if (isValid(y-1, x)) {
                if (maze[y-1][x] == 2) {
                    System.out.println("Moved north.\nWin!");
                    return;
                } else if (maze[y-1][x] == 1) {
                    path.push(new Position(y-1, x));
                    System.out.println("Moved north.");
                    continue;  // go to next iteration
                }
            }

            //east
            if (isValid(y, x+1)) {
                if (maze[y][x+1] == 2) {
                    System.out.println("Moved east.\nWin!");
                    return;
                } else if (maze[y][x+1] == 1) {
                    path.push(new Position(y, x+1));
                    System.out.println("Moved east.");
                    continue;  // go to next iteration
                }
            }

            // to backtrack
            System.out.println("Dead end, backtracking.");
            path.pop();
            if (path.size() <= 0) {
                System.out.println("No path.");
                return;
            }
        }

    }

    public static boolean isValid(int y, int x) {
        if (y < 0 ||
            y >= maze.length ||
            x < 0 ||
            x >= maze[y].length) {  // out of bounds
            return false;  // the || is OR
        }
        return true;
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
