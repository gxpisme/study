package io.github.gxpisme;

import sun.jvm.hotspot.utilities.Assert;

/**
 * https://leetcode.com/problems/regular-expression-matching/
 * <p>
 * Given an input string s and a pattern p, implement regular expression matching with support for '.' and '*' where:
 * <p>
 * '.' Matches any single character.????
 * '*' Matches zero or more of the preceding element.
 * The matching should cover the entire input string (not partial).
 * <p>
 * <p>
 * Example 1:
 * Input: s = "aa", p = "a"
 * Output: false
 * Explanation: "a" does not match the entire string "aa".
 * Example 2:
 * Input: s = "aa", p = "a*"
 * Output: true
 * Explanation: '*' means zero or more of the preceding element, 'a'. Therefore, by repeating 'a' once, it becomes "aa".
 * Example 3:
 * Input: s = "ab", p = ".*"
 * Output: true
 * Explanation: ".*" means "zero or more (*) of any character (.)".
 * Example 4:
 * Input: s = "aab", p = "c*a*b"
 * Output: true
 * Explanation: c can be repeated 0 times, a can be repeated 1 time. Therefore, it matches "aab".
 * Example 5:
 * Input: s = "mississippi", p = "mis*is*p*."
 * Output: false
 * Example 6:
 * Input: s = "abcd", p = "a.*d"
 * Output: false
 * Constraints:
 * 1 <= s.length <= 20
 * 1 <= p.length <= 30
 * <p>
 * s contains only lowercase English letters.
 * p contains only lowercase English letters, '.', and '*'.
 * It is guaranteed for each appearance of the character '*', there will be a previous valid character to match.
 */
public class Ten {
    public static void main(String[] args) {
        if (isMatch("aa", "a") == false) {
        } else {
            System.out.println("1 failed");
        }
        if (isMatch("aa", "a*") == true) {
        } else {
            System.out.println("2 failed");
        }
        if (isMatch("ab", ".*") == true) {
        } else {
            System.out.println("3 failed");
        }
        if (isMatch("aab", "c*a*b") == true) {
        } else {
            System.out.println("4 failed");
        }
        if (isMatch("mississippi", "mis*is*p*.") == false) {
        } else {
            System.out.println("5 failed");
        }
        if (isMatch("abcd", "a.*d") == true) {
        } else {
            System.out.println("6 failed");
        }
        if (isMatch("abcd", "a.*e") == false) {
        } else {
            System.out.println("7 failed");
        }
        if (isMatch("aaa", "aa") == false) {
        } else {
            System.out.println("8 failed");
        }
        if (isMatch("ab", ".*c") == false) {
        } else {
            System.out.println("9 failed");
        }
        if (isMatch("aaa", "ab*ac*a") == true) {
        } else {
            System.out.println("10 failed");
        }
        if (isMatch("aaa", "a.a") == true) {
        } else {
            System.out.println("11 failed");
        }
        if (isMatch("aaca", "ab*a*c*a") == true) {
        } else {
            System.out.println("12 failed");
        }
        if (isMatch("a", "ab*") == true) {
        } else {
            System.out.println("13 failed");
        }
        if (isMatch("bbbba", ".*a*a") == true) {
        } else {
            System.out.println("14 failed");
        }
        if (isMatch("aaa", "a*a") == true) {
        } else {
            System.out.println("15 failed");
        }
    }

    /**
     * @param s string
     * @param p pattern
     * @return boolean
     */
    public static boolean isMatch(String s, String p) {
        if (p.isEmpty()) {
            return s.isEmpty();
        }
        boolean firstMatch = !s.isEmpty() && (p.charAt(0) == s.charAt(0) || p.charAt(0) == '.');
        if (p.length() >= 2 && p.charAt(1) == '*') {
            return isMatch(s, p.substring(2))  // 这里代表没有匹配的情况
                    || firstMatch && isMatch(s.substring(1), p); // 这里代表匹配到>=1的情况
        }
        return firstMatch && isMatch(s.substring(1), p.substring(1));
    }

    /**
     * 若pattern只有'.' 没有'*'
     * @param s
     * @param p
     * @return
     */
    public static boolean isSingleMatch(String s, String p) {
        if (p.isEmpty()) {
            return s.isEmpty();
        }
        // 字符串s 不为空，并且s和p一一对应，p为'.'时也与s对应。
        boolean firstMatch = !s.isEmpty() && (p.charAt(0) == s.charAt(0) || p.charAt(0) == '.');
        // 均向后移动一位
        return firstMatch && isMatch(s.substring(1), p.substring(1));
    }
}
