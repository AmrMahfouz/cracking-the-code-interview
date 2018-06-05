package Chapter1;

import java.util.HashMap;
import java.util.Map;

/**
 * Palindrome Permutation: Given a string, write a function to check if it is a permutation of
 * a palindrome. A palindrome is a word or phrase that is the same forwards and backwards. A
 * permutation is a rearrangement of letters. The palindrome does not need to be limited to just
 * dictionary words.
 * EXAMPLE
 * Input: Tact Coa
 * Output: True (permutations:"taco cat'; "atco cta'; etc.)
 */
class Question4 {

    public boolean palindromePermutationS1(String s) {
        Map<Character, Integer> charCount = new HashMap<>();
        s = s.toLowerCase();
        for (char c : s.toCharArray()) {
            if (c != ' ') {
                charCount.put(c, charCount.getOrDefault(c, 0) + 1);
            }
        }
        boolean oddCount = false;
        for (int count : charCount.values()) {
            if (count % 2 == 1) {
                if (oddCount) {
                    return false;
                }
                oddCount = true;
            }
        }
        return true;
    }

    public boolean palindromePermutationS2(String s) {
        s = s.toLowerCase();
        int[] map = new int[26];
        int oddCount = 0;
        for (char c : s.toCharArray()) {
            if (c == ' ') {
                continue;
            }
            int val = c - 'a';
            map[val]++;
            if (map[val] % 2 == 1) {
                oddCount++;
            } else {
                oddCount--;
            }
        }
        return oddCount <= 1;
    }

    public boolean palindromePermutationS3(String s) {
        s = s.toLowerCase();
        int bitVector = 0;
        for (char c : s.toCharArray()) {
            if (c == ' ') {
                continue;
            }
            int val = c - 'a';
            int mask = 1 << val;
            if ((bitVector & mask) == 0) {
                bitVector |= mask;
            } else {
                bitVector &= ~mask;
            }
        }

        return bitVector == 0 || (bitVector & (bitVector - 1)) == 0;
    }

    public static void main(String[] args) {
        Question4 question4 = new Question4();
        System.out.println(question4.palindromePermutationS1("Tact Coa"));
        System.out.println(question4.palindromePermutationS2("Tact Coa"));
        System.out.println(question4.palindromePermutationS3("Tact Coa"));
    }

}
