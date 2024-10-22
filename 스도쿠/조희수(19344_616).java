import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 @author ranuinclulus
 @since 2024.10.16
 @link
 @timecomplex
 @performance
 @category
 @note
 */
public class Main {
    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder builder = new StringBuilder();
    static StringTokenizer tokenizer;
    static int[][] sudoku;
    static boolean flag;

    public void solution() throws IOException {
        sudoku = new int[9][9];
        for (int i = 0; i < 9; i++) {
            String[] input = reader.readLine().split("");
            for (int j = 0; j < 9; j++) {
                sudoku[i][j] = Integer.parseInt(input[j]);
            }
        }

        backtracking(0);

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                builder.append(sudoku[i][j]);
            }
            builder.append("\n");
        }
        writer.write(builder.toString());
        writer.flush();
    }

    private void backtracking(int depth) {
        if (depth == 81) {
            flag = true;
            return;
        }

        int col = depth / 9;
        int row = depth % 9;

        if (sudoku[col][row] != 0) backtracking(depth + 1);
        else {
            for (int i = 1; i <= 9; i++) {
                if (!isValid(col, row, i)) continue;
                sudoku[col][row] = i;
                backtracking(depth + 1);

                if (flag) return;
                sudoku[col][row] = 0;
            }
        }
    }

    private boolean isValid(int col, int row, int number) {
        for (int i = 0; i < 9; i++) {
            if (sudoku[col][i] == number || sudoku[i][row] == number) return false;
        }

        int squareCol = col / 3 * 3;
        int squareRow = row - row % 3;

        for (int i = squareCol; i < squareCol + 3; i++) {
            for (int j = squareRow; j < squareRow + 3; j++) {
                if (sudoku[i][j] == number) return false;
            }
        }
        return true;
    }

    public static void main(String[] args) throws IOException {
        new Main().solution();
    }
}
