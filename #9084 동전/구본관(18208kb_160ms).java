import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        int T = Integer.parseInt(br.readLine());

        for (int t = 1; t <= T; t++) {
            int N = Integer.parseInt(br.readLine());

            int[] coin = new int[N+1];
            st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= N; i++) {
                coin[i] = Integer.parseInt(st.nextToken());
            }

            int M = Integer.parseInt(br.readLine());

            int[][] dp = new int[N+1][M + 1];

            for(int i = 1; i <= N; i++){
                for(int j = 1; j <= M; j++){
                    for(int k = j; k > 0; k-=coin[i]){
                        dp[i][j] += dp[i-1][k];
                    }
                    if(j%coin[i] == 0){
                        dp[i][j] += 1;
                    }
                }
            }
//            for (int i = 1; i <= N; i++) {
//                System.out.println(Arrays.toString(dp[i]));
//            }
            System.out.println(dp[N][M]);
        }
    }
}
