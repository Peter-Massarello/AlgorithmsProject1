import java.util.concurrent.*;
import java.util.Random;
//Peter Massarello 10/28/20
/*--
The purpose of the programming assignment is to perform empirical comparative
analysis of several modifications of Quicksort for integer arrays.
--*/
public class quicksortMedian {

    public static void swap(int[] array, int a, int b) {// standard swap function
        int temp = array[a];
        array[a] = array[b];
        array[b] = temp;
    }

    public static int partition(int[] array, int low, int high, int piv) {//Partition taken javacodegeeks.com
        int i = low - 1;
        int j = high;
        while(i < j) {
            while(array[++i] < piv);
            while(j > 0 && array[--j] > piv);
            if(i >= j) {
                break;
            }
            else {
                swap(array, i, j);
            }
        }
        swap(array, i, high);
        return i;
    }

    public static int getMedian(int[] array, int low, int high) {//Median taken from javacodegeeks.com
        int median = (low + high)/2;

        if(array[low] > array[median]) {
            swap(array, low, median);
        }
        if(array[low] > array[high]) {
            swap(array, low, high);
        }
        if(array[median] > array[high]) {
            swap(array, median, high);
        }
        swap(array, median, high);
        return array[high];
    }

    public static void quickSortMedian(int[] array, int low, int high) {//Quicksort using lomutos partitioning
        if(low >= high) {
            return;
        }
        int piv = getMedian(array, low, high);// gathers pivot value using median of three
        int p = partition(array, low, high, piv);// partitions based on previous pivot

        quickSortMedian(array, low, p - 1);
        quickSortMedian(array, p + 1, high);

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

    public static void runFunctionRand(int[] array, int size) {//Main driver function for random number sort
        getRandomArray(array, size);

        long startTime = System.nanoTime();
        quickSortMedian(array, 0, size-1);
        long endTime = System.nanoTime();
        long elapsedTime = endTime - startTime;

        System.out.println("Time taken to sort random array size of " + size + " is - " + elapsedTime);
    }

    public static void runFunctionSorted(int[] array, int size) {//Main driver function for sorted number sort
        getSortedArray(array, size);

        long startTime = System.nanoTime();
        quickSortMedian(array, 0, size-1);
        long endTime = System.nanoTime();
        long elapsedTime = endTime - startTime;

        System.out.println("Time taken to sort sorted array size of " + size + " is - " + elapsedTime);
    }

    public static void runFunctionPartialSort(int[] array, int size) {//Main driver function for partial number sort
        getPartialSortedArray(array, size);

        long startTime = System.nanoTime();
        quickSortMedian(array, 0, size-1);
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

        runFunctionRand(array_rand_1, size1);
        runFunctionRand(array_rand_2, size2);
        runFunctionRand(array_rand_3, size3);

        runFunctionSorted(array_sorted_1, size1);
        runFunctionSorted(array_sorted_2, size2);
        runFunctionSorted(array_sorted_3, size3);

        runFunctionPartialSort(array_partialsorted_1, size1);
        runFunctionPartialSort(array_partialsorted_2, size2);
        runFunctionPartialSort(array_partialsorted_3, size3);


    }
}