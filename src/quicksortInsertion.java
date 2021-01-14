import java.util.concurrent.*;
import java.util.Random;
//Peter Massarello 10/28/20
/*--
The purpose of the programming assignment is to perform empirical comparative
analysis of several modifications of Quicksort for integer arrays.
--*/
public class quicksortInsertion {
    public static final int N_1 = 1000, N_2 = 10000, N_3 = 100000;

    public static void swap(int[] array, int a, int b) {
        int temp = array[a];
        array[a] = array[b];
        array[b] = temp;
    }

    public static void insertionSort(int[] array, int low, int n) {// standard swap function
        for(int i = low + 1; i <= n; i++) {
            int value = array[i];
            int j = i;
            while(j > low && array[j - 1] > value) {
                array[j] = array[j - 1];
                j--;
            }
            array[j] = value;
        }
    }

    public static int lomutoPartition(int[] array, int low, int high) {//Partition taken from textbook
        int pivot = array[high];
        int i = (low - 1);

        for(int j = low; j <= high-1; j++) {
            if(array[j] <= pivot) {
                i++;
                swap(array, i, j);
            }
        }
        swap(array, i+1, high);
        return (i+1);
    }

    public static void quickSortNew(int[] array, int low, int high, int num) {//Quicksort using lomutos partitioning then switching to
        while(low < high) {
            if(high - low <= num) {
                insertionSort(array, low, high);
                break;
            }
            else {
                int p = lomutoPartition(array, low, high);
                if(p - low < high - p) {
                    quickSortNew(array, low, high, num);
                    low = p + 1;
                }
                else {
                    quickSortNew(array, low, high, num);
                    high = p - 1;
                }
            }
        }


    }

    public static void printArray(int[] array) {
        for(int i : array) {
            System.out.print(i + " ");
        }
        System.out.println();
    }

    public static void getRandomArray(int[] array, int size) {//initializes array full of random numbers between 1-10000
        Random rand = new Random();
        int ceiling = 10001, randInt = 0, arrSize = size;
        for(int i = 0; i < arrSize; i++) {
            randInt = rand.nextInt(ceiling);
            array[i] = randInt;
        }
    }

    public static void getSortedArray(int[] array, int size) {//initializes array full of sorted numbers going from 1-(n-1)
        int arrSize = size;
        for(int i = 0; i < arrSize; i++) {
            array[i] =  i;
        }
    }

    public static void getPartialSortedArray(int[] array, int size) {//initializes array full of partially sorted numbers
        Random rand = new Random();
        int ceiling = 10001;
        for(int i = 0; i < size; i++) {
            if((i % 10) == 9) {
                int randInt = rand.nextInt(ceiling);
                array[i] = randInt;
            }
            else {
                array[i] = i + 1;
            }
        }
    }

    public static void runFunctionRand(int[] array, int size, int num) {//Main driver function for random number sort 1000 numbers
        getRandomArray(array, size);

        long startTime = System.nanoTime();
        quickSortNew(array, 0, size-1, num);
        long endTime = System.nanoTime();
        long elapsedTime = endTime - startTime;

        System.out.println("Time taken to sort random array size of " + size + " is - " + elapsedTime);
    }

    public static void runFunctionSorted(int[] array, int size, int num) {//Main driver function for sorted number sort 1000 numbers
        getSortedArray(array, size);

        long startTime = System.nanoTime();
        quickSortNew(array, 0, size-1, num);
        long endTime = System.nanoTime();
        long elapsedTime = endTime - startTime;

        System.out.println("Time taken to sort sorted array size of " + size + " is - " + elapsedTime);
    }

    public static void runFunctionPartialSort(int[] array, int size, int num) {//Main driver function for partial number sort 1000 numbers
        getPartialSortedArray(array, size);

        long startTime = System.nanoTime();
        quickSortNew(array, 0, size-1, num);
        long endTime = System.nanoTime();
        long elapsedTime = endTime - startTime;

        System.out.println("Time taken to sort partially sorted array size of " + size + " is - " + elapsedTime);
    }

    public static void main(String args[]) {
        int size1 = 1000, size2 = 10000, size3 = 100000;
        int[] array_rand_1 = new int[size1];
        int[] array_rand_2 = new int[size2];
        int[] array_rand_3 = new int[size3];

        int[] array_sorted_1 = new int[size1];
        int[] array_sorted_2 = new int[size2];
        int[] array_sorted_3 = new int[size3];

        int[] array_partialsorted_1 = new int[size1];
        int[] array_partialsorted_2 = new int[size2];
        int[] array_partialsorted_3 = new int[size3];

        runFunctionRand(array_rand_1, size1, N_1);
        runFunctionRand(array_rand_2, size2, N_2);
        runFunctionRand(array_rand_3, size3, N_3);

        runFunctionSorted(array_sorted_1, size1, N_1);
        runFunctionSorted(array_sorted_2, size2, N_2);
        runFunctionSorted(array_sorted_3, size3, N_3);

        runFunctionPartialSort(array_partialsorted_1, size1, N_1);
        runFunctionPartialSort(array_partialsorted_2, size2, N_2);
        runFunctionPartialSort(array_partialsorted_3, size3, N_3);

    }
}
