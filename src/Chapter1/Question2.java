package Chapter1;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Given two strings, write a method to decide if one is a permutation of the
 * other.
 */
class Question2 {

    public boolean isPermutationS1(String s1, String s2) {
        if (s1.length() != s1.length()) {
            return false;
        }
        Map<Character, Integer> charCount = new HashMap<>();
        for (char c : s1.toCharArray()) {
            charCount.put(c, charCount.getOrDefault(c, 0) + 1);
        }
        for (char c : s2.toCharArray()) {
            if (!charCount.containsKey(c)) {
                return false;
            }
            Integer count = charCount.get(c);
            if (count == 0) {
                return false;
            }
            charCount.put(c, count - 1);
        }
        return true;
    }

    public boolean isPermutationS2(String s1, String s2) {
        if (s1.length() != s2.length()) {
            return false;
        }
        // assuming ASCII characters only
        int[] chars = new int[128];
        for (char c : s1.toCharArray()) {
            int val = c - 'a';
            chars[val]++;
        }
        for (char c : s2.toCharArray()) {
            int val = c - 'a';
            if (chars[val] == 0) {
                return false;
            }
            chars[val]--;
        }
        return true;
    }

    public boolean isPermutationS3(String s1, String s2) {
        if (s1.length() != s2.length()) {
            return false;
        }
        char[] s1Chars = s1.toCharArray();
        Arrays.sort(s1Chars);
        char[] s2Chars = s2.toCharArray();
        Arrays.sort(s2Chars);
        s1 = new String(s1Chars);
        s2 = new String(s2Chars);
        return s1.equals(s2);
    }

    public static void main(String[] args) {
        Question2 question2 = new Question2();
        System.out.println(question2.isPermutationS1("amr", "ram"));
        System.out.println(question2.isPermutationS1("657", "22222"));
        System.out.println(question2.isPermutationS2("amr", "ram"));
        System.out.println(question2.isPermutationS2("657", "22222"));
        System.out.println(question2.isPermutationS3("amr", "ram"));
        System.out.println(question2.isPermutationS3("657", "22222"));
    }

}
