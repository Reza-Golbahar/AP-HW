//403106595

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int rows = scanner.nextInt();
        int columns = scanner.nextInt();
        int[][] grid = new int[rows][columns];
        boolean[][] visited = new boolean[rows][columns];
        PriorityQueue<int[]> heightQueue = new PriorityQueue<>(Comparator.comparingInt(a -> a[2]));

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                grid[i][j] = scanner.nextInt();
                if (i == 0 || j == 0 || i == rows - 1 || j == columns - 1) {
                    heightQueue.add(new int[]{i, j, grid[i][j]});
                    visited[i][j] = true;
                }
            }
        }

        int waterCollected = 0;
        int[] deltaX = {-1, 1, 0, 0};
        int[] deltaY = {0, 0, -1, 1};

        while (!heightQueue.isEmpty()) {
            int[] cell = heightQueue.poll();
            int xCoordinate = cell[0], yCoordinate = cell[1], height = cell[2];

            for (int i = 0; i < 4; i++) {
                int newCoordinateX = xCoordinate + deltaX[i];
                int newCoordinateY = yCoordinate + deltaY[i];
                if (newCoordinateX >= 0 &&
                        newCoordinateY >= 0 &&
                        newCoordinateX < rows &&
                        newCoordinateY < columns &&
                        !visited[newCoordinateX][newCoordinateY]) {

                    visited[newCoordinateX][newCoordinateY] = true;
                    waterCollected += Math.max(0, height - grid[newCoordinateX][newCoordinateY]);
                    heightQueue.add(new int[]{newCoordinateX, newCoordinateY, Math.max(grid[newCoordinateX][newCoordinateY], height)});
                }
            }
        }

        System.out.println(waterCollected);
    }
}
