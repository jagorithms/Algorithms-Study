import java.io.*;
import java.util.*;

public class Main {

    static int N, T;
    

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder output = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());

        int[][] dp = new int[N+1][T+1];
        int[][] hw = new int[N+1][2];
        int totalFine = 0;
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            hw[i][0] = Integer.parseInt(st.nextToken());
            hw[i][1] = Integer.parseInt(st.nextToken());
            totalFine += hw[i][1];
        }

        for (int i = 1; i <= N; i++) {
            for (int t = 1; t <= T; t++) {
                if (hw[i][0] <= t) {
                    if ((hw[i][1] + dp[i-1][t - hw[i][0]]) > dp[i-1][t]) {
                        dp[i][t] = hw[i][1] + dp[i - 1][t - hw[i][0]];
                    } else {
                        dp[i][t] = dp[i-1][t];
                    }
                } else {
                    dp[i][t] = dp[i-1][t];
                }
            }
        }
        output.append(totalFine-dp[N][T]);
        bw.write(output.toString());
        bw.flush();
        br.close();
        bw.close();
    }
}
