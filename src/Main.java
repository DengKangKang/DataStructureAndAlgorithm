import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

class Solution {

    public static void main(String[] args) {
        Object o = new Object();
        Solution solution = new Solution();
        System.out.println(solution.threeSum(new int[]{1, 32131, 211321, 3232}));

    }

    private HashSet<List<Integer>> results = new HashSet<>();
    private HashSet<List<Integer>> sums = new HashSet<>();


    private List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        recur(nums, nums.length, 0, new ArrayList<>());
        return new ArrayList<>(results);
    }


    private void recur(int[] nums, int n, int i, ArrayList<Integer> threeSum) {

        if (threeSum.size() == 2) {
            ArrayList<Integer> arrayList = new ArrayList<>(threeSum);
            arrayList.add(i);
            if (!sums.add(arrayList)) return;
        }

        if (threeSum.size() == 3) {
            int sum = 0;
            for (int v : threeSum) {
                sum += v;
            }
            if (sum == 0) {
                results.add(threeSum);
            }
            return;
        }
        if (i == n) {
            return;
        }


        if ((n - i) > (3 - threeSum.size())) {
            ArrayList<Integer> integers = new ArrayList<>(threeSum);
            recur(nums, n, i + 1, integers);
        }


        threeSum.add(nums[i]);
        recur(nums, n, i + 1, threeSum);


    }


}

class Solution2 {

    public static void main(String[] args) {
        boolean valid = new Solution2().isValid("((");
        System.out.println(valid);
    }

    private char[] left = new char[]{'(', '{', '['};
    private char[] right = new char[]{')', '}', ']'};


    private boolean isValid(String s) {
        Stack<Character> stack = new Stack();
        char[] symbol = s.toCharArray();
        for (char c : symbol) {
            if (isLeft(c)) {
                stack.push(c);
            } else {
                Character pop = stack.pop();
                System.out.println(pop);
                if (right[indexOf(pop)] != c) return false;
            }
        }
        return stack.empty();
    }

    private boolean isLeft(char v) {
        for (char c : left) {
            if (c == v) return true;
        }
        return false;
    }

    private int indexOf(char v) {
        for (int i = 0; i < left.length; i++) {
            if (left[i] == v) return i;
        }
        return -1;
    }

}

class Solution3 {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        invertTree(root);
        System.out.println(root.right);

    }

    public static TreeNode invertTree(TreeNode root) {
        recall(root);
        return root;
    }

    private static void recall(TreeNode root) {
        if (root == null) return;
        if (root.left != null && root.right != null) {
            TreeNode tmp = root.left;
            root.left = root.right;
            root.right = tmp;
        }
        recall(root.left);
        recall(root.right);
    }

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

}

class Solution4 {

    public static void main(String[] args) {
        Integer i = null;
        System.out.println(1 > i);
    }

    public boolean isValidBST(TreeNode root) {
        return recall(root, null, null);
    }

    public boolean recall(TreeNode node, Integer minVal, Integer maxVal) {
        if (node == null) return true;
        if (node.left != null && node.right != null) {
            if (node.right.val <= node.val) return false;
            if (maxVal != null && node.right.val >= maxVal) return false;
            if (minVal != null && node.right.val <= minVal) return false;
            if (node.left.val >= node.val) return false;
            if (maxVal != null && node.left.val >= maxVal) return false;
            if (minVal != null && node.left.val <= minVal) return false;
            return recall(node.left, minVal, node.val) && recall(node.right, node.val, maxVal);
        } else if (node.left != null) {
            if (node.left.val >= node.val) return false;
            if (maxVal != null && node.left.val >= maxVal) return false;
            if (minVal != null && node.left.val <= minVal) return false;
            return recall(node.left, minVal, node.val);
        } else if (node.right != null) {
            if (node.right.val <= node.val) return false;
            if (maxVal != null && node.right.val >= maxVal) return false;
            if (minVal != null && node.right.val <= minVal) return false;
            return recall(node.right, node.val, maxVal);
        } else {
            return true;
        }
    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
}

class Solution5 {
    public static void main(String[] args) {
        Solution5 solution5 = new Solution5();
        System.out.println(solution5.minPathSum(new int[][]{}));
    }

    public int minPathSum(int[][] grid) {
        if (grid == null || grid.length == 0) return 0;
        int m = grid.length;
        int n = grid[0].length;
        int[][] sums = new int[m][n];

        sums[0][0] = grid[0][0];
        for (int i = 1; i < m; i++) {
            sums[i][0] = sums[i - 1][0] + grid[i][0];
        }
        for (int i = 1; i < n; i++) {
            sums[0][i] = sums[0][i - 1] + grid[0][i];
        }

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                sums[i][j] = Math.min(sums[i - 1][j], sums[j - 1][i]);
            }
        }

        return sums[m - 1][n - 1];

    }
}

class Solution6 {


    public static void main(String[] args) {
        Solution6 solution6 = new Solution6();
        int[] a = new int[]{10, 9, 2, 5, 3, 7, 101, 18};
        solution6.dynamicPra(a);
    }

    private LinkedList<Integer> longestIncreasing;


    private void recur(int[] a, int i, LinkedList<Integer> increasing) {
        if (longestIncreasing == null || increasing.size() > longestIncreasing.size()) {
            longestIncreasing = new LinkedList<>(increasing);
        }
        if (i >= a.length) return;
        if (!increasing.isEmpty()) {
            System.out.println(increasing + "i: " + i + " last: " + increasing.getLast());

        }
        if (increasing.isEmpty() || increasing.getLast() < a[i]) {
            LinkedList<Integer> newIncresing = new LinkedList<>(increasing);
            increasing.add(a[i++]);
            recur(a, i, newIncresing);
            recur(a, i, increasing);
        } else {
            LinkedList<Integer> newIncreasing = new LinkedList<>();
            newIncreasing.add(a[i++]);
            recur(a, i, newIncreasing);
            recur(a, i, increasing);
        }

    }

    public void dynamicPra(int[] a) {
        int[] dp = new int[a.length];
        dp[0] = 1;
        int maxLength = 0;
        for (int i = 1; i < a.length; i++) {
            for (int j = 0; j < i; j++) {
                int maxVal = 0;
                if (a[i] > a[j]) {
                    maxVal = Math.max(maxVal, dp[j]);
                }
                dp[i] = maxVal + 1;
                maxLength = Math.max(maxLength, dp[i]);
            }
        }

    }
}

class Solution7 {

    //    [[-1],[2,3],[1,-1,-3]]
    public static void main(String[] args) {
        List<List<Integer>> triangle = new ArrayList<>();
        triangle.add(Arrays.asList(-1));
        triangle.add(Arrays.asList(2, 3));
        triangle.add(Arrays.asList(1, -1, -3));
//        triangle.add(Arrays.asList(4, 1, 8, 3));
        System.out.println(minimumTotal(triangle));

    }

    private static int minimumTotal(List<List<Integer>> triangle) {
        int[] values = new int[triangle.size()];
        for (List<Integer> integers : triangle) {
            for (int j = integers.size() - 1; j >= 0; j--) {
                if (j == 0) {
                    values[j] = values[j] + integers.get(j);
                } else if (j == integers.size() - 1) {
                    values[j] = integers.get(j) + values[j - 1];
                } else {
                    values[j] = integers.get(j) + Math.min(values[j], values[j - 1]);
                }
            }
        }
        int minSum = values[0];
        for (int i = 1; i < values.length; i++) {
            if (values[i] < minSum) minSum = values[i];
        }
        return minSum;
    }
}

class Solution8 {

    public static void main(String[] args) {
        Solution8 s = new Solution8();
        System.out.println(s.maxProduct(new int[]{-5, 0, -2, 2}));

    }

    public int maxProduct(int[] nums) {
        if (nums == null || nums.length == 0) return 0;

        int[] dp = new int[nums.length];
        dp[0] = nums[0];

        int minus = nums[0] < 0 ? 1 : 0;

        for (int i = 1; i < nums.length; i++) {
            if (nums[i] < 0) minus++;
            if (nums[i] == 0) minus = 0;
            int product = nums[i];
            int minusCopy = minus;
            if (product < 0) minusCopy--;
            for (int j = i - 1; j >= 0; j--) {
                if (minus % 2 == 0) {
                    if (nums[j] == 0) break;
                    product *= nums[j];
                } else {
                    if (minusCopy == 1 && nums[j] <= 0) break;
                    product *= nums[j];
                    if (nums[j] < 0) {
                        minusCopy--;

                    }
                }
            }
            dp[i] = Math.max(dp[i - 1] * nums[i], product);

        }
        int max = dp[0];
        for (int i = 1; i < dp.length; i++) {
            max = Math.max(max, dp[i]);
        }

        System.out.println(Arrays.toString(dp));
        return max;
    }
}

class Solution9 {

    public static void main(String[] args) {
        Solution9 solution9 = new Solution9();
        System.out.println(solution9.coinChange(new int[]{186, 419, 83, 408}, 6249));
        System.out.println(solution9.dp(new int[]{25, 72, 33}, 15682));
    }

    public int coinChange(int[] coins, int amount) {
        if (coins == null || coins.length == 0) return -1;
        Arrays.sort(coins);
        recur(coins, amount, coins.length - 1, 0, 0);
        return minCount;
    }

    int minCount = -1;

    public void recur(int[] coins, int amount, int i, int count, int sum) {
        if (minCount != -1 && count >= minCount) {
            return;
        }
        if (sum >= amount || i < 0) {
            if (sum == amount) {
                if (minCount == -1) {
                    minCount = count;
                } else {
                    minCount = Math.min(count, minCount);
                }
            }
            return;
        }
        recur(coins, amount, i, count + 1, sum + coins[i]);
        recur(coins, amount, i - 1, count + 1, sum + coins[i]);
        recur(coins, amount, i - 1, count, sum);
    }


    public int dp(int[] coins, int amount) {
        if (coins == null || coins.length == 0) return -1;
        if (amount == 0) return 0;
        boolean[][] dp = new boolean[amount][amount + 1];

        for (int coin : coins) {
            if (coin == amount) return 1;
            else if (coin < amount) dp[0][coin] = true;

        }

        for (int i = 1; i < dp.length; i++) {
            int over = 0;
            int prevSums = 0;
            for (int j = 0; j < dp[i - 1].length; j++) {
                if (dp[i - 1][j]) {
                    ++prevSums;
                    for (int coin : coins) {
                        int sum = coin + j;
                        if (sum < amount) {
                            dp[i][sum] = true;
                        } else if (sum == amount) {
                            return i + 1;
                        } else {
                            ++over;
                        }
                    }
                }
            }
            if (over == prevSums * coins.length) return -1;
        }
        return -1;
    }


}

class Solution10 {

    public static void main(String[] args) {
        Solution10 solution10 = new Solution10();
        System.out.println(solution10.maxProfit(new int[]{1, 3, 0, 1, 1}));
    }

    private int maxProfit(int[] prices) {
        if (prices == null || prices.length == 0) return 0;
        int br = 0;
        int sr = -1;
        int income = 0;

        for (int i = 1; i < prices.length; i++) {
            if (prices[i] < prices[br]) {
                br = i;
            } else if (br > sr || prices[i] > prices[sr]) {
                sr = i;
            }
            if (sr > br) {
                income = Math.max(prices[sr] - prices[br], income);
            }

        }

        return Math.max(0, income);
    }
}

class Solution11 {

    public static void main(String[] args) {

        Solution11 solution11 = new Solution11();
//        int min = solution11.mincostTickets(new int[]{1, 4, 6, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 20, 21, 22, 23, 27, 28}, new int[]{3, 13, 45});
//        int min = solution11.mincostTickets(new int[]{1, 4, 6, 7, 8, 20}, new int[]{7, 2, 15});
//        int min = solution11.mincostTickets(new int[]{1, 2, 3, 4, 6, 8, 9, 10, 13, 14, 16, 17, 19, 21, 24, 26, 27, 28, 29}, new int[]{3, 14, 50});
        int min = solution11.mincostTickets(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 30, 31}, new int[]{2, 7, 15});
        System.out.println(min);
    }

    public int mincostTickets(int[] days, int[] costs) {
        int[] minCost = new int[days.length];
        for (int i = 0; i < days.length; i++) {
            if (i == 0) {
                minCost[i] = minVal(costs[0], costs[1], costs[2]);
            } else {
                int cost = minCost[i - 1] + costs[0];

                int x = 1;
                while (i - (x + 1) >= 0 && days[i] - days[i - x] < 7) {
                    x++;
                }

                int cost1;
                if (i - x == 0 && days[i] - days[0] < 7) {
                    cost1 = costs[1];
                } else {
                    cost1 = minCost[i - x] + costs[1];
                }
                int y = 1;
                while (i - (y + 1) >= 0 && days[i] - days[i - y] < 30) {
                    y++;
                }
                int cost2;
                if (i - y == 0 && days[i] - days[0] < 30) {
                    cost2 = costs[2];
                } else {
                    cost2 = minCost[i - y] + costs[2];
                }

                minCost[i] = minVal(cost, cost1, cost2);
            }
        }
        return minCost[days.length - 1];
    }

    public int minVal(int a, int b, int c) {
        int min = Math.min(a, b);
        return Math.min(min, c);
    }

}

class Solution12 {

    public static void main(String[] args) {
        Solution12 solution = new Solution12();
        System.out.println(solution.myPow(2.1, 3));
    }


    public double myPow(double x, int n) {
        if (x == 0) return 0;
        double result = 1;
        if (n > 0) {
            while (n > 0) {
                result = result * x;
                n--;
            }
        } else {

            while (n < 0) {
                result /= x;
                n++;
            }
        }
        DecimalFormat decimalFormat = new DecimalFormat(".#####");

        return Double.valueOf(decimalFormat.format(result));
    }

    public double myPow2(double x, int n) {
        if (x == 0) return 0;
        long N = n;
        if (N < 0) {
            x = 1 / x;
            N = -N;

        }

        double ans = 1;
        for (long i = 0; i < N; i++) {
            ans *= x;
        }
        DecimalFormat decimalFormat = new DecimalFormat(".#####");
        return Double.valueOf(decimalFormat.format(ans));
    }
}

class Solution13 {

    public static void main(String[] args) {
        Solution13 solution13 = new Solution13();
        int i = solution13.singleNumber(new int[]{2, 1, 3, 4, 5, 6, 1, 2, 7, 5, 4, 6, 7});
        System.out.println(i);

    }

    public int singleNumber(int[] nums) {
        int unMatch = 0;
        for (int i = 0; i < nums.length; i++) {
            for (int j = unMatch; j < i; j++) {
                if (nums[i] == nums[j]) {
                    if (unMatch != j) {
                        int tmp = nums[unMatch];
                        nums[unMatch++] = nums[i];
                        nums[i] = tmp;
                        int tmp2 = nums[unMatch];
                        nums[unMatch++] = nums[j];
                        nums[j] = tmp2;
                    } else {
                        unMatch = unMatch + 1;
                        int tmp = nums[unMatch];
                        nums[unMatch++] = nums[i];
                        nums[i] = tmp;
                    }

                }
            }
        }
        return nums[unMatch];
    }
}

class Solution14 {


    public static void main(String[] args) {
        Solution14 solution14 = new Solution14();
        System.out.println(solution14.subarraySum(new int[]{0, 0, 0}, 0));
    }


    public int subarraySum(int[] nums, int k) {
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            int sum = 0;
            for (int j = i; j >= 0; j--) {
                sum = sum + nums[j];
                if (sum == k) count++;
            }
        }
        return count;
    }

}

class Solution15 {
    public boolean validPalindrome(String s) {
        int low = 0, high = s.length() - 1;
        while (low < high) {
            char c1 = s.charAt(low), c2 = s.charAt(high);
            if (c1 == c2) {
                low++;
                high--;
            } else {
                boolean flag1 = true, flag2 = true;
                for (int i = low, j = high - 1; i < j; i++, j--) {
                    char c3 = s.charAt(i), c4 = s.charAt(j);
                    if (c3 != c4) {
                        flag1 = false;
                        break;
                    }
                }
                for (int i = low + 1, j = high; i < j; i++, j--) {
                    char c3 = s.charAt(i), c4 = s.charAt(j);
                    if (c3 != c4) {
                        flag2 = false;
                        break;
                    }
                }
                return flag1 || flag2;
            }
        }
        return true;
    }
}

class Solution16 {

    private char[] vowel = new char[]{'a', 'e', 'i', 'o', 'u'};

    public int findTheLongestSubstring(String s) {
        int maxLength = 0;
        int[] dp = new int[s.length()];
        char[] chars = s.toCharArray();
        for (int i = 0; i < s.toCharArray().length; i++) {
            if (i == 0) {
                if (isVowel(chars[i])) {
                    dp[i] = 0;
                } else {
                    dp[i] = 1;
                }
            } else {

            }


        }
        return maxLength;
    }

    public boolean isVowel(char c) {
        for (char value : vowel) {
            if (value == c) {
                return true;
            }
        }
        return false;
    }


}

class Solution17 {
    public static void main(String[] args) {
        System.out.println(largestRectangleArea(new int[]{2, 1, 5, 6, 2, 3}));

    }

    public static int largestRectangleArea(int[] heights) {
        int height = 0;
        int with = 0;
        int start = 0;
        for (int i = 0; i < heights.length; i++) {
            if (i == 0) {
                height = heights[0];
                with = 1;
                start = 0;
            } else {
                int area0 = 0;
                if (start + with - 1 == i) {
                    area0 = (with + 1) * height;
                }
                int nWith = 0;
                int nHeight = 0;
                int minHeight = 0;
                int minWith = 0;
                for (int j = i; j >= 0; j--) {
                    if (j == i) {
                        nHeight = heights[j];
                        minHeight = nHeight;
                        nWith = 1;
                    } else {
                        if (heights[j] < minHeight) {
                            minHeight = heights[j];
                            minWith = i - j + 1;
                            if (minHeight * minWith > nWith * nHeight) {
                                nWith = with + 1;
                                nHeight = minHeight;
                            }
                        } else {
                            nWith += 1;
                        }

                    }
                }
                if (nWith * nHeight > with * height && nWith * nHeight > area0) {
                    with = nWith;
                    height = nHeight;
                    start = i - (with - 1);
                } else if (start + with - 1 == i) {
                    with += 1;
                }
            }
        }
        return with * height;
    }
}



