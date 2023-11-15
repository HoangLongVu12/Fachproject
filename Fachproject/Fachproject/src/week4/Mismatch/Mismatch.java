package week4.Mismatch;

import java.util.Arrays;
import java.util.Scanner;

public class Mismatch {

    public static void main(String[] args)
    {
        Scanner scr = new Scanner(System.in);
        int n = scr.nextInt();

        int[] list = new int[n+1];

        int duplicate = 0;
        int missing = 0;

        while(scr.hasNext())
        {
            int index = scr.nextInt();

            if(list[index] == 0 )
            {
                list[index] = index;

            }
            else
            {
                duplicate = index;

            }

        }
        for(int i = 1 ; i < list.length ; i++)
        {
            if(list[i] == 0)
            {
                missing = i;
            }
        }
        scr.close();
        System.out.println(missing);
        System.out.println(duplicate);

    }
}
