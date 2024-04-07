package week5.Fishmans;

import java.util.*;

public class Fishmans {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Read input
        int n = scanner.nextInt();
        int money = scanner.nextInt();
        int k = scanner.nextInt();

        Map<String, Integer> hashMap = new LinkedHashMap<>();

        for(int i = 0; i < n; i++) {
            hashMap.put(scanner.next(),scanner.nextInt());
        }
        List<Map.Entry<String, Integer>> sortedHashmap = new ArrayList<>(hashMap.entrySet());

        Collections.sort(sortedHashmap, Comparator.comparing(Map.Entry::getValue));
        int count= 0;
        for( int i = 0;i < k; i++) {
            money -= sortedHashmap.get(sortedHashmap.size()-1-i).getValue();
            count++;
            if(money <= 0) {
                break;
            }
        }

        if( money > 0) {
            System.out.println("impossible");
        } else {
            System.out.println(count);
            List<String> keys = new ArrayList<>(hashMap.keySet());
            for(int i = 0; i < n; i++) {
                for(int j = n-1; j > n - count - 1; j--) {
                    if(Objects.equals(keys.get(i), sortedHashmap.get(j).getKey())) {
                        System.out.println(keys.get(i) + ", I'm sorry. Goodbye!");
                    }
                }
            }
        }


    }
}