package Chapter1;

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
