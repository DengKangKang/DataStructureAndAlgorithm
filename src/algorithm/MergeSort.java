package algorithm;

import java.util.Arrays;

public class MergeSort {

    public static void mergeSort(int[] unSort, int length) {
        mergeSortR(unSort, 0, length - 1);
        System.out.println(Arrays.toString(unSort));
    }

    private static void mergeSortR(int[] unSort, int p, int r) {
        if (p >= r) return;
        //取出中间位置
        int q = p + (r - p) / 2;
        //递归第一部分
        mergeSortR(unSort, p, q);
        //递归第二部分
        mergeSortR(unSort, q + 1, r);

        //合并
        merge(unSort, p, q, r);
    }

    private static void merge(int[] unSort, int p, int q, int r) {
        //第一部分起点为i；
        int i = p;
        //第二部分呢起点为j
        int j = q + 1;
        //临时数组初始位置0
        int k = 0;
        //定义当前长度相同的数组
        int[] tmp = new int[r - p + 1];

        //循环比较两部分的元素并按大小存入临时数组
        while (i <= q && j <= r) {
            if (unSort[i] < unSort[j]) {
                tmp[k++] = unSort[i++];
            } else {
                tmp[k++] = unSort[j++];
            }
        }

        //判断那个部分还有剩余元素
        int start = i;
        int end = q;
        if (j <= r) {
            start = j;
            end = r;
        }


        while (start <= end) {
            tmp[k++] = unSort[start++];
        }


        for (i = 0; i <= r - p; ++i) {
            unSort[p + i] = tmp[i];
        }
    }
}
