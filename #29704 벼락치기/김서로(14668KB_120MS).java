import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 벼락치기 {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer tokens;

    private static int N;
    private static int T;

    private static int[][] problemInfo;
    private static int[] dp;
    private static int sum; // 모든 문제에 대한 총 벌금

    private static int solution() {
        for (int[] problem : problemInfo) {
            int day = problem[0];
            int fine = problem[1];

            for (int i = T; i >= day; i--) {
                dp[i] = Math.max(dp[i], dp[i - day] + fine);
            }
        }

        return sum - dp[T];
    }

    private static void init() throws IOException {
        tokens = new StringTokenizer(br.readLine());
        N = Integer.parseInt(tokens.nextToken());
        T = Integer.parseInt(tokens.nextToken());

        dp = new int[T + 1];
        problemInfo = new int[N][2];
        sum = 0;

        for (int i = 0; i < N; i++) {
            tokens = new StringTokenizer(br.readLine());
            int day = Integer.parseInt(tokens.nextToken());
            int fine = Integer.parseInt(tokens.nextToken());
            problemInfo[i][0] = day;
            problemInfo[i][1] = fine;
            sum += fine;
        }
    }

    public static void main(String[] args) throws IOException {
        init();
        System.out.println(solution());
    }
}
