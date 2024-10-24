package org.example;

public class Main {

    public static final int WATER = 0;
    public static final int LAND = 1;
    public static final int CHECKED = 2;

    public static void main(String[] args) {

    /*
        // Test Case 1:
        int rows = 5;
        int cols = 8;
        int mat[][] = {
                {0, 0, 0, 0, 0, 0, 0, 1},
                {0, 1, 1, 1, 1, 0, 0, 1},
                {0, 1, 0, 1, 0, 0, 0, 1},
                {0, 1, 1, 1, 1, 0, 1, 0},
                {1, 0, 0, 0, 0, 1, 0, 1}
        };
    */

    /*
        // Test Case 2:
        int rows = 3;
        int cols = 3;
        int mat[][] = {
                {1, 0, 0},
                {0, 1, 0},
                {0, 0, 1}
        };
    */

        // Test Case 3:
        int rows = 5;
        int cols = 5;
        int mat[][] = {
                {0, 0, 0, 0, 0},
                {0, 1, 1, 1, 0},
                {0, 1, 1, 1, 0},
                {0, 1, 1, 1, 0},
                {0, 0, 0, 1, 0}
        };

        int result = getClosedIslandsNum(rows, cols, mat);
        System.out.println("The number of closed islands is: " + result);
    }

    private static int getClosedIslandsNum (int rows, int cols, int [][] matrix) {

        int closedIslandsNum = 0;

        /*
            Since we are interested only in islands surrounded by water, we'll check only the area that's not lying on the border.
            So if the matrix is 2 by 2 or smaller, there is no inside area and therefore no closed islands either.
        */
        if (rows <= 2 || cols <= 2) return 0;

        // Traversing only the inside area (excluding the border - first/last row and first/last column)
        for (int row = 1; row < rows - 1 ; row++) {
            for (int col = 1; col < cols - 1; col++) {

                int currentField = matrix[row][col];

                // If the current position is a Land, we check (recursively) if it's part of a closed island
                if (currentField == LAND) {
                    if (isClosedIsland(row, col, rows, cols, matrix)) closedIslandsNum++;
                }
            }
        }

        return closedIslandsNum;
    }

    private static boolean isClosedIsland (int currRow, int currCol, int rows, int cols, int [][] matrix) {

        // If the current field is NOT a Land (means it is Water or already Checked Land), we are not interested in it
        if (matrix[currRow][currCol] != LAND) return true;

        // If the current field is a Land and is on the map border, the island is considered for not closed!
        if (isFieldOnMapBorder(currRow, currCol, rows - 1, cols - 1)) return false;

        matrix[currRow][currCol] = CHECKED;

        // We check the surrounding fields to find all the land connected to current field
        boolean left = isClosedIsland(currRow, currCol - 1, rows, cols, matrix);
        boolean right = isClosedIsland(currRow, currCol + 1, rows, cols, matrix);
        boolean up = isClosedIsland(currRow - 1, currCol, rows, cols, matrix);
        boolean down = isClosedIsland(currRow + 1, currCol, rows, cols, matrix);

        return left && right && up && down;
    }

    private static boolean isFieldOnMapBorder(int currRow, int currCol, int lastRow, int lastCol) {

        return (currRow == 0 || currRow == lastRow) || (currCol == 0 || currCol == lastCol);
    }
}