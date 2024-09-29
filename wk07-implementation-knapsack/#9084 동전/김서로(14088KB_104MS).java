import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 동전 {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer tokens;
    private static StringBuilder sb = new StringBuilder();

    private static int N;
    private static int M; // 만들어야 할 금액
    private static int[] coins;

    private static int[] dp;

    private static void solution() {
        dp[0] = 1;

        for (int coin : coins)  {
            for (int i = 1; i <= M; i++) {
                if (i - coin < 0) continue;
                dp[i] += dp[i - coin];
            }
        }
    }

    private static void init() throws IOException {
        N = Integer.parseInt(br.readLine());
        coins = new int[N];

        tokens = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            coins[i] = Integer.parseInt(tokens.nextToken());
        }

        M = Integer.parseInt(br.readLine());
        dp = new int[M + 1];
    }

    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            init();
            solution();
            sb.append(dp[M]).append("\n");
        }
        System.out.println(sb);
    }
}
