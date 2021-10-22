package io.github.gxpisme;

import java.util.*;

/**
 * https://leetcode.com/problems/letter-combinations-of-a-phone-number/submissions/
 *
 * Given a string containing digits from 2-9 inclusive, return all possible letter combinations that the number could represent. Return the answer in any order.
 * <p>
 * A mapping of digit to letters (just like on the telephone buttons) is given below. Note that 1 does not map to any letters.
 * <p>
 * Example 1:
 * Input: digits = "23"
 * Output: ["ad","ae","af","bd","be","bf","cd","ce","cf"]
 * Example 2:
 * Input: digits = ""
 * Output: []
 * Example 3:
 * Input: digits = "2"
 * Output: ["a","b","c"]
 * <p>
 * Constraints:
 * 0 <= digits.length <= 4
 * digits[i] is a digit in the range ['2', '9'].
 *
 *
 * @author gxp177726@alibaba-inc.com
 * @date 2021/10/21
 */
public class Seventeen {

    public static void main(String[] args) {
        List<String> strings = letterCombinations("4239");
        System.out.println(strings.size());
        System.out.println(strings);
    }

    public static List<String> letterCombinations(String digits) {
        List<String> result = new ArrayList<>();
        if (digits == null || digits.length() < 1) {
            return result;
        }

        Map<String, List<String>> phoneNumberMap = new HashMap<>(8);
        phoneNumberMap.put("2", Arrays.asList("a", "b", "c"));
        phoneNumberMap.put("3", Arrays.asList("d", "e", "f"));
        phoneNumberMap.put("4", Arrays.asList("g", "h", "i"));
        phoneNumberMap.put("5", Arrays.asList("j", "k", "l"));
        phoneNumberMap.put("6", Arrays.asList("m", "n", "o"));
        phoneNumberMap.put("7", Arrays.asList("p", "q", "r", "s"));
        phoneNumberMap.put("8", Arrays.asList("t", "u", "v"));
        phoneNumberMap.put("9", Arrays.asList("w", "x", "y", "z"));

        if (digits.length() == 1) {
            return phoneNumberMap.get(digits.substring(0, 1));
        }

        for (String one : phoneNumberMap.get(digits.substring(0, 1))) {
            String item;
            for (String two : phoneNumberMap.get(digits.substring(1, 2))) {
                item = one + two;
                if (digits.length() > 2) {
                    for (String three : phoneNumberMap.get(digits.substring(2, 3))) {
                        item = one + two + three;
                        if (digits.length() > 3) {
                            for (String four : phoneNumberMap.get(digits.substring(3, 4))) {
                                item = one + two + three + four;
                                result.add(item);
                            }
                        } else {
                            result.add(item);
                        }
                    }
                } else {
                    result.add(item);
                }
            }
        }
        return result;
    }
}
