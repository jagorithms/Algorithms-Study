import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int T = Integer.parseInt(st.nextToken());
        int W = Integer.parseInt(st.nextToken());
        
        int[] plums = new int[T];
        for (int i = 0; i < T; i++) {
            plums[i] = Integer.parseInt(br.readLine());
        }

        int[][] dp = new int[T][W + 1];

        for (int w = 0; w <= W; w++) {
            dp[0][w] = (plums[0] == 1 && w % 2 == 0) || (plums[0] == 2 && w % 2 == 1) ? 1 : 0;
        }

        for (int t = 1; t < T; t++) {
            for (int w = 0; w <= W; w++) {
                if (plums[t] == 1 && w % 2 == 0) {
                    dp[t][w] = Math.max(dp[t][w], dp[t-1][w] + 1); 
                } else if (plums[t] == 2 && w % 2 == 1) {
                    dp[t][w] = Math.max(dp[t][w], dp[t-1][w] + 1); 
                } else {
                    dp[t][w] = Math.max(dp[t][w], dp[t-1][w]);
                }

                if (w > 0) {
                    if (plums[t] == 1 && (w - 1) % 2 == 0) {
                        dp[t][w] = Math.max(dp[t][w], dp[t-1][w-1] + 1);
                    } else if (plums[t] == 2 && (w - 1) % 2 == 1) {
                        dp[t][w] = Math.max(dp[t][w], dp[t-1][w-1] + 1);
                    }
                }
            }
        }

        int result = 0;
        for (int w = 0; w <= W; w++) {
            result = Math.max(result, dp[T-1][w]);
        }
        
        System.out.println(result);
    }
}
