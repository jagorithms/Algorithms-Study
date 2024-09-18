
import java.io.*;
import java.util.StringTokenizer;

/*
 @author ranuinclulus
 @since 2024.08.14
 @link https://www.acmicpc.net/problem/1780
 @timecomplex
 @performance 310188KB 860MS
 @category #devided conquer
 @note
 */
public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static int n;
    static int[][] map;
    static int minusCnt;
    static int zeroCnt;
    static int plusCnt;

    public void solution() throws IOException {
        n = Integer.parseInt(br.readLine());
        map = new int[n][n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        conquer(0, 0, n);
        sb.append(minusCnt).append("\n")
          .append(zeroCnt).append("\n")
          .append(plusCnt).append("\n");
        bw.write(sb.toString());
        bw.flush();
    }

    private void conquer(int row, int col, int size) {
        if (checkSame(row, col, size)) {
            int val = map[row][col];

            if (val > 0) plusCnt++;
            else if (val == 0) zeroCnt++;
            else minusCnt++;
            return;
        }

        else {
            int newSize = size / 3;
            for (int i = row; i < row + size; i += newSize) {
                for (int j = col; j < col + size; j += newSize) {
                    conquer(i, j, newSize);
                }
            }
        }
    }

    private boolean checkSame(int row, int col, int size) {
        int val = map[row][col];

        for (int i = row; i < row + size; i++) {
            for (int j = col; j < col + size; j++) {
                if (map[i][j] != val) return false;
            }
        }
        return true;
    }

    public static void main(String[] args) throws IOException {
        new Main().solution();
    }
}
