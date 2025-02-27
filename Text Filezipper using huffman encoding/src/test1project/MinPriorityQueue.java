/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package test1project;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Mohib
 */
class MinPriorityQueue<T extends Comparable<T>> {
    private int size = 0;
    private List<T> list;

    MinPriorityQueue() {
        list = new ArrayList<>();
    }

    public int len() { return size; }
    public boolean isEmpty() { return size == 0; }
    public T poll() { return poll(0); }

    public void add(T elem) {
        list.add(elem);
        siftUp(size);
        size++;
    }

    private T poll(int i) {
        if(size == 1) {
            size--;
            T data = list.get(0);
            list.clear();
            return data;
        }
        size--;
        T data_to_return = list.get(i);
        swap(i, size);
        list.remove(list.get(size));
        siftDown(i);
        return data_to_return;
    }

    private void siftDown(int k) {
        while(true) {
            int left = (k*2) + 1;
            int right = (k*2) + 2;
            int smallest = left;
            if(right < size) {
                if (isLess(right, left)) smallest = right;
            }
            if(smallest < size) {
                if (isLess(k, smallest)) break;
                swap(k, smallest);
                k = smallest;
            } else break;
        }
    }

    private void siftUp(int k) {
        int parentIndex = (k-1) / 2;
        while(k > 0 && isLess(k, parentIndex)) {
            swap(k, parentIndex);
            k = parentIndex;
            parentIndex = (k-1) / 2;
        }
    }

    private boolean isLess(int i, int j) {
        T elem0 = list.get(i);
        T elem1 = list.get(j);

        return elem0.compareTo(elem1) <= 0;
    }

    private void swap(int i, int j) {
        T elem_i = list.get(i);
        T elem_j = list.get(j);

        list.set(i, elem_j);
        list.set(j, elem_i);
    }

    @Override
    public String toString() {
        return list.toString();
    }
}