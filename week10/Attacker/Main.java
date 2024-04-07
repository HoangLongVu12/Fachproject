package week10.Attacker;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {

    private static int n;
    private static char[][] house;
    private static boolean[][] visited;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        n = scanner.nextInt();
        scanner.nextLine(); // Consume the remaining newline

        house = new char[n][n];
        visited = new boolean[n][n];

        for (int i = 0; i < n; i++) {
            house[i] = scanner.nextLine().toCharArray();
        }

        int minDoors = findMinimumDoors();
        System.out.println(minDoors);
    }

    private static int findMinimumDoors() {
        int doors = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (!visited[i][j] && isBorder(i, j) && house[i][j] == '0') {
                    doors += bfs(i, j);
                }
            }
        }
        return doors;
    }

    private static int bfs(int x, int y) {
        if (visited[x][y]) {
            return 0;
        }

        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[] {x, y});
        visited[x][y] = true;

        int doors = 0;

        while (!queue.isEmpty()) {
            int[] cell = queue.poll();
            int cx = cell[0], cy = cell[1];

            for (int[] dir : new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}}) {
                int nx = cx + dir[0], ny = cy + dir[1];

                if (nx >= 0 && nx < n && ny >= 0 && ny < n && !visited[nx][ny] && house[nx][ny] == '0') {
                    visited[nx][ny] = true;
                    queue.offer(new int[]{nx, ny});
                    if (isInterior(nx, ny)) {
                        doors++;
                    }
                }
            }
        }

        return doors;
    }

    private static boolean isBorder(int x, int y) {
        return x == 0 || y == 0 || x == n - 1 || y == n - 1;
    }

    private static boolean isInterior(int x, int y) {
        return x > 0 && y > 0 && x < n - 1 && y < n - 1;
    }
}
