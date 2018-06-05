package Chapter1;

/**
 * One Away: There are three types of edits that can be performed on strings: insert a character,
 * remove a character, or replace a character. Given two strings, write a function to check if they are
 * one edit (or zero edits) away.
 * EXAMPLE
 * pale, ple -) true
 * pales, pale -) true
 * pale, bale -) true
 * pale, bae -) false
 */
class Question5 {

    public boolean OneWay(String s1, String s2) {
        int s1Length = s1.length();
        int s2Length = s2.length();
        int diff = Math.abs(s1Length - s2Length);
        if (diff > 1) {
            return false;
        }
        if (diff == 1) {
            // check insert and remove case
            if (s1.length() > s2.length()) {
                return insertOrRemove(s1, s2);
            } else {
                return insertOrRemove(s2, s1);
            }
        }
        // diff == 0, check replace case
        return replace(s1, s2);
    }

    private boolean replace(String s1, String s2) {
        boolean diff = false;
        for (int i = 0; i < s1.length(); i++) {
            if (s1.charAt(i) != s2.charAt(i)) {
                if (diff) {
                    return false;
                }
                diff = true;
            }
        }
        return true;
    }

    private boolean insertOrRemove(String first, String second) {
        int length = Math.max(first.length(), second.length());
        int i = 0;
        int j = 0;
        boolean diff = false;
        while (i < first.length() && j < second.length()) {
            if (first.charAt(i) != second.charAt(j)) {
                if (diff) {
                    return false;
                }
                diff = true;
                i++;
            }
            i++;
            j++;
        }
        return true;
    }

    public static void main(String[] args) {
        Question5 question5 = new Question5();
        System.out.println(question5.OneWay("pale", "ple"));
        System.out.println(question5.OneWay("pales", "pale"));
        System.out.println(question5.OneWay("pale", "bale"));
        System.out.println(question5.OneWay("pale", "bae"));
        System.out.println(question5.OneWay("pale", "pale"));
    }

}
