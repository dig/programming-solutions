/**
 * @author Joseph.
 * https://open.kattis.com/problems/10kindsofpeople
 */

import java.util.*;

class Coordinate {

    private int x, y;
    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    @Override
    public String toString() {
        return "[X=" + this.x + "Y=" + this.y + "]";
    }

}

public class TenKindsOfPeople {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int row = sc.nextInt();
        int column = sc.nextInt();
        sc.nextLine();

        //--- Parse map into 2D int array
        int[][] map = new int[row][column];
        for (int i = 0; i < row; i++) {
            char[] chars = sc.nextLine().toCharArray();

            for (int k = 0; k < column; k++) {
                map[i][k] = Character.getNumericValue(chars[k]);
            }
        }

        int amountOfCoords = sc.nextInt();

        //--- Parse coordinates
        Map<Coordinate, Coordinate> coordinateMap = new LinkedHashMap<>();
        for (int i = 0; i < amountOfCoords; i++) {
            Coordinate from = new Coordinate(sc.nextInt() - 1, sc.nextInt() - 1);
            Coordinate to = new Coordinate(sc.nextInt() - 1, sc.nextInt() - 1);

            coordinateMap.put(from, to);
        }

        //--- Check if coordinate can go from one to another
        for (Coordinate from : coordinateMap.keySet()) {
            Coordinate to = coordinateMap.get(from);

            PathFinder decimalPathFinder = new PathFinder(deepCopyIntMatrix(map), 1, 0, row, column, from, to);
            PathFinder binaryPathFinder = new PathFinder(deepCopyIntMatrix(map), 0, 1, row, column, from, to);

            String result = "neither";
            if (decimalPathFinder.pathExist()) {
                result = "decimal";
            }
            if (binaryPathFinder.pathExist()) {
                result = "binary";
            }

            System.out.println(result);
        }

        sc.close();
    }

    public static int[][] deepCopyIntMatrix(int[][] input) {
        int[][] result = new int[input.length][];
        for (int r = 0; r < input.length; r++) {
            result[r] = input[r].clone();
        }
        return result;
    }

}

/**
 *  Path finding uses Breadth-First search.
 */

class PathFinder {

    private int[][] matrix;
    private int floors;
    private int walls;
    private int rows;
    private int columns;
    private Coordinate from;
    private Coordinate to;
    private boolean possible;

    private static int ALREADY_VISITED = 2;

    public PathFinder(int[][] matrix, int floors, int walls, int rows, int columns, Coordinate from, Coordinate to) {
        this.matrix = matrix;
        this.floors = floors;
        this.walls = walls;
        this.rows = rows;
        this.columns = columns;
        this.from = from;
        this.to = to;
        this.possible = false;
        this.calculatePath();
    }

    public boolean pathExist() { return this.possible; }

    private void calculatePath() {
        int N = matrix.length;

        List<Coordinate> queue = new ArrayList<>();
        queue.add(from);
        boolean pathExists = false;

        while(!queue.isEmpty()) {
            Coordinate current = queue.remove(0);
            if (current.getX() == to.getX() &&
                    current.getY() == to.getY() &&
                    matrix[current.getX()][current.getY()] == floors) {
                pathExists = true;
                break;
            }

            matrix[current.getX()][current.getY()] = ALREADY_VISITED;

            List<Coordinate> neighbors = getNeighbors(current);
            queue.addAll(neighbors);
        }

        this.possible = pathExists;
    }

    public void display() {
        for (int i = 0; i < matrix.length; i++) {
            String line = "";
            for (int k = 0; k < matrix[i].length; k++) {
                line += String.valueOf(matrix[i][k]);
            }

            System.out.println(line);
        }
    }

    private List<Coordinate> getNeighbors(Coordinate node) {
        List<Coordinate> neighbors = new ArrayList<>();

        if (isValidPoint(node.getX() - 1, node.getY())) {
            neighbors.add(new Coordinate(node.getX() - 1, node.getY()));
        }

        if (isValidPoint(node.getX() + 1, node.getY())) {
            neighbors.add(new Coordinate(node.getX() + 1, node.getY()));
        }

        if (isValidPoint(node.getX(), node.getY() - 1)) {
            neighbors.add(new Coordinate(node.getX(), node.getY() - 1));
        }

        if (isValidPoint(node.getX(), node.getY() + 1)) {
            neighbors.add(new Coordinate(node.getX(), node.getY() + 1));
        }

        return neighbors;
    }

    private boolean isValidPoint(int x, int y) {
        return !(x < 0 || y < 0 || y >= columns || x >= rows) &&
                (matrix[x][y] != ALREADY_VISITED && matrix[x][y] != walls);
    }
}