package algorithm;


import java.util.Arrays;

// E(A) = f(A,p,q-1)+f(A,q+1,r)
// 终止条件 p >= r
public class QuickSort {


    public static void quickSort(int[] unSort, int p, int r) {
        //不能再分割则终止递归，排序完成
        if (p >= r) return;
        //取中间点，并且分区
        int q = partition(unSort, p, r);
        //分别对左边和右边区域再次排序
        quickSort(unSort, p, q - 1);
        quickSort(unSort, q + 1, r);
    }


    private static int partition(int[] unSort, int p, int r) {
        //取最后一个元素为中间点
        int pivot = unSort[r];
        //初始化i为区域头p
        int i = p;
        //遍历整个区域最后一个元素前停止
        for (int j = p; j < r; j++) {
            //如果元素小于中间点
            if (unSort[j] < pivot) {
                //移动到以排序区
                if (i == j) {
                    i++;

                } else {
                    int tmp = unSort[i];
                    unSort[i++] = unSort[j];
                    unSort[j] = tmp;
                }
            }
        }


        if (i == r) return i;

        //将中间点放置到中间位置
        unSort[i] = unSort[i] ^ unSort[r];
        unSort[r] = unSort[i] ^ unSort[r];
        unSort[i] = unSort[i] ^ unSort[r];

        //返回中间点
        return i;

    }

}
