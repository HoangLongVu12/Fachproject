package week7.compression;

import java.util.*;
public class Compression {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        int anzahl = scanner.nextInt();
        String[] words = new String[anzahl];
        String delete = scanner.nextLine();
        for(int i = 0; i < anzahl; i++){
            words[i] = scanner.nextLine();
        }
        scanner.close();

        int[] result = new int[anzahl];

        for(int i = 0; i < words.length; i++){
            int count = 0;
            for(int k = i; k > 0; k --){
                
                if(words[k-1].startsWith(words[i])){
                    count++;
                }
            }
            result[i] = count;
            count = 0;
        }

        for(int numb : result){
            System.out.println(numb);
        }
        
        
    }
}
