package sort;

import java.util.Arrays;

public class MergeSort {

    public static void main(String[] args) {
        MergeSort mergeSort = new MergeSort();
        int[] a = new int[]{3, 2, 1, 5, 8, 9, 6, 4, 11, 10};
        mergeSort.mergeSort(a, 0, a.length - 1);
        System.out.println(Arrays.toString(a));

    }


    private int num = 0;

    //f(a) = f(a,p,q)+ f(a,q+1,r)
    //p>=r;
    private void mergeSort(int[] a, int p, int r) {
        if (p >= r) return;

        int q = p + ((r - p) >> 1);

        mergeSort(a, p, q);
        mergeSort(a, q + 1, r);

        merge(a, p, q, r);

    }

    private void merge(int[] a, int p, int q, int r) {
        int i = p;
        int j = q + 1;
        int[] tmp = new int[r - p + 1];
        int tmpCount = 0;
        while (i <= q && j <= r) {
            if (a[i] <= a[j]) {
                tmp[tmpCount++] = a[i++];
            } else {
                num += (q - i + 1);
                tmp[tmpCount++] = a[j++];
            }
        }


        if (j > r) {
            while (i <= q) {
                tmp[tmpCount++] = a[i++];
            }

        } else {
            while (j <= r) {
                tmp[tmpCount++] = a[j++];
            }
        }
        System.out.println(p);
        System.out.println(q);
        System.out.println(r);
        System.out.println(Arrays.toString(tmp)
        );
        if (tmp.length >= 0) System.arraycopy(tmp, 0, a, p, tmp.length);


    }



}
