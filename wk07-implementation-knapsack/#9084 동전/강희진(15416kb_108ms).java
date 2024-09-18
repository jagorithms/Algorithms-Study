import java.util.*;
import java.io.*;

public class Main {

    static int N, coins[], M;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());

        int T = Integer.parseInt(st.nextToken());
        for (int t = 1; t <= T; t++) {
            N = Integer.parseInt(br.readLine());
            coins = new int[N];
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                coins[i] = Integer.parseInt(st.nextToken());
            }
            M = Integer.parseInt(br.readLine());

            int[][] dp = new int[N][M+1];
            for (int i = 0; i < N; i++) {
                dp[i][0] = 1; // 0을 만들 수 있는 방법은 1개다
            }
            for (int i = 1; i <= M; i++) {
                if (i % coins[0] == 0) dp[0][i] = 1; // 가장 작은 동전으로 만들 수 있는 금액 초기화
            }

            for (int coinIdx = 1; coinIdx < N; coinIdx++) {
                for (int totalMoney = 0; totalMoney <= M; totalMoney++) {
                    dp[coinIdx][totalMoney] = dp[coinIdx-1][totalMoney];
                    if (totalMoney - coins[coinIdx] >= 0 && dp[coinIdx][totalMoney - coins[coinIdx]] > 0) {
                        dp[coinIdx][totalMoney] += dp[coinIdx][totalMoney - coins[coinIdx]];
                    }
                }
            }

            sb.append(dp[N-1][M]).append("\n");
            /*for (int i = 0; i < N; i++) {
                for (int j = 0; j <= M; j++) {
                    System.out.print(dp[i][j]+" ");
                }
                System.out.println();
            }*/
        }

        // sb.append(sum);

        bw.write(sb.toString());
        bw.flush();
        br.close();
        bw.close();
    }
    
}
