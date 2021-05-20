package Main;

import java.util.Arrays;
import java.util.Objects;
class Hash {

    private final int MAXarray_size = 32;
    private final int[][] HashTable;
    private int array_size;

    Hash() {
        HashTable = new int[MAXarray_size][2];
        array_size = 0;
    }


    private int indexOfHashTable(int key, int comparisonsCount) {
        return (key + comparisonsCount) % this.MAXarray_size;
    }

    public void add(int key, int number){
        int comparisonsCount = 0;
        if(HashTable[indexOfHashTable(key,comparisonsCount)][0]==0) {
            HashTable[indexOfHashTable(key, comparisonsCount)][1] = number;
            HashTable[indexOfHashTable(key, comparisonsCount)][0] = key;
        }
        else {
            while (HashTable[indexOfHashTable(key, comparisonsCount)][0]!=0)
                ++comparisonsCount;
            HashTable[indexOfHashTable(key, comparisonsCount)][1] = number;
            HashTable[indexOfHashTable(key, comparisonsCount)][0] = key;
        }
        array_size++;
    }

    public void delete(int key){
        int comparisonCount;
        boolean flag = false;
        for(comparisonCount = 0; !flag && comparisonCount < this.array_size
                && HashTable[indexOfHashTable(key, comparisonCount)][0]!=0; ++comparisonCount) {
            if (this.HashTable[this.indexOfHashTable(key, comparisonCount)][0]==key) {
                flag = true;
                HashTable[indexOfHashTable(key, comparisonCount)][0] = 0;
                HashTable[indexOfHashTable(key, comparisonCount)][1] = 0;
                array_size--;
            }
        }
        if (!flag)
            System.out.println("Элемент не найден");

    }




    public void search(int key) {
        boolean flag = false;

        int comparisonCount;
        for(comparisonCount = 0; !flag && comparisonCount < array_size
                && HashTable[indexOfHashTable(key, comparisonCount)][0]!=0; ++comparisonCount) {
            if (HashTable[indexOfHashTable(key, comparisonCount)][0]==key) {
                flag = true;
                System.out.println("Поиск по хеш-таблице, с простым рехешированием. Индификатор равен: "+HashTable[indexOfHashTable(key, comparisonCount)][1]+". Количество проверок: " + comparisonCount+1);
            }
        }

        if (!flag) {
            System.out.println("Поиск по хеш-таблице, с простым рехешированием. Индификатор не найден. Количество проверок: " + comparisonCount);
        }

    }


}
