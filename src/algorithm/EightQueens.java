package algorithm;

import java.util.Arrays;

public class EightQueens {

    public static void main(String[] args) {
        cal8Queens(0);
    }

    private static int[] a = new int[8];


    private static void cal8Queens(int row) {
        if (row == 8) {
            printQueens(a);
            return;
        }
        a[row] = 0;
        for (int i = 0; i < 8; i++) {
            if (isOk(row, i)) {
                a[row] = i;
                cal8Queens(row + 1);
            }
        }

    }


    private static boolean isOk(int row, int column) {
        if (row == 0) return true;
        for (int i = 0; i < row; i++) {

            int distance = row - i;

            if (a[i] == (column - distance) || a[i] == (column + distance)) {

                return false;
            }
            if (a[i] == column) {
                return false;
            }
        }
        return true;

    }

    private static void printQueens(int[] result) {
        for (int row = 0; row < 8; ++row) {
            for (int column = 0; column < 8; ++column) {
                if (result[row] == column) System.out.print("Q ");
                else System.out.print("* ");
            }
            System.out.println();
        }
        System.out.println();
    }
}
