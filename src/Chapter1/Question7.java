package Chapter1;

/**
 * Rotate Matrix: Given an image represented by an NxN matrix, where each pixel in the image is 4
 * bytes, write a method to rotate the image by 90 degrees. Can you do this in place?
 */
public class Question7 {

    public void rotate(int[][] array) {

        for (int layer = 0; layer < array.length / 2; layer++) {
            int first = layer;
            int last = array.length - 1 - layer;
            for (int i = first; i < last; i++) {
                int offset = i - first;
                int top = array[first][i];
                // left -> top
                array[first][i] = array[last - offset][first];
                // bottom -> left
                array[last - offset][first] = array[last][last - offset];
                // right -> bottom
                array[last][last - offset] = array[i][last];
                // top -> right
                array[i][last] = top;
            }
        }

    }

    public static void main(String[] args) {
        Question7 question7 = new Question7();
        int[][] matrix = {
                {1, 2, 3, 4, 5},
                {6, 7, 8, 9, 10},
                {11, 12, 13, 14, 15},
                {16, 17, 18, 19, 20},
                {21, 22, 23, 24, 25}
        };
        question7.rotate(matrix);
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }

}
