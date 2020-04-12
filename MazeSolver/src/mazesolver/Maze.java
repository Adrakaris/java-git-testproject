package mazesolver;

import java.util.LinkedList;

public class Maze {
    public int[][] maze;  // maze thing
    // 0 wall 1 path 2 dest
    public LinkedList<Position> path;  // path taken
    public Position start;  // start pos
}
