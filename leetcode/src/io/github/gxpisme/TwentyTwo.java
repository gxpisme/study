package io.github.gxpisme;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.
 * <p>
 * Example 1:
 * Input: n = 3
 * Output: ["((()))","(()())","(())()","()(())","()()()"]
 * <p>
 * Example 2:
 * Input: n = 1
 * Output: ["()"]
 * <p>
 * Constraints:
 * 1 <= n <= 8
 */
public class TwentyTwo {

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        List<String> strings = generateParenthesisThree(8);
        System.out.println(strings);
        System.out.println(System.currentTimeMillis() - start);

//        System.out.println(generateParenthesisTwo(1));
//        System.out.println(generateParenthesisTwo(2));
//        System.out.println(generateParenthesisTwo(3));
//        System.out.println(generateParenthesisTwo(4));
//        System.out.println(generateParenthesisTwo(5));

//        System.out.println(generateParenthesis(1));
//        System.out.println(generateParenthesis(2));
//        System.out.println(generateParenthesis(3));
//        System.out.println(generateParenthesis(4));
//        System.out.println(generateParenthesis(5));
    }

    /**
     * 输出所有的排列组合
     * a = a
     * ab = ab ba
     * abc = abc acb bac bca cab cba
     * 有三个值的情况这样考虑，就是将其中一个值固定，然后将其他值进行组合排列
     * 即将a固定，bc组合排列有两种结果bc cb,然后与a进行拼接，结果为abc acb
     * abcd = abcd abdc acbd acbd adbc adcb ...
     * 有四个值的话依次类推，将其中一个值固定，然后排列组合其他结果。
     *
     * @param strings
     * @return
     */
    public static Set<String> getCombination(List<String> strings) {
        // 如果只有一个值则直接返回
        if (strings.size() == 1) {
            Set<String> set = new HashSet<>();
            set.add(strings.get(0));
            return set;
        }
        // 如果只有两个值，则返回两个情况。即ab ba两种情况
        if (strings.size() == 2) {
            Set<String> set = new HashSet<>();
            set.add(strings.get(0) + strings.get(1));
            set.add(strings.get(1) + strings.get(0));
            return set;
        }
        // 若是多个值，则遍历，
        Set<String> resultList = new HashSet<>();

        for (int i = 0; i < strings.size(); i++) {
            // removeCurrentIndexList 是除去上层遍历的当前index的所有值
            ArrayList<String> removeCurrentIndexList = new ArrayList<>();
            for (int j = 0; j < strings.size(); j++) {
                if (i != j) {
                    removeCurrentIndexList.add(strings.get(j));
                }
            }

            // 然后递归调用移除index之后的列表
            Set<String> itemResultList = getCombination(removeCurrentIndexList);

            // 遍历列表，拼接返回结果
            for (String s : itemResultList) {
                resultList.add(strings.get(i) + s);
            }
        }

        return resultList;
    }

    public static Boolean symmetry(String str) {
        if (Objects.isNull(str) || str.isEmpty()) {
            return false;
        }
        if (str.length() % 2 != 0) {
            return false;
        }
        if (str.charAt(0) == ')') {
            return false;
        }
        if (str.charAt(str.length() - 1) == '(') {
            return false;
        }
        // 用两个栈结构来看是否符合
        Stack<Character> leftStack = new Stack<>();
        Stack<Character> rightStack = new Stack<>();
        for (int i = 0; i < str.length(); i++) {
            leftStack.push(str.charAt(i));
        }

        while (leftStack.size() > 0) {
            Character leftPop = leftStack.pop();
            if (leftPop == ')') {
                rightStack.push(leftPop);
                continue;
            }
            if (rightStack.size() == 0) {
                return false;
            }
            Character rightPop = rightStack.pop();
            if (rightPop == '(') {
                return false;
            }
        }

        return true;
    }

    public static List<String> generateParenthesisTwo(int n) {
        /**
         * 用到这种前后需要匹配的，需要用到栈这个数据结构
         *  ()
         *  (()) ()()
         *  ((())) (()()) (())() ()(()) ()()()
         */
        List<String> stringList = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            stringList.add("(");
            stringList.add(")");
        }
        Set<String> combinationList = getCombination(stringList);
        ArrayList<String> result = new ArrayList<>();
        for (String combination : combinationList) {
            if (symmetry(combination)) {
                result.add(combination);
            }
        }
        return result;
    }

    public static List<String> generateParenthesis(int n) {
        List<String> stringList = new ArrayList<>();

        // n=1的情况，直接返回
        if (n == 1) {
            stringList.add("()");
            return stringList;
        }

        // n=2的情况，返回这两种case
        if (n == 2) {
            stringList.add("()()");
            stringList.add("(())");
            return stringList;
        }

        Set<String> set = new HashSet<>();
        // 开始遍历n>2的情况
        for (int i = 1; i < n; i++) {
            // 分别获取两个字符串所有的情况 再进行拼接
            List<String> left = generateParenthesis(i);
            List<String> right = generateParenthesis(n - i);
            for (String l : left) {
                for (String r : right) {
                    set.add(l + r);
                }
            }
            // 考虑到特殊的case，头和尾进行呼应
            if (i == 1) {
                for (String s : right) {
                    set.add("(" + s + ")");
                }
            }
        }
        return set.stream().distinct().collect(Collectors.toList());
    }

    /**
     * 官方的答案
     * @param n
     * @return
     */
    public static List<String> generateParenthesisThree(int n) {
        List<String> combinations = new ArrayList();
        generateAll(new char[2 * n], 0, combinations);
        return combinations;
    }

    public static void generateAll(char[] current, int pos, List<String> result) {
        if (pos == current.length) {
            if (valid(current))
                result.add(new String(current));
        } else {
            current[pos] = '(';
            generateAll(current, pos+1, result);
            current[pos] = ')';
            generateAll(current, pos+1, result);
        }
    }

    public static boolean valid(char[] current) {
        int balance = 0;
        for (char c: current) {
            if (c == '(') balance++;
            else balance--;
            if (balance < 0) return false;
        }
        return (balance == 0);
    }
}