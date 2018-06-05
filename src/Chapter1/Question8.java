package Chapter1;

/**
 * Zero Matrix: Write an algorithm such that if an element in an MxN matrix is 0, its entire row and
 * column are set to 0.
 */
class Question8 {

    public void zeroMatrix(int[][] matrix) {

        boolean[] rows = new boolean[matrix.length];
        boolean[] columns = new boolean[matrix[0].length];

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j] == 0) {
                    rows[i] = true;
                    columns[j] = true;
                }
            }
        }

        for (int i = 0; i < rows.length; i++) {
            if (rows[i] == true) {
                for (int j = 0; j < matrix[i].length; j++) {
                    matrix[i][j] = 0;
                }
            }
        }

        for (int j = 0; j < columns.length; j++) {
            if (columns[j] == true) {
                for (int i = 0; i < matrix.length; i++) {
                    matrix[i][j] = 0;
                }
            }
        }

    }

    public static void main(String[] args) {
        Question8 question8 = new Question8();
        int[][] matrix = {
                {1, 2, 3, 4, 5, 6},
                {1, 0, 3, 4, 5, 6},
                {1, 2, 3, 0, 5, 6},
                {1, 2, 3, 4, 5, 6}
        };
        question8.zeroMatrix(matrix);
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }

}
