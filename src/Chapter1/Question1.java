package Chapter1;

import java.util.HashSet;
import java.util.Set;

/**
 * Is Unique: Implement an algorithm to determine if a string has all unique characters. What if you
 * cannot use additional data structures?
 */
class Question1 {

    public boolean isUniqueS1(String s) {
        Set<Character> chars = new HashSet<>();
        for (char c : s.toCharArray()) {
            if (chars.contains(c)) {
                return false;
            }
            chars.add(c);
        }
        return true;
    }

    public boolean isUniqueS2(String s) {
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            for (int j = 0; j < s.length(); j++) {
                if (i == j) {
                    continue;
                }
                if (c == s.charAt(j)) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean isUniqueS3(String s) {
        if (s.length() > 128) {
            return false;
        }
        boolean[] charSet = new boolean[128];
        for (char c : s.toCharArray()) {
            int index = c;
            if (charSet[index]) {
                return false;
            }
            charSet[index] = true;
        }
        return true;
    }

    public boolean isUniqueS4(String s) {
        // assuming lower case chars only
        int checker = 0;
        for (char c : s.toCharArray()) {
            int val = c - 'a';
            if ((checker & (1 << val)) > 0) {
                return false;
            }
            checker |= (1 << val);
        }
        return true;
    }

    public static void main(String[] args) {
        Question1 question1 = new Question1();
        System.out.println(question1.isUniqueS1("123456890"));
        System.out.println(question1.isUniqueS1("12345678900987654321"));
        System.out.println(question1.isUniqueS2("123456890"));
        System.out.println(question1.isUniqueS2("12345678900987654321"));
        System.out.println(question1.isUniqueS3("123456890"));
        System.out.println(question1.isUniqueS3("12345678900987654321"));
        System.out.println(question1.isUniqueS4("abcdefg"));
        System.out.println(question1.isUniqueS4("abcdefgabcdefg"));
    }

}
