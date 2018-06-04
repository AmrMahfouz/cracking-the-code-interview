package Chapter1;

/**
 * String Rotation: Assume you have a method i5Substring which checks ifone word is a substring
 * of another. Given two strings, 51 and 52, write code to check if 52 is a rotation of 51 using only one
 * call to isSubstring (e.g., "waterbottle" a rotation "erbottlewat" ).
 */
public class Question9 {

    public boolean stringRotation(String s1, String s2) {
        return (s1 + s1).contains(s2);
    }

    public static void main(String[] args) {
        Question9 question9 = new Question9();
        System.out.println(question9.stringRotation("waterbottle", "erbottlewat"));
    }

}
