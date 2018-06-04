package Chapter1;

public class Question9 {

    public boolean stringRotation(String s1, String s2) {
        return (s1 + s1).contains(s2);
    }

    public static void main(String[] args) {
        Question9 question9 = new Question9();
        System.out.println(question9.stringRotation("waterbottle", "erbottlewat"));
    }

}
