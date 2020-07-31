package heap;

public class MaxHeap {

    private int[] heap;
    private int capacity;
    private int count;

    public MaxHeap(int capacity) {
        this.capacity = capacity;
        heap = new int[capacity+1];
    }


    public void insert(int v) {
        if (count >= capacity) return;
        int index = ++count;
        heap[index] = v;
        while (index / 2 > 0 && heap[index / 2] < v) {
            heap[index] = heap[index / 2];
            heap[index / 2] = v;
            index = index / 2;
        }
    }

    public void removeMax() {
        if (count <= 0) return;
        heap[1] = heap[count];
        count--;
        heapify(1);
    }

    private void heapify(int i) {
        while (true) {
            int maxPos = i;
            if (i * 2 <= count && heap[i] < heap[i * 2]) maxPos = i * 2;
            if (i * 2 + 1 <= count && heap[maxPos] < heap[i * 2 + 1]) maxPos = i * 2 + 1;
            if (maxPos == i) break;

            int tmp = heap[i];
            heap[i] = heap[maxPos];
            heap[maxPos] = tmp;

            i = maxPos;
        }
    }

}
