package Main;
import java.util.*;
public class Chains {

    private final int MAXarray_size = 32;
    private final LinkedList[] list = new LinkedList[MAXarray_size];
    private  final int[] HashTable;
    private  int array_size;

    Chains(int[] array) {
        HashTable = new int[MAXarray_size];
        array_size = array.length;
        for(int i=0 ; i< MAXarray_size; i++){
            list[i] = new LinkedList<Integer>();
        }
        ResetToZero();

        for(int i = 0; i < array_size; ++i) {
            if(HashTable[indexOfHashTable(array[i])]!=0)
                list[indexOfHashTable(array[i])].add(array[i]);
            else
                HashTable[indexOfHashTable(array[i])] = array[i];
        }

    }

    public void add(int number){
        if(HashTable[indexOfHashTable(number)]!=0)
            list[indexOfHashTable(number)].add(number);
        else
            HashTable[indexOfHashTable(number)] = number;
        array_size++;
    }
    public void delete(int number){
        if(HashTable[indexOfHashTable(number)]!=0)
            list[indexOfHashTable(number)].remove((Integer) 12);
        else
            HashTable[indexOfHashTable(number)] = 0;
        array_size--;
    }

    private int indexOfHashTable(int num) {
        return (num) % this.MAXarray_size;
    }

    private void ResetToZero() {
        for(int i = 0; i < this.MAXarray_size; ++i) {
            this.HashTable[i] = 0;
            list[i].clear();
        }

    }




    public boolean listSearch(int num){
        return list[indexOfHashTable(num)].contains(num);
    }
    
    public int listNumberLocationIndex(int num){
        return list[indexOfHashTable(num)].indexOf(num)+1;
    }

    public void search(int num) {
        boolean flag = false;
        int comparisonCount=0;
        if (HashTable[indexOfHashTable(num)]!=num) {
            flag = listSearch(num);
            comparisonCount+= listNumberLocationIndex(num)+1;
            }
        if(HashTable[indexOfHashTable(num)]==num) {
            flag = true;
            comparisonCount++;
        }


        if (flag) {
            System.out.println("Поиск по хеш-таблице, с рехешированием методом цепочек. Индификатор найден. Количество проверок: " + comparisonCount);
        } else {
            System.out.println("Поиск по хеш-таблице, с рехешированием методом цепочек. Индификатор не найден. Количество проверок: " + comparisonCount);
        }
    }

    public  void see(){
        for(int i=0; i<32; i++){
            System.out.println(HashTable[i]);
            for(int j=0 ; j<list[i].size(); j++)
                System.out.print("->"+list[i].get(j)+" ");
        }
    }


}