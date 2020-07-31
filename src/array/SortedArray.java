package array;

import java.util.Arrays;

@SuppressWarnings("all")
public class SortedArray {


    public static void main(String[] args) {
        SortedArray array = new SortedArray(10);
        array.add(7);
        array.add(20);
        array.add(20);
        array.add(6);
        array.add(6);
        array.add(9);
        array.add(9);
        array.add(9);
        array.add(9);
        array.add(8);
        System.out.println(Arrays.toString(array.items));
    }

    private int[] items;
    private int capacity;
    private int count;

    public SortedArray(int capacity) {
        items = new int[capacity];
        this.capacity = capacity;
    }


    public void add(int item) {
        if (count == capacity) {
            return;
        }
        if (count == 0) {
            items[count++] = item;
            return;
        }
        if (item > items[count - 1]) {
            items[count] = item;
            count++;
            return;
        }

        items[count++] = item;
        int j = count - 2;

        for (; j >= 0; j--) {
            if(items[j]>item){
                items[j+1]=items[j];
            }else{
                break;
            }
        }
        items[j+1]= item;
    }

    public void delete(int item) {
        int i = binarySearch(item);
        if (items[i] == item) {
            moveLeft(i);
            count--;
            items[count] = 0;
        }

    }

    private void moveLeft(int index) {
        for (int i = index; i < count; i++) {
            items[i] = items[i + 1];
        }
    }


    private int binarySearch(int v) {
        int middle = 0;
        int left = 0;
        int right = count;
        while (left < right) {
            middle = left + (right - left >> 1);
            if (items[middle] > v) {
                right = middle - 1;
            } else if (items[middle] < v) {
                left = middle + 1;
            } else {
                return middle;
            }
        }
        return middle;
    }


}
