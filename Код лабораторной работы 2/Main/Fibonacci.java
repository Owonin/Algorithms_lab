package Main;


import org.w3c.dom.ls.LSOutput;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Objects;

class Fibonacci

{

    public static int min(int x, int y)

    { return Math.min(x, y); }



    public static void Search(int arr[], int toFind, int ArrayLength) {
        int fibMMm2 = 0;
        int fibMMm1 = 1;
        int comparisonCount =0;
        int fibM = fibMMm2 + fibMMm1;

        while (fibM < ArrayLength) {
            fibMMm2 = fibMMm1;
            fibMMm1 = fibM;
            fibM = fibMMm2 + fibMMm1;
            comparisonCount++;
        }

        int offset = -1;

        while (fibM > 1) {
            int i = min(offset+fibMMm2, ArrayLength-1);
            if (arr[i] < toFind)
            {
                fibM = fibMMm1;
                fibMMm1 = fibMMm2;
                fibMMm2 = fibM - fibMMm1;
                offset = i;
                comparisonCount++;
            }
            else if (arr[i] > toFind)
        {
            comparisonCount++;
            fibM = fibMMm2;
            fibMMm1 = fibMMm1 - fibMMm2;
            fibMMm2 = fibM - fibMMm1;
        } else {
                System.out.println("Фиббоначев поиск - индификатор найден. Количество проверок: " + comparisonCount);
                return;
            }
        }
        if(fibMMm1 == 1 && arr[offset+1] == toFind)
            System.out.println("Фиббоначев поиск - индификатор найден. Количество проверок: " + comparisonCount);
        else
            System.out.println("Фиббоначев поиск - индификатор не найден. Количество проверок: " + comparisonCount);

    }


}