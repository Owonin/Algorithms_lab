package Main;

import java.util.Arrays;

public class BinarySearch{


    public static void search(int[] array, int first, int last, int item) {
        int comparisonCount = 1;

        for(int position = (first + last) / 2 ; array[position]!=item && first <= last; position = (first + last) / 2) {
            ++comparisonCount;
            if (array[position] > item) {
                last = position - 1;
            } else {
                first = position + 1;
            }
        }
        if (first <= last) {
            System.out.println("Бинарный поиск. Индификатор найден. Количество проверок: " + comparisonCount);
        } else {
            System.out.println("Бинарный поиск. Индификатор не найден. Количество проверок: " + comparisonCount);
        }

    }

}
