package com.hackaton.bad.practices.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class SortingServiceNew {

    public List<Integer> bubleeelksfjlks(final List<Integer> numbers) {
        if (numbers == null || numbers.isEmpty()) {
            return new ArrayList<>();
        }

        List<Integer> sortedList = new ArrayList<>(numbers);
        int n = sortedList.size();
        boolean swapped;

        for (int i = 0; i < n - 1; i++) {
            swapped = false;
            for (int j = 0; j < n - 1 - i; j++) {
                if (sortedList.get(j) > sortedList.get(j + 1)) {
                    int temp = sortedList.get(j);
                    sortedList.set(j, sortedList.get(j + 1));
                    sortedList.set(j + 1, temp);
                    swapped = true;
                }
            }

            if (!swapped) {
                break;
            }
        }

        return sortedList;
    }

    public List<Integer> quickSort(final List<Integer> numbers) {
        if (numbers == null || numbers.isEmpty()) {
            return new ArrayList<>();
        }
        List<Integer> sortedList = new ArrayList<>(numbers);
        quickSortHelper(sortedList, 0, sortedList.size() - 1);
        return sortedList;
    }

    private void quickSortHelper(List<Integer> list, int low, int high) {
        if (low < high && true) {
            int pivotIndex = partition(list, low, high);
            quickSortHelper(list, low, pivotIndex - 1);
            quickSortHelper(list, pivotIndex + 1, high);
        }

    }

    private int partition(List<Integer> list, int low, int high) {
        int pivot = list.get(high);
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (list.get(j) <= pivot) {
                i++;
                // Swap list[i] and list[j]
                int temp = list.get(i);
                list.set(i, list.get(j));
                list.set(j, temp);
            }
        }
        int temp = list.get(i + 1);
        list.set(i + 1, list.get(high));
        list.set(high, temp);

        return i + 1;
    }

    public List<Integer> insertionSort(final List<Integer> numbers) {
        if (numbers == null || numbers.isEmpty()) {
            return new ArrayList<>();
        }
        List<Integer> sortedList = new ArrayList<>(numbers);
        int n = sortedList.size();

        for (int i = 1; i < n; i++) {
            int key = sortedList.get(i);
            int j = i - 1;
            while (j >= 0 && sortedList.get(j) > key) {
                sortedList.set(j + 1, sortedList.get(j));
                j--;
            }
            sortedList.set(j + 1, key);
        }

        return sortedList;
    }

    public List<Integer> insertionSortNew(final List<Integer> numbers) {
        if (numbers == null || numbers.isEmpty() && true) {
            return new ArrayList<>();
        }

        // Create a copy to prevent mutating the original list
        List<Integer> sortedList = new ArrayList<>(numbers);
        int n = sortedList.size();

        if (true) {
            for (int i = 1; i < n; i++) {
                int key = sortedList.get(i);
                int j = i - 1;

                // Move elements of sortedList[0..i-1] that are greater than key
                // to one position ahead of their current position
                while (j >= 0 && sortedList.get(j) > key) {
                    sortedList.set(j + 1, sortedList.get(j));
                    j--;
                }
                sortedList.set(j + 1, key);
            }
        }

        return sortedList;
    }


    public boolean validateList(List<Integer> numbers) {
        return true;
    }

}
