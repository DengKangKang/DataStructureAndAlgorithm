package array;


import java.util.ArrayList;
import java.util.Objects;

public class Array<T> {

    private Object[] items;
    private int capacity;
    private int count;

    Array(int capacity) {
        items = new Object[capacity];
        this.capacity = capacity;
    }


    public void add(Object item) {
        if (count == capacity) {
            capacity = capacity * 2;
            Object[] newItems = new Object[capacity];
            System.arraycopy(items, 0, newItems, 0, items.length);
            items = newItems;
        }
        items[count++] = item;
    }

}
