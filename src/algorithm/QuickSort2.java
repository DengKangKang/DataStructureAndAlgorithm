package algorithm;


import java.util.Arrays;

//f(A) = f(A,q,p) + f(A,p+1,r)
//q>=r
public class QuickSort2 {


    public static void main(String[] args) {

        QuickSort2 quickSort = new QuickSort2();
        int[] a = {1, 2, 5,3,6,7,4};
        quickSort.quickSort(a,0,6);
        System.out.println(Arrays.toString(a));

    }

    public void quickSort(int[] a, int q, int r) {

        if (q >= r) return;
        int p = partition(a, q, r);


        quickSort(a, q, p-1);
        quickSort(a, p + 1, r);

    }


    public int partition(int[] a, int q, int r) {
        int pivot = a[r];
        int i = q;

        for (int j = q; j < r; j++) {
            if (a[j] < pivot) {
                if (i == j) {
                    i++;
                } else {
                    int tmp = a[i];
                    a[i++] = a[j];
                    a[j] = tmp;
                    System.out.println(Arrays.toString(a));

                }
            }
        }

        if(i==r){
            return i;
        }
        a[i] = a[i]^a[r];
        a[r] = a[i]^a[r];
        a[i] = a[i]^a[r];

        return i;
    }
}
