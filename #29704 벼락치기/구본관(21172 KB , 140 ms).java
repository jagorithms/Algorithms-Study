import java.io.*;
import java.util.*;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;

	public static void main(String[] args) throws Exception {
		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int T = Integer.parseInt(st.nextToken());

		int[][] dp = new int[1000+1][1000+1];
		int result = 0;

		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			result += b;

			for(int j = 1; j < a; j++) {
				dp[i][j] = dp[i-1][j];
			}
			for (int j = a; j <= T; j++) {
				dp[i][j] = Math.max(dp[i - 1][j - a] + b, dp[i - 1][j]);
			}
		}

		// for (int i = 1; i <= N; i++) {
		// 	for (int j = 1; j <= T; j++) {
		// 		System.out.print(dp[i][j]+" ");
		// 	}
		// 	System.out.println();
		// }

		System.out.println(result - dp[N][T]);
	}
}
