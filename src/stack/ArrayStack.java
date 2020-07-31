package stack;

import java.util.Arrays;
import java.util.Stack;

public class ArrayStack {

    private String[] items;
    private int capacity;
    private int count;

    ArrayStack(int capacity) {

        items = new String[capacity];
        this.capacity = capacity;
    }

    void push(String item) {
        if (count == capacity) {
            String[] newItems = new String[count * 2];
            System.arraycopy(items, 0, newItems, 0, items.length);
            items = newItems;
        }
        items[count] = item;
        count++;
    }

    String pop() {
        if (count == 0) return null;
        String item = items[--count];
        items[count] = null;
        return item;
    }

    @Override
    public String toString() {
        return Arrays.toString(items);
    }
}
