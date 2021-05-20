package Main;

public class RandomHash {

    private final int MAXarray_size = 32;
    private int[] additionalArray;
    private final int[][] HashTable;
    private int array_size;

    RandomHash() {
        HashTable = new int[MAXarray_size][2];
        array_size = 0;
        setAdditionalArray();
    }

    private void setAdditionalArray(){
        additionalArray = new int[MAXarray_size];
        for(int i = 0; i < 31; ++i) {
            this.additionalArray[i] = (int)(Math.random() * 100.0D);
        }
    }

    private int indexOfHashTable(int key, int comparisonsCount) {
        return (key + additionalArray[comparisonsCount]) % this.MAXarray_size;
    }

    public void add(int key, int number){
        int comparisonsCount = 0;
        if(HashTable[indexOfHashTable(key,comparisonsCount)][0]==0) {
            HashTable[indexOfHashTable(key,comparisonsCount)][1]= number;
            HashTable[indexOfHashTable(key,comparisonsCount)][0]= key;
        }
        else {
            for(; HashTable[indexOfHashTable(key, comparisonsCount)][0]!=0; ++comparisonsCount) {
            }
            HashTable[indexOfHashTable(key,comparisonsCount)][1]= number;
            HashTable[indexOfHashTable(key,comparisonsCount)][0]= key;
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
                HashTable[indexOfHashTable(key,comparisonCount)][1]= 0;
                array_size--;
            }
        }
        if (!flag)
            System.out.println("Элемент не найден");

    }


    public void search(int key) {
        boolean flag = false;

        int comparisonCount;
        for(comparisonCount = 0; !flag && comparisonCount < this.array_size
                && HashTable[indexOfHashTable(key, comparisonCount)][0]!=0; ++comparisonCount) {
            if (HashTable[this.indexOfHashTable(key, comparisonCount)][0]==key) {
                flag = true;
                System.out.println("Поиск по хеш-таблице, с рехешированием псевдослучайными числами. Индификатор равен: "+HashTable[indexOfHashTable(key, comparisonCount)][1]+". Количество проверок: " + comparisonCount+1);
            }
        }

        if (!flag) {
            System.out.println("Поиск по хеш-таблице, с рехешированием псевдослучайными числами. Индификатор не найден. Количество проверок: " + comparisonCount);
        }

    }


}
