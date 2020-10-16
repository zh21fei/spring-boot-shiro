package com.neo.lambda;

import java.util.*;

/**
 * @author zhangpengfei
 * @since 2020/7/14
 */
public class Main {
    public static void main(String[] args) {
        //System.out.println(longestPalindrome("cbbd"));

        /*int[] numbers = {2, 7, 11, 15};
        System.out.println(Arrays.toString(twoSum(numbers, 9)));*/

        //System.out.println(reverse(964632351));

        //System.out.println(isPalindrome2(12345));

        //System.out.println(isValid("()[]{}"));

        //System.out.println(strStr("aaaaa", "bba"));

        System.out.println(romanToInt("MCMXCIV"));

    }


    /**
     * 392.判断子序列（判断S是否为t的子序列）
     *
     * @param s 子序列
     * @param t 原始字符串
     * @return true or false
     */
    public static boolean isSubsequence(String s, String t) {
        // 在t 中逐个查s
        int index = -1;
        for (char c : s.toCharArray()) {
            index = t.indexOf(c, index + 1);
            if (index == -1) {
                return false;
            }
        }
        return true;
    }

    /**
     * I             1
     * V             5
     * X             10
     * L             50
     * C             100
     * D             500
     * M             1000
     *
     * @param s
     * @return
     */
    public static int romanToInt(String s) {
        if (s.length() == 0) {
            return 0;
        }
        char[] arr = s.toCharArray();
        HashMap<Character, Integer> map = new HashMap<>();
        map.put('I', 1);
        map.put('V', 5);
        map.put('X', 10);
        map.put('L', 50);
        map.put('C', 100);
        map.put('D', 500);
        map.put('M', 1000);
        int result = 0;
        /*for (int i = 0; i < arr.length; i++) {
            char c = arr[i];
            result += map.get(c);
            if (i > 0 && (map.get(arr[i])) > (map.get(arr[i - 1]))) {
                // 减去前一个值的二倍 是因为前面操作加了一次
                result -= map.get(arr[i - 1]) * 2;
            }
        }*/
        Integer preNum = map.get(s.charAt(0));
        for (int i = 1; i < arr.length; i++) {
            int num = map.get(arr[i]);
            if (preNum < num) {
                result -= preNum;
            } else {
                result += preNum;
            }
            preNum = num;
        }
        result += preNum;
        return result;
    }

    public static int strStr(String haystack, String needle) {
        if (needle == null) {
            return 0;
        }
        if (haystack.contains(needle)) {
            return haystack.indexOf(needle);
        }
        return -1;
    }

    public static void cheng() {
        for (int i = 1; i <= 9; i++) {
            for (int j = 1; j <= i; j++) {
                System.out.print(j + " * " + i + " = " + i * j + "  ");
            }
            System.out.println();
        }
    }

    public static boolean isValid(String s) {
        if (s == null || s.length() % 2 == 0) {
            return false;
        }
        // 建立一个栈，循环遍历比较
        Stack<Character> characters = new Stack<>();
        for (char c : s.toCharArray()) {
            if (c == '(') {
                characters.push(')');
            } else if (c == '{') {
                characters.push('}');
            } else if (c == '[') {
                characters.push(']');
            } else if (characters.isEmpty() || c != characters.pop()) {
                return false;
            }
        }
        return characters.isEmpty();
    }

    public static int searchInsert(int[] nums, int target) {
        /*for (int i = 0; i < nums.length; i++) {
            if (nums[i] >= target){
                return i;
            }
        }
        return nums.length;*/

        // 二分法查找
        int start = 0;
        int end = nums.length - 1;
        while (start <= end) {
            int middle = (start + end) / 2;
            if (nums[middle] > target) {
                end = middle - 1;
            } else if (nums[middle] < target) {
                start = middle + 1;
            } else {
                return middle;
            }
        }
        return start;
    }

    // 暴力解法
    public static String longestPalindrome(String s) {
        String ans = "";
        int max = 0;
        for (int i = 0; i < s.length(); i++)
            for (int j = i + 1; j <= s.length(); j++) {
                String substring = s.substring(i, j);
                if (isPalindromic(substring) && substring.length() > max) {
                    ans = substring;
                    max = Math.max(max, ans.length());
                }
            }
        return ans;
    }

    public static boolean isPalindromic(String s) {
        for (int i = 0; i < s.length() / 2; i++) {
            if (s.charAt(i) != s.charAt(s.length() - i - 1)) {
                return false;
            }
        }
        return true;
    }

    public static String longestPalindrome2(String s) {
        int len = s.length();
        if (len < 2) {
            return s;
        }

        int maxLen = 1;
        int begin = 0;
        // s.charAt(i) 每次都会检查数组下标越界，因此先转换成字符数组
        char[] charArray = s.toCharArray();

        // 枚举所有长度大于 1 的子串 charArray[i..j]
        for (int i = 0; i < len - 1; i++) {
            for (int j = i + 1; j < len; j++) {
                if (j - i + 1 > maxLen && validPalindromic(charArray, i, j)) {
                    maxLen = j - i + 1;
                    begin = i;
                }
            }
        }
        return s.substring(begin, begin + maxLen);
    }

    /**
     * 验证子串 s[left..right] 是否为回文串
     */
    private static boolean validPalindromic(char[] charArray, int left, int right) {
        while (left < right) {
            if (charArray[left] != charArray[right]) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }

    public static int[] twoSum(int[] numbers, int target) {
        /*int[] index = new int[2];
        for (int i = 0; i < numbers.length; i++) {
            for (int j = i + 1; j < numbers.length; j++) {
                if (numbers[i]+numbers[j]==target){
                    index[0] = i+1;
                    index[1] = j+1;
                }
            }
        }
        return index;*/

        // 双指针
        int left = 0;
        int right = numbers.length - 1;
        while (left < right) {
            int sum = numbers[left] + numbers[right];
            if (sum == target) {
                return new int[]{left + 1, right + 1};
            } else if (sum > target) {
                right--;
            } else {
                left++;
            }
        }
        return new int[2];
    }

    public static int reverse(int x) {
        String s = null;
        String[] split;
        if (x >= 0) {
            s = String.valueOf(x);
            split = s.split("");
        } else {
            String substring = String.valueOf(x).substring(1);
            split = substring.split("");
        }
        List<String> stringList = Arrays.asList(split);
        ArrayList<String> values = new ArrayList<>();
        StringBuffer sb = new StringBuffer();
        for (int i = stringList.size() - 1; i >= 0; i--) {
            sb.append(stringList.get(i));
        }
        int i = Integer.parseInt(sb.toString());
        if (x > 0) {
            return i;
        } else {
            return -i;
        }
    }

    public static int isPalindrome2(int x) {
        int reverse = 0;
        while (x != 0) {
            reverse = reverse * 10 + x % 10;
            x /= 10;
        }
        return reverse;
    }


    // 判断一个整数是不是回文数
    public boolean isPalindrome(int x) {
        if (x < 0) {
            return false;
        }
        String[] split = String.valueOf(x).split("");
        List<String> list = Arrays.asList(split);
        ArrayList<String> b = new ArrayList<>();
        ArrayList<String> a = new ArrayList<>(list);
        for (int i = list.size() - 1; i >= 0; i--) {
            b.add(list.get(i));
        }
        return a.equals(b);
    }

    public List<TreeNode> generateTrees(int n) {
        if (n == 0) {
            return new LinkedList<TreeNode>();
        }
        return generateTrees(1, n);
    }

    public List<TreeNode> generateTrees(int start, int end) {
        List<TreeNode> allTrees = new LinkedList<TreeNode>();
        if (start > end) {
            allTrees.add(null);
            return allTrees;
        }

        // 枚举可行根节点
        for (int i = start; i <= end; i++) {
            // 获得所有可行的左子树集合
            List<TreeNode> leftTrees = generateTrees(start, i - 1);

            // 获得所有可行的右子树集合
            List<TreeNode> rightTrees = generateTrees(i + 1, end);

            // 从左子树集合中选出一棵左子树，从右子树集合中选出一棵右子树，拼接到根节点上
            for (TreeNode left : leftTrees) {
                for (TreeNode right : rightTrees) {
                    TreeNode currTree = new TreeNode(i);
                    currTree.left = left;
                    currTree.right = right;
                    allTrees.add(currTree);
                }
            }
        }
        return allTrees;
    }

    public int minArray(int[] numbers) {
        Arrays.sort(numbers);
        return numbers[0];
    }

    // 二分法
    public int minArray2(int[] numbers) {
        int left = 0;
        int right = numbers.length - 1;
        while (left < right) {
            int middle = left + (right - left) / 2;
            if (numbers[middle] < numbers[right]) {
                right = middle;
            } else if (numbers[middle] > numbers[right]) {
                left = middle + 1;
            } else {
                right -= 1;
            }
        }
        return numbers[left];
    }
}
