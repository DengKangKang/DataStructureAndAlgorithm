import java.util.*;

///剑指offer专项100题

//整数除法
class Solution1 {

    public static void main(String[] args) {
        System.out.println(new Solution1().improvedDivide(-2147483648, -1109186033));
        System.out.println(new Solution1().divide(-2147483648, -1109186033));
    }

    //二分查找法
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

    ///代码实现二进制除法竖式
    ///时、空复杂度皆为O(1)
    ///关键点就是被除数通过右移取高位。
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

    public static void main(String[] args) {
        System.out.println(new Solution5().maxProduct(new String[]{"abcw", "baz", "foo", "bar", "fxyz", "abcdef"}));
        System.out.println(new Solution5().maxProduct(new String[]{"a", "ab", "abc", "d", "cd", "bcd", "abcd"}));
        System.out.println(new Solution5().maxProduct(new String[]{"a", "aa", "aaa", "aaaa"}));
        System.out.println(new Solution5().improveMaxProduct(new String[]{"abcw", "baz", "foo", "bar", "fxyz", "abcdef"}));
        System.out.println(new Solution5().improveMaxProduct(new String[]{"a", "ab", "abc", "d", "cd", "bcd", "abcd"}));
        System.out.println(new Solution5().improveMaxProduct(new String[]{"a", "aa", "aaa", "aaaa"}));
    }

    //暴力求解 时间复杂度大于O(n^2)
    public int maxProduct(String[] words) {
        int width = 0;
        int height = 0;
        for (int i = 0; i < words.length; i++) {
            String x = words[i];
            for (int j = i + 1; j < words.length; j++) {
                String y = words[j];
                if (containSameElement(x, y)) continue;
                if (x.length() * y.length() > width * height) {
                    width = x.length();
                    height = y.length();
                }

            }
        }
        return width * height;
    }

    public boolean containSameElement(String a, String b) {
        boolean result = false;
        char[] aChars = a.toCharArray();
        char[] bChars = b.toCharArray();
        for (char aChar : aChars) {
            for (char bChar : bChars) {
                if (aChar == bChar) {
                    result = true;
                    break;
                }
            }
        }
        return result;
    }

    //单个word只由小写字母组成
    //使用掩码的方式降低比对两个word是否又相同字母的时间复杂度，以达到时间复杂度等于O(n^2)。
    //对每个word生成掩码需要额外的空间复杂度O(n)
    public int improveMaxProduct(String[] words) {
        int product = 0;
        int[] masks = new int[words.length];
        for (int i = 0; i < words.length; i++) {
            int mask = 0;
            char[] chars = words[i].toCharArray();
            for (char aChar : chars) {
                mask |= 1 << aChar - 'a';
            }
            masks[i] = mask;
        }
        for (int i = 0; i < words.length; i++) {
            for (int j = i + 1; j < words.length; j++) {
                if ((masks[i] & masks[j]) == 0) {
                    product = Math.max(product, words[i].length() * words[j].length());
                }
            }
        }
        return product;
    }
}

//排序数组中两个数字之和
class Solution6 {

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new Solution6().twoSum(new int[]{1, 2, 4, 6, 10}, 3)));
        System.out.println(Arrays.toString(new Solution6().specialTwoSum(new int[]{1, 2, 4, 6, 10}, 3)));
    }

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
        int left = 0;
        int right = numbers.length - 1;
        while (right > left) {
            int sum = numbers[right] + numbers[left];
            if (sum > target) {
                right--;
            } else if (sum < target) {
                left++;
            } else {
                break;
            }
        }
        return new int[]{left, right};
    }
}

//数组中和为 0 的三个数
class Solution7 {

    public static void main(String[] args) {
        System.out.println(new Solution7().threeSum(new int[]{-1, 0, 1, 1, 1, 2, 2, 2, -1, -1, -1, -1, -1, -1, -1, -1}));
    }

    ///三重遍历最终需要落到去重，时、空复杂度都不理想。
    ///考虑先排序，这样有效避免了重复问题。
    ///第一层遍历确定第一个数nums[i]后，第二层遍历就变成了twoSum，即nums[j] + nums[k] = -nums[i]。
    // 因为数组是升序排列的所以直接用双指针来解即可。
    // 还要考虑的一个点是第一层还是第二层遍历如果碰到重复的数需要跳过
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            if (i != 0 && nums[i] == nums[i - 1]) continue;
            int left = i + 1;
            int right = nums.length - 1;
            while (right > left) {
                int sum = nums[left] + nums[right];
                if (left != i + 1 && nums[left] == nums[left - 1]) {
                    left++;
                } else if (right != nums.length - 1 && nums[right] == nums[right + 1]) {
                    right--;
                } else if (sum > -nums[i]) {
                    right--;
                } else if (sum < -nums[i]) {
                    left++;
                } else {
                    List<Integer> integers = new ArrayList<>();
                    integers.add(nums[i]);
                    integers.add(nums[left]);
                    integers.add(nums[right]);
                    result.add(integers);
                    left++;
                    right--;
                }
            }
        }
        return result;
    }

}

//和大于等于 target 的最短子数组
class Solution8 {

    public static void main(String[] args) {
        System.out.println(new Solution8().minSubArrayLen(3, new int[]{1, 1, 1,}));
    }

    ///滑动窗口
    public int minSubArrayLen(int target, int[] nums) {
        int left = 0, right = 0, summarise = 0;
        int result = Integer.MAX_VALUE;
        while (right < nums.length) {
            summarise += nums[right++];
            //寻找长传中符合条件的字串；
            while (summarise >= target) {
                ///对子串的头部进行修剪找到符合条件并且长度最小的子串，与后面的字符拼接；
                summarise -= nums[left++];
                result = Math.min(right - left + 1, result);
            }
        }
        return result == Integer.MAX_VALUE ? 0 : result;
    }

}

///剑指 Offer II 012. 左右两边子数组的和相等
class Solution12 {

    public static void main(String[] args) {
        System.out.println(new Solution12().pivotIndex(new int[]{2, 1, -1}));
    }

    public int pivotIndex(int[] nums) {
        int[] summarise = new int[nums.length + 1];
        for (int i = 1; i < summarise.length; i++) {
            summarise[i] += summarise[i - 1] + nums[i - 1];
        }
        for (int i = 1; i < summarise.length; i++) {
            if (summarise[summarise.length - 1] - summarise[i] == summarise[i - 1]) {
                return i - 1;
            }
        }
        return -1;
    }
}