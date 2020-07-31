package algorithm;

public class LevenshteinDistance {


    public static void main(String[] args) {
        System.out.println(dynamicProgramming());
    }

    private static int minDistance = Integer.MAX_VALUE;

    private static char[] a = "mitcmu".toCharArray();
    private static char[] b = "mtacnu".toCharArray();

    private static final int n = 6;
    private static final int m = 6;

    private static void recall(int i, int j, int distance) {

        if (i == n || j == m) {
            if (i < n) distance += (n - i);
            if (j < m) distance += (m - j);
            if (distance < minDistance) minDistance = distance;
            return;
        }

        if (a[i] == b[i]) {
            recall(i + 1, j + 1, distance);
        } else {
            recall(i + 1, j + 1, distance + 1);
            recall(i + 1, j, distance + 1);
            recall(i, j + 1, distance + 1);
        }

    }


    private static int[][] minDistances = new int[n][m];

    private static int dynamicProgramming() {

        for (int j = 0; j < m; j++) {
            if (a[0] == b[j]) minDistances[0][j] = j;
            else if (j != 0) minDistances[0][j] = minDistances[0][j - 1] + 1;
            else minDistances[0][j] = 1;
        }

        for (int i = 0; i < n; i++) {
            if (a[i] == b[0]) minDistances[i][0] = i;
            else if (i != 0) minDistances[i][0] = minDistances[i - 1][0] + 1;
            else minDistances[i][0] = 1;
        }

        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {
                if (a[i] == b[j])
                    minDistances[i][j] = min(minDistances[i - 1][j] + 1, minDistances[i - 1][j - 1] + 1, minDistances[i - 1][j - 1]);
                else
                    minDistances[i][j] = min(minDistances[i - 1][j] + 1, minDistances[i - 1][j - 1] + 1, minDistances[i - 1][j - 1] + 1);

            }
        }
        return minDistances[n - 1][m - 1];
    }


    private static int min(int x, int y, int z) {
        int minv = Integer.MAX_VALUE;
        if (x < minv) minv = x;
        if (y < minv) minv = y;
        if (z < minv) minv = z;
        return minv;
    }


}
