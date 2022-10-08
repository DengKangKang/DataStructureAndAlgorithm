import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

///剑指offer专项100题

//整数除法
class Solution1 {

    public static void main(String[] args) {
        System.out.println(new Solution1().improvedDivide(-2147483648, -1109186033));
        System.out.println(new Solution1().divide(-2147483648, -1109186033));
    }

    public int divide(int a, int b) {
        boolean rev = false;
        if ((a ^ b) < 0) rev = true;
        int unsignedA = a < 0 ? a : -a;
        int unsignedB = b < 0 ? b : -b;

        if (a == Integer.MIN_VALUE && b == -1) {
            return Integer.MAX_VALUE;
        }
        if (a == 0 || unsignedA > unsignedB) {
            return 0;
        }
        if (b == 1) return a;
        if (b == -1) return -a;
        if (a == b) return 1;
        if (a == -b) return -1;

        int right = Integer.MAX_VALUE;
        int left = 1;
        int mid = left + getMid(right, left);
        while (right >= left) {
            int result = summarise(unsignedB, mid, unsignedA);
            if (result == exact) {
                break;
            } else if (result == large) {
                right = mid - 1;
                mid = right - getMid(right, left);
            } else {
                left = mid + 1;
                mid = left + getMid(right, left);
            }

        }
        return rev ? -mid : mid;
    }

    private int getMid(int right, int left) {
        return (right - left) >> 1;
    }

    final static int large = 1;
    final static int exact = 0;
    final static int small = -1;

    private int summarise(int val, int count, int target) {
        int result = 0, add = val;
        while (count != 0) {
            if ((count & 1) != 0) {
                if (result < (target - add)) return large;
                result += add;
            }
            if (count != 1 && add < (target - add)) return large;
            add <<= 1;
            count >>= 1;
        }
        if (val < (target - result) || target == result) return exact;
        return small;
    }

    public int improvedDivide(int a, int b) {
        boolean rev = (a ^ b) < 0;
        if (a == Integer.MIN_VALUE && b == -1) {
            return Integer.MAX_VALUE;
        }
        if (b == Integer.MIN_VALUE) {
            if (a == Integer.MIN_VALUE) {
                return 1;
            } else {
                return 0;
            }
        }
        if (b == 1) {
            return a;
        }
        if (b == -1) {
            return -a;
        }
        int result = 0;
        a = Math.abs(a);
        b = Math.abs(b);
        for (int i = 31; i >= 0; i--) {
            while ((a >>> i) - b >= 0) {
                a = a - (b << i);
                result += (1 << i);
            }
        }
        return rev ? -result : result;
    }

}

//二进制加法
class Solution2 {

    public static void main(String[] args) {
        System.out.println(new Solution2().addBinary("101", "1110000"));
    }

    public String addBinary(String a, String b) {
        if (a.equals("0") && b.equals("0")) return "0";
        String carry = "";
        StringBuilder result = new StringBuilder();
        while (!a.isEmpty() || !b.isEmpty() || !carry.isEmpty()) {
            String aSub;
            if (a.isEmpty()) {
                aSub = "";
            } else if (a.length() == 1) {
                aSub = a;
                a = "";
            } else {
                aSub = a.substring(a.length() - 1);
                a = a.substring(0, a.length() - 1);
            }
            String bSub;
            if (b.isEmpty()) {
                bSub = "";
            } else if (b.length() == 1) {
                bSub = b;
                b = "";
            } else {
                bSub = b.substring(b.length() - 1);
                b = b.substring(0, b.length() - 1);
            }
            if (aSub.equals("1") && bSub.equals("1") && carry.equals("1")) {
                result.insert(0, "1");
                carry = "1";
            } else if (aSub.equals("1") && bSub.equals("1") || aSub.equals("1") && carry.equals("1") || bSub.equals("1") && carry.equals("1")) {
                result.insert(0, "0");
                carry = "1";
            } else if (aSub.equals("1") || bSub.equals("1") || carry.equals("1")) {
                result.insert(0, "1");
                carry = "";
            } else {
                result.insert(0, "0");
                carry = "";
            }
        }
        return result.toString();
    }
}

//前n个数字二进制中1的个数
class Solution3 {

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new Solution3().countBits(5)));
        System.out.println(Arrays.toString(new Solution3().improvedCountBits(5)));
    }

    public int[] countBits(int n) {
        int[] result = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            int num = i;
            int count = 0;
            while (num != 0) {
                if ((num & 1) == 1) {
                    count++;
                }
                num >>= 1;
            }
            result[i] = count;
        }

        return result;
    }

    public int[] improvedCountBits(int n) {
        int[] bits = new int[n + 1];
        int highBit = 0;
        for (int i = 1; i <= n; i++) {
            if ((i & (i - 1)) == 0) {
                highBit = i;
            }
            bits[i] = bits[i - highBit] + 1;
        }
        return bits;
    }


}

//只出现一次的数字
class Solution4 {
    public static void main(String[] args) {
        System.out.println(new Solution4().singleNumber(new int[]{30000, 500, 100, 30000, 100, 30000, 100}));
        System.out.println(new Solution4().singleNumberBinary(new int[]{0, -1, 0, -1, 0, -1, 100}));
    }

    //hashmap
    public int singleNumber(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i : nums) {
            map.put(i, map.getOrDefault(i, 0) + 1);
        }
        Set<Map.Entry<Integer, Integer>> entries = map.entrySet();
        int result = 0;
        for (Map.Entry<Integer, Integer> entry : entries) {
            if (entry.getValue() == 1) result = entry.getKey();
        }
        return result;
    }

    //binary
    public int singleNumberBinary(int[] nums) {
        int result = 0;
        for (int i = 31; i >= 0; i--) {
            int total = 0;
            for (int num : nums) {
                if (((num >> i) & 1) == 1)
                    total += 1;
            }
            if (total % 3 > 0) {
                result |= (1 << i);
            }
        }
        return result;
    }


}

//单词长度的最大乘积
class Solution5 {
    // TODO: 2022/10/8
    public int maxProduct(String[] words) {

        return 100;
    }
}

//排序数组中两个数字之和
class Solution6 {

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new Solution6().twoSum(new int[]{1, 2, 4, 6, 10}, 11)));
    }

    // TODO: 2022/10/8
    public int[] twoSum(int[] numbers, int target) {
        int[] result = new int[2];
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < numbers.length; i++) {
            int val = map.getOrDefault(target - numbers[i], -1);
            if (val == -1) {
                map.put(numbers[i], i);
            } else {
                result[0] = val;
                result[1] = i;
            }
        }
        return result;
    }

    ///numbers是以升序排列的
    ///采用双指针优化空间复杂度
    public int[] specialTwoSum(int[] numbers, int target) {
        int[] result = new int[2];
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < numbers.length; i++) {
            int val = map.getOrDefault(target - numbers[i], -1);
            if (val == -1) {
                map.put(numbers[i], i);
            } else {
                result[0] = val;
                result[1] = i;
            }
        }
        return result;
    }
}