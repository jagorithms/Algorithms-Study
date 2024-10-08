import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        int n = Integer.parseInt(br.readLine());
        int[][] li = new int[n][2];

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            li[i][0] = Integer.parseInt(st.nextToken());
            li[i][1] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(li, Comparator.comparingInt(o -> o[0]));

        int[] dp = new int[501];
        Arrays.fill(dp, 1);

        int maxDp = 0;

        for (int a = 1; a < n; a++) {
            for (int b = 0; b < a; b++) {
                if (li[b][1] < li[a][1] && dp[b] >= dp[a]) {
                    dp[a] = dp[b] + 1;
                    maxDp = Math.max(maxDp, dp[a]);
                }
            }
        }
        
        System.out.println(n - maxDp);
    }
}
