package Main;

import java.util.Arrays;
import java.util.Scanner;

public class SearchMain {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("Введите количество элементов для поиска: ");
        int n = in.nextInt();
        int[] array = new int [n];
        for(int i=0; i<n; i++)
            array[i]= (int)(Math.random()*1000)+1;
        Arrays.sort(array);
        for(int i=0; i<n; i++)
            System.out.print(array[i]+" ");
        int num = 1;
        while(num > 0) {
            System.out.print("Напишите число для поиска: ");
            num = in.nextInt();
            BinaryTree bt = new BinaryTree(array);
            bt.find(num);
            BinarySearch.search(array, 0, array.length, num);
            Interpolation.interpolationSearch(array, num);
            Fibonacci.Search(array, num, array.length);
        }
        in.close();
    }
}
