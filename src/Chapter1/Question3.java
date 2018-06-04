package Chapter1;

/**
 * URLify: Write a method to replace all spaces in a string with '%2e: You may assume that the string
 * has sufficient space at the end to hold the additional characters, and that you are given the "true"
 * length of the string. (Note: if implementing in Java, please use a character array so that you can
 * perform this operation in place.)
 * EXAMPLE
 * Input: "Mr John Smith JJ, 13
 * Output: "Mr%2eJohn%2eSmith"
 */
public class Question3 {

    public String URLfify(String url, int length) {
        char[] chars = url.toCharArray();
        int lastValidCharIndex = length - 1;
        for (int i = chars.length - 1; i >= 0; i--) {
            char c = chars[lastValidCharIndex];
            if (c == ' ') {
                chars[i--] = '0';
                chars[i--] = '2';
                chars[i] = '%';
            } else {
                chars[i] = c;
            }
            lastValidCharIndex--;
        }
        return new String(chars);
    }

    public static void main(String[] args) {
        Question3 question3 = new Question3();
        System.out.println(question3.URLfify("Mr John Smith    ", 13));
    }

}
