package heap;

import java.util.Arrays;

public class SortByHeap {

    public static void main(String[] args) {
        SortByHeap sortByHeap = new SortByHeap();
        int[] a = {0, 7, 5, 19, 8, 4, 1, 20, 13, 16};
        sortByHeap.sort(a, a.length - 1);
        System.out.println(Arrays.toString(a));
    }

    private void sort(int[] a, int n) {
        for (int i = n / 2; i > 0; i--) {
            heapify(a, i, n);
        }
        System.out.println(Arrays.toString(a));

        int k = n;

        while (k > 0) {
            int tmp = a[k];
            a[k] = a[1];
            a[1] = tmp;
            heapify(a, 1, --k);
        }
    }

    private void heapify(int[] a, int i, int n) {

        while (true) {
            int maxPos = i;
            if (i * 2 <= n && a[i] < a[i * 2]) maxPos = i * 2;
            if (i * 2 + 1 <= n && a[maxPos] < a[i * 2 + 1]) maxPos = i * 2 + 1;
            if (maxPos == i) return;

            int tmp = a[i];
            a[i] = a[maxPos];
            a[maxPos] = tmp;
            i = maxPos;
        }


    }
}
