package Main;

import java.util.Random;
import java.util.Scanner;

public class MainHash {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("Введите количество элементов для поиска: ");
        int n = in.nextInt();
        int[] array = new int [n];
        for(int i=0; i<n; i++)
            array[i]= (int)(Math.random()*1000)+1;
        for(int i=0; i<n; i++)
            System.out.print(array[i]+" ");
        RandomHash rHash = new RandomHash();
        Hash hash = new Hash();
        hash.add(12,3);
        hash.add(13,5);
        hash.add(14,8);
        hash.add(15,2);
        rHash.add(12,3);
        rHash.add(13,5);
        rHash.add(14,8);
        rHash.add(15,2);
        Chains chains = new Chains(array);
        int num = 1;
        while(num > 0) {
            System.out.print("Напишите число для поиска: ");
            num = in.nextInt();
            rHash.search(num);
            hash.search(num);
            chains.search(num);
        }
    }
}
