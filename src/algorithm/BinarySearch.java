package algorithm;

public class BinarySearch {


    //f(a,p,r) = f(a,t,p,q-1) or f(a, t,q+1,r)
    // p>=r
    public static void search(int[] a, int target) {
        if (a == null || a.length == 0) return;
        int index = searchInternally(a, target, 0, a.length - 1);
        System.out.println(index);

    }

    private static int searchInternally(int[] a, int target, int p, int r) {
        if (p >r) return -1;
        int q = p + (r - p) / 2;
        if (a[q] == target) return q;
        if (a[q] > target) {
            return searchInternally(a, target, p, q-1);
        } else {
            return searchInternally(a, target, q + 1, r);
        }

    }

    public static void  search2(){

    }
}
