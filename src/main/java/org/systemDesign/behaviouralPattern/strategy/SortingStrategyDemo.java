package org.systemDesign.behaviouralPattern.strategy;

import java.util.Arrays;

public class SortingStrategyDemo {
    public static void main(String[] args) {
        BubbleSortStrategy bubbleSort = new BubbleSortStrategy();
        int[] array = new int[]{3, 6, 9, 1, 5, 4, 2, 9, 8, 6};
        bubbleSort.sort(array);
        System.out.println(Arrays.toString(array));

        SelectionSortStrategy selectionSort = new SelectionSortStrategy();
        int[] array1 = new int[]{3, 6, 9, 1, 5, 4, 2, 9, 8, 6};
        selectionSort.sort(array1);
        System.out.println(Arrays.toString(array));

        InsertionSortStrategy insertionSort = new InsertionSortStrategy();
        int[] array2 = new int[]{3, 6, 9, 1, 5, 4, 2, 9, 8, 6};
        insertionSort.sort(array2);
        System.out.println(Arrays.toString(array));
    }
}
// Strategy Interface
interface SortStrategy {
    void sort(int[] array);
}
// Concrete Strategy 1: Bubble Sort
class BubbleSortStrategy implements SortStrategy {
    @Override
    public void sort(int[] array) {
        System.out.println("Sorting using Bubble Sort");
        // Implement bubble sort logic here
        for(int i=0; i<array.length-1; i++){
            for(int j=0; j< array.length-i-1; j++){
                if(array[j] > array[j+1]){
                    int temp = array[j];
                    array[j] = array[j+1];
                    array[j+1] = temp;
                }
            }
        }
    }
}
// Concrete Strategy 2: Selection Sort
class SelectionSortStrategy implements SortStrategy {
    @Override
    public void sort(int[] array) {
        System.out.println("Sorting using Selection Sort");
        // Implement selection sort logic here
        for(int i=0; i<array.length-1; i++){
            int minIndex = i;
            for(int j=i+1; j<array.length; j++){
                if(array[j] < array[minIndex] ){
                    minIndex = j;
                }
            }
            if(minIndex != i){
                int temp = array[i];
                array[i] = array[minIndex];
                array[minIndex] = temp;
            }
        }
    }
}
// Concrete Strategy 3: Insertion Sort
class InsertionSortStrategy implements SortStrategy {
    @Override
    public void sort(int[] array) {
        System.out.println("Sorting using Insertion Sort");
        // Implement insertion sort logic here
        for(int i=1; i< array.length; i++){
            int temp = array[i], j = i;
            while(j>0 && array[j-1] > temp){
                array[j] = array[j-1];
                j--;
            }
            array[j] = temp;
        }
    }
}
// Concrete Strategy 4: Merge Sort
class MergeSortStrategy implements SortStrategy {
    @Override
    public void sort(int[] array) {
        System.out.println("Sorting using Merge Sort");
        // Implement merge sort logic here
    }
}
// Concrete Strategy 5: Quick Sort
class QuickSortStrategy implements SortStrategy {
    @Override
    public void sort(int[] array) {
        System.out.println("Sorting using Quick Sort");
        // Implement quick sort logic here
    }
}
