package mazesolver;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Solver {
    public static void main(String[] args) throws FileNotFoundException {
        // creating list of mazes
        ArrayList<Maze> mazes = readMazes(); // new ArrayList<>();

        // looping over all mazes
        for (Maze i : mazes) {  // fancy ass for each
            boolean result = solveMaze(i);
            System.out.println();
            if (result) {
                System.out.println("Win! Path (o):");

                // display le display
                for (int[] j : i.maze) {  // for every row
                    for (int k : j) {  // print dep
                        if (k == -1) {  // should be path
                            System.out.print("o");
                        } else if (k == 69) {  // any explored that was dead end
                            System.out.print("_");
                        } else if (k == 1) {  // should be free
                            System.out.print("_");
                        } else if (k == 2) {
                            System.out.print("*");
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

    /**
     * Read any number of mazes from an input file mazes.txt
     * <p>
     *     This method uses the file <code>mazes.txt</code> to read a list of mazes from. Mazes should be formatted
     *     accordingly, as with the given example:
     * </p>
     * <p> <code>
     *     6 | The number of rows the maze has
     *     1,1,1,1,0,1,1,1,0,1,0 | the maze itself, which
     *     0,0,1,1,1,1,0,0,0,1,0 | is written according to
     *     0,0,0,1,0,1,1,1,1,1,1 | the following key:
     *     1,1,1,2,0,1,0,1,0,1,0 | 0: wall
     *     0,0,1,0,1,0,0,0,1,1,0 | 1: path
     *     0,0,0,1,1,1,1,0,0,0,0 | 2: goal
     *     4 | The y-coord (row index) of the start position
     *     8 | The x-coord (col index) of the start position
     *     --------------------- Divider, can be anything </code>
     * </p>
     *
     * @return Array of stored mazes
     * @throws FileNotFoundException
     */
    private static ArrayList<Maze> readMazes() throws FileNotFoundException {
        ArrayList<Maze> mazes = new ArrayList<>();
        Scanner in = new Scanner(new File("mazes.txt"));

        while (in.hasNext()) {
            // Filling list from file
            // 0 wall 1 path 2 dest
            // for n rows
            Maze m = new Maze();
            int nRows = Integer.parseInt(in.nextLine());  // gets the number of rows
            m.maze = new int[nRows][];

            // loop to incr
            for (int z = 0; z < nRows; z++) {
                // gets the row of the maze file, and reads it to an int array
                m.maze[z] = Arrays.stream(in.nextLine().split(",")).mapToInt(Integer::parseInt).toArray();
            }

            // for positions
            int y = Integer.parseInt(in.nextLine());
            int x = Integer.parseInt(in.nextLine());
            m.start = new Position(y, x);
            mazes.add(m);

            in.nextLine();  // reads the blank line and discard it
        }

        in.close();
        return mazes;
    }

    /**
     * Solves a maze m.
     *
     * @param m Object of type Maze
     * @return True for solveable, false for unsolvable
     */
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
                } else {
                    m.maze[y][x] = -1;  // set start to -1
                }
            }
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
            System.out.print("Backtracking. ");
            Position dd = m.path.pop();
            m.maze[dd.y][dd.x] = 69;
            if (m.path.size() <= 0) {
                return false;
            }
        }

    }

    /**
     * Makes a move on the maze if a valid move is to be found.
     * <p>
     *     Method will try to move to the coordinates specified, should they be within the array, and return whether
     *     it is possible or not. A direction should be provided as the method will print to console the direction
     *     of movement. For example, for a movement south, "South" should be passed.
     * </p>
     *
     * @param m Object of type Maze
     * @param y Index of row
     * @param x Index of column
     * @param dir Direction
     * @return True for valid movement, else false
     */
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
                System.out.printf("%s. ", dir);
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if the coordinates are within the bounds of the array.
     *
     * @param m Object of type Maze
     * @param y Index of row
     * @param x Index of column
     * @return True for valid, false for invalid
     */
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
