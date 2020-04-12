package mazesolver;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

public class Solver {
    public static void main(String[] args) {
        // creating list of mazes
        ArrayList<Maze> mazes = new ArrayList<>();

        // Creating mazes
        // 0 wall 1 path 2 dest
        Maze m = new Maze();
        m.maze = new int[][]{
            {1,1,1,1,0,1,1,1,0,1,0},
            {0,0,1,1,1,1,0,0,0,1,0},
            {0,0,0,1,0,1,1,1,1,1,1},
            {1,1,1,2,0,1,0,1,0,1,0},
            {0,0,1,0,1,0,0,0,1,1,0},
            {0,0,0,1,1,1,1,0,0,0,0}
        };
        m.start = new Position(4, 8);
        m.path = new LinkedList<>();

        Maze n = new Maze();
        n.maze = new int[][]{
            {2,1,1,1,0,1,1,1,0,1,0},
            {0,0,1,1,1,1,0,0,0,1,0},
            {0,0,0,1,0,1,1,1,1,1,1},
            {1,1,1,1,1,1,0,1,0,1,0},
            {0,0,1,0,1,0,0,0,1,1,0},
            {0,0,0,1,1,1,1,0,0,0,0}
        };
        n.start = new Position(0, 9);
        n.path = new LinkedList<>();

        //adding mazes
        mazes.add(m);
        mazes.add(n);

        // looping over all mazes
        for (Maze i : mazes) {  // fancy ass for each
            boolean result = solveMaze(i);
            if (result) {
                System.out.println("Win! Explored paths:");

                // display le display
                for (int[] j : i.maze) {  // for every row
                    for (int k : j) {  // print dep
                        if (k == -1) {  // should be path
                            System.out.print("o");
                        } else if (k == 1) {  // should be free
                            System.out.print("_");
                        } else {  // anything else is a wall
                            System.out.print("#");
                        }
                    }
                    System.out.print("\n");
                }
            } else {
                System.out.println("No path.");
            }
            System.out.println();
        }
    }

    public static boolean solveMaze(Maze m) {
        m.path.push(m.start);  // must be static to do this and access it w/o creating Solver class

        // stepping
        while (true) {
            // .peek() looks at the head of the list
            assert m.path.peek() != null;
            int y = m.path.peek().y;
            int x = m.path.peek().x;
            // System.out.printf("Peeked Position: y %d x %d%n", y, x);

            // check if current position has won
            if (isValid(m, y, x)) {
                if (m.maze[y][x] == 2) {
                    return true;
                }
            }
            m.maze[y][x] = -1;  // set start to -1
            // System.out.println(Arrays.deepToString(m.maze));

            // south
            if (move(m, y+1, x, "South")) {
                continue;
            }
            // west
            if (move(m, y, x-1, "West")) {
                continue;
            }
            // north
            if (move(m, y-1, x, "North")) {
                continue;
            }
            // east
            if (move(m, y, x+1, "East")) {
                continue;
            }

            // to backtrack
            System.out.println("Dead end, backtracking.");
            m.path.pop();
            if (m.path.size() <= 0) {
                return false;
            }
        }

    }

    public static boolean move(Maze m, int y, int x, String dir) {
        // move if the path is valid
        // win should already be checked
        if (isValid(m, y, x)) {
            // System.out.printf("'Tis valid in direction %s.%n", dir);
            // System.out.println(y + ", " + x + " is " + m.maze[y][x]);
            if (m.maze[y][x] == 1 ||
                m.maze[y][x] == 2) {
                // System.out.println("'Tis a one.");
                m.path.push(new Position(y, x));
                System.out.printf("Moved %s.%n", dir);
                return true;
            }
        }
        return false;
    }

    public static boolean isValid(Maze m, int y, int x) {
        /* if (y < 0 ||
                y >= m.maze.length ||
                x < 0 ||
                x >= m.maze[y].length) {  // out of bounds
            return false;  // the || is OR
        } */ // THIS IS THE ORIGINAL IF STATEMENT

        // INTELLIJ SUGGESTS THIS
        return y >= 0 &&
                y < m.maze.length &&
                x >= 0 &&
                x < m.maze[y].length;  // the && is OR
    }

    public static void arrEx() {
        // depreciated
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
