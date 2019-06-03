package com.zero.one;

public class SortTheOdd {
    public static int[] sortArray(final int[] array) {

        for (int i = 0; i < array.length - 1; i++) {
            for (int j = i + 1; j < array.length; j++) {
                if (array[i] > array[j] && array[i] % 2 == 1 && array[j] % 2 == 1) {
                    int aux = array[i];
                    array[i] = array[j];
                    array[j] = aux;
                }
            }
        }
        return array;
    }
}

