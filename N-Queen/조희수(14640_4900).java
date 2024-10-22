import java.io.*;
import java.util.StringTokenizer;

/*
 @author ranuinclulus
 @since 2024.10.18
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
    static int n, count;
    static int[] queenPos;

    public void solution() throws IOException {
        n = Integer.parseInt(reader.readLine());
        queenPos = new int[n];

        setQueenPos(0);

        builder.append(count);
        writer.write(builder.toString());
        writer.flush();
    }

    private void setQueenPos(int row) {
        if (row == n) {
            count++;
            return;
        } else for(int i = 0; i < n; i++) {
            queenPos[row] = i;
            if (checkQuuen(row)) setQueenPos(row + 1);
        }
     }

    private boolean checkQuuen(int row) {
        for (int i = 0; i < row; i++) {
            if (queenPos[i] == queenPos[row]) return false; // 지난 기록과 겹치는 게 있는지
            else if(Math.abs(queenPos[i] - queenPos[row]) == row - i) return false; // 대각선과 겹치는 게 있는지
        }
        return true;
    }

    public static void main(String[] args) throws IOException {
        new Main().solution();
    }
}
