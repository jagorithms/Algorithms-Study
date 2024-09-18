import java.util.*;
import java.io.*;

public class Main {
    static int[][] grid;
    static int negativeOnes, positiveOnes, zeros;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());

        grid = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int j = 0;
            while (st.hasMoreTokens()) {
                grid[i][j++] = Integer.parseInt(st.nextToken());
            }
        }

        recurse(0, 0, N);
        sb.append(negativeOnes).append("\n").append(zeros).append("\n").append(positiveOnes);
        bw.write(sb.toString());
        bw.flush();
        br.close();
    }

    private static void recurse(int y, int x, int width) {
        int uniformity = checkUniformity(y, x, width);
        if (-2 != uniformity) {
            switch (uniformity) {
                case (-1):
                    negativeOnes++;
                    break;
                case (0):
                    zeros++;
                    break;
                default:
                    positiveOnes++;
                    break;
            }
        } else {
            width /= 3;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    recurse(y + width * i, x + width*j, width);
                }
            }
        }
    }

    private static int checkUniformity(int y, int x, int width) {
        int standard = grid[y][x];
        boolean success = true;
        for (int i = y; i < y + width; i++) {
            for (int j = x; j < x + width; j++) {
                if (standard != grid[i][j]) {
                    success = false;
                    break;
                }
            }
        }
        return success ? standard : -2;
    }


}
