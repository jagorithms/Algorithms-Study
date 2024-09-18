import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 계단오르기 {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    private static int N; // 계단의 개수
    private static int[] stairs; // 각 계단의 높이
    private static int[][] dp;

    private static int solution() {
        // dp[i][0]: 이전 계단과 연속해서 밟았을 경우의 누적 점수
        // dp[i][1]: 이전 계단을 건너뛰고 두 칸 올랐을 경우의 누적 점수
        for (int i = 2; i <= N; i++) {
            dp[i][0] = stairs[i] + dp[i-1][1];
            dp[i][1] = stairs[i] + Math.max(dp[i-2][0], dp[i-2][1]);
        }

        return Math.max(dp[N][0], dp[N][1]);
    }

    private static void init() throws IOException {
        N = Integer.parseInt(br.readLine());
        stairs = new int[N + 1];

        for (int i = 1; i <= N; i++) {
            stairs[i] = Integer.parseInt(br.readLine());
        }

        dp = new int[N + 1][2];
        dp[1][0] = dp[1][1] = stairs[1];
    }

    public static void main(String[] args) throws IOException {
        init();
        System.out.println(solution());
    }
}
