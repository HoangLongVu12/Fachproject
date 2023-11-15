package week5.Fishmans;

import java.util.*;

public class Fishmans {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Read input
        int n = scanner.nextInt();
        int money = scanner.nextInt();
        int k = scanner.nextInt();

        HashMap<String, Integer> hashMap = new HashMap<>();

        //SortedMap<String, Integer> sort = new TreeMap<>();
        for(int i = 0; i < n; i++) {
            hashMap.put(scanner.next(),scanner.nextInt());
        }

        List<Map.Entry<String, Integer>> sortedHashmap = new ArrayList<>(hashMap.entrySet());

        Collections.sort(sortedHashmap, Comparator.comparing(Map.Entry::getValue));
        int help = 0;
        int count= 0;
        while( help < k) {
            money -= sortedHashmap.get(sortedHashmap.size()-1-help).getValue();
            count++;
            if(money <= 0) {
                break;
            }
            help++;
        }

        if( money > 0) {
            System.out.println("impossible");
            return;
        } else {
            System.out.println(count);
            for(int i = 0; i < count; i++) {
                System.out.println(sortedHashmap.get(sortedHashmap.size()-i-1).getKey() + ", I'm sorry. Goodbye!");
            }
        }


    }
}
