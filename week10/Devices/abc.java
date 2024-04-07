package week10.Devices;

import java.util.*;

public class abc {

    private static List<Integer>[] connections;
    private static boolean[] used;
    private static int[] deviceToOutlet;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int m = scanner.nextInt(); // Number of outlets
        int n = scanner.nextInt(); // Number of devices
        int k = scanner.nextInt(); // Number of connections

        connections = (List<Integer>[]) new List[m + 1];
        for (int i = 0; i <= m; i++) {
            connections[i] = new ArrayList<>();
        }

        for (int i = 0; i < k; i++) {
            int outlet = scanner.nextInt();
            int device = scanner.nextInt();
            connections[outlet].add(device);
        }

        int result = findMaxDevices(m, n);
        System.out.println(result);
    }

    private static int findMaxDevices(int m, int n) {
        deviceToOutlet = new int[n + 1];
        Arrays.fill(deviceToOutlet, -1);

        int result = 0;
        for (int outlet = 1; outlet <= m; outlet++) {
            used = new boolean[n + 1];
            if (dfs(outlet)) result++;
        }

        // Check if power strip can be used to connect more devices
        if (result < m + 2 && result < n) {
            result = Math.min(result + 2, n);
        }

        return result;
    }

    private static boolean dfs(int outlet) {
        for (int device : connections[outlet]) {
            if (!used[device]) {
                used[device] = true;
                if (deviceToOutlet[device] == -1 || dfs(deviceToOutlet[device])) {
                    deviceToOutlet[device] = outlet;
                    return true;
                }
            }
        }
        return false;
    }
}
