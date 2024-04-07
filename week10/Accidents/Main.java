package week10.Accidents;
import java.util.*;
import java.util.*;

import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] nm = br.readLine().split(" ");
        int n = Integer.parseInt(nm[0]);
        int m = Integer.parseInt(nm[1]);

        int[] inDegree = new int[n+1];
        for (int i = 0; i < m; i++) {
            String[] ij = br.readLine().split(" ");
            int j = Integer.parseInt(ij[1]);
            inDegree[j]++;
        }

        int max = 0;
        for (int i = 1; i <= n; i++) {
            max = Math.max(max, inDegree[i]);
        }

        System.out.println(max);
    }
}

