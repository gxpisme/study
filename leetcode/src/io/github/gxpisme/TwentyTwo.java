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
     * ������е��������
     * a = a
     * ab = ab ba
     * abc = abc acb bac bca cab cba
     * ������ֵ������������ǣ����ǽ�����һ��ֵ�̶���Ȼ������ֵ�����������
     * ����a�̶���bc������������ֽ��bc cb,Ȼ����a����ƴ�ӣ����Ϊabc acb
     * abcd = abcd abdc acbd acbd adbc adcb ...
     * ���ĸ�ֵ�Ļ��������ƣ�������һ��ֵ�̶���Ȼ������������������
     *
     * @param strings
     * @return
     */
    public static Set<String> getCombination(List<String> strings) {
        // ���ֻ��һ��ֵ��ֱ�ӷ���
        if (strings.size() == 1) {
            Set<String> set = new HashSet<>();
            set.add(strings.get(0));
            return set;
        }
        // ���ֻ������ֵ���򷵻������������ab ba�������
        if (strings.size() == 2) {
            Set<String> set = new HashSet<>();
            set.add(strings.get(0) + strings.get(1));
            set.add(strings.get(1) + strings.get(0));
            return set;
        }
        // ���Ƕ��ֵ���������
        Set<String> resultList = new HashSet<>();

        for (int i = 0; i < strings.size(); i++) {
            // removeCurrentIndexList �ǳ�ȥ�ϲ�����ĵ�ǰindex������ֵ
            ArrayList<String> removeCurrentIndexList = new ArrayList<>();
            for (int j = 0; j < strings.size(); j++) {
                if (i != j) {
                    removeCurrentIndexList.add(strings.get(j));
                }
            }

            // Ȼ��ݹ�����Ƴ�index֮����б�
            Set<String> itemResultList = getCombination(removeCurrentIndexList);

            // �����б�ƴ�ӷ��ؽ��
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
        // ������ջ�ṹ�����Ƿ����
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
         * �õ�����ǰ����Ҫƥ��ģ���Ҫ�õ�ջ������ݽṹ
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

        // n=1�������ֱ�ӷ���
        if (n == 1) {
            stringList.add("()");
            return stringList;
        }

        // n=2�����������������case
        if (n == 2) {
            stringList.add("()()");
            stringList.add("(())");
            return stringList;
        }

        Set<String> set = new HashSet<>();
        // ��ʼ����n>2�����
        for (int i = 1; i < n; i++) {
            // �ֱ��ȡ�����ַ������е���� �ٽ���ƴ��
            List<String> left = generateParenthesis(i);
            List<String> right = generateParenthesis(n - i);
            for (String l : left) {
                for (String r : right) {
                    set.add(l + r);
                }
            }
            // ���ǵ������case��ͷ��β���к�Ӧ
            if (i == 1) {
                for (String s : right) {
                    set.add("(" + s + ")");
                }
            }
        }
        return set.stream().distinct().collect(Collectors.toList());
    }

    /**
     * �ٷ��Ĵ�
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