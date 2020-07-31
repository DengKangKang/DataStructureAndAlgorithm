package queue;

public class ArrayQueue {


    private int[] array;
    private int head = 0;
    private int tail = 0;
    private int capacity;

    ArrayQueue(int capacity) {
        this.capacity = capacity;
        array = new int[capacity];
    }

    ArrayQueue() {
        capacity = 10;
        array = new int[capacity];
    }

    public boolean enqueue(int v) {
        if ((head - tail) == capacity) {
            return false;
        }
        array[tail] = v;

        tail = (tail + 1) % capacity;
        return true;
    }

    public int pop() {
        if (tail == head) {
            return -1;
        }
        int ret = array[head];
        head = (head + 1) % capacity;
        return ret;
    }

}
