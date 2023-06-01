package com.interview.base;

/**
 * @Author qcl
 * @Description
 * @Date 12:10 PM 6/1/2023
 */
public class QuickSort {
    public static void main(String[] args) {
        int[] arr = {9, 2, 5, 1, 1000, 3, 7, 4, 6};
        int max = findMaxValue(arr);
        System.out.println("最大值为: " + max);
    }

    public static int findMaxValue(int[] arr) {
        quickSort(arr, 0, arr.length - 1);
        return arr[arr.length - 1];
    }

    public static void quickSort(int[] arr, int low, int high) {
        if (low < high) {
            int pivotIndex = partition(arr, low, high);
            quickSort(arr, low, pivotIndex - 1);
            quickSort(arr, pivotIndex + 1, high);
        }
    }

    public static int partition(int[] arr, int low, int high) {
        int pivot = arr[high];
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (arr[j] <= pivot) {
                i++;
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        int temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;
        return i + 1;
    }
}

