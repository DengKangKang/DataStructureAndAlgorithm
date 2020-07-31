package heap;

import java.util.Arrays;

public class MinHeap {
    private int[] heap;
    private int capacity;
    private int count = 0;


    public static void main(String[] args) {
        int[] a = {1,2,5,4,7,6,3,9,98,11,3321,3,131,3131,};
        MinHeap heap = new MinHeap(5);
        for (int i : a) {
            if(heap.count <heap.capacity) {
                heap.insert(i);

            }else {
                heap.removeMax();
                heap.insert(i);
            }
        }
        System.out.println(Arrays.toString(heap.heap));

    }

    private MinHeap(int capacity) {
        this.capacity = capacity;
        heap = new int[capacity+1];
    }


    private void insert(int v) {
        if (count >= capacity) return;
        int index = ++count;
        heap[index] = v;
        while (index / 2 > 0 && heap[index / 2] > v) {
            heap[index] = heap[index / 2];
            heap[index / 2] = v;
            index = index / 2;
        }
    }

    private void removeMax() {
        if (count <= 0) return;
        heap[1] = heap[count];
        count--;
        heapify(1);
    }

    private void heapify(int i) {
        while (true) {
            int minPos = i;
            if (i * 2 <= count && heap[i] > heap[i * 2]) minPos = i * 2;
            if (i * 2 + 1 <= count && heap[minPos] > heap[i * 2 + 1]) minPos = i * 2 + 1;
            if (minPos == i) break;

            int tmp = heap[i];
            heap[i] = heap[minPos];
            heap[minPos] = tmp;

            i = minPos;
        }
    }

}
