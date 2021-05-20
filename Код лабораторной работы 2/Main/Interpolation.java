package Main;

import java.util.Arrays;
//

public class Interpolation {
    public Interpolation() {
    }

    public static void interpolationSearch(int[] sortedArray, int toFind) {
        int comparisonCount = 1;
        int low = 0;
        int high = sortedArray.length - 1;

        while(sortedArray[low] < toFind && sortedArray[high] > toFind) {
            comparisonCount++;
            if (sortedArray[high] == sortedArray[low]) {
                break;
            }

            int mid = low + (toFind - sortedArray[low]) * (high - low) / (sortedArray[high] - sortedArray[low]);
            if (sortedArray[mid] < toFind) {
                comparisonCount++;
                low = mid + 1;
            } else if (sortedArray[mid] > toFind) {
                comparisonCount++;
                high = mid - 1;
            } else {
                System.out.println("Интерполяционный поиск - индификатор найден. Количество проверок: " + comparisonCount);
                return;
            }
        }

        if (sortedArray[low] == toFind) {
            System.out.println("Интерполяционный поиск - индификатор найден. Количество проверок: " + comparisonCount);
        }else

        if (sortedArray[high] == toFind) {
            System.out.println("Интерполяционный поиск - индификатор найден. Количество проверок: " + comparisonCount);
        }else

            System.out.println("Интерполяционный поиск - индификатор не найден. Количество проверок: " + comparisonCount);

    }

}
