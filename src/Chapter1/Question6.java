package Chapter1;

public class Question6 {

    public String compress(String s) {
        if (s.length() < 2) {
            return s;
        }
        int i = 0;
        StringBuilder result = new StringBuilder();
        char prevChar = s.charAt(i);
        int count = 0;
        while(i < s.length()) {
            if (prevChar == s.charAt(i)) {
                count++;
            } else {
                result.append(prevChar);
                result.append(count);
                prevChar = s.charAt(i);
                count = 1;
            }
            i++;
        }
        result.append(prevChar);
        result.append(count);
        return result.length() < s.length() ? result.toString() : s;
    }

    public static void main(String[] args) {
        Question6 question6 = new Question6();
        System.out.println(question6.compress("aabcccccaaa"));
    }

}
