import java.io.*;
import java.util.*;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;

	public static void main(String[] args) throws Exception {
		int n = Integer.parseInt(br.readLine());

		if (n == 1) {
			System.out.println(Integer.parseInt(br.readLine()));
		} else {
			int[] li = new int[n + 1];
			for (int i = 1; i <= n; i++) {
				li[i] = Integer.parseInt(br.readLine());
			}

			int[] dp = new int[n + 1];
			dp[1] = li[1];
			dp[2] = li[1] + li[2];

			for (int i = 3; i <= n; i++) {
				dp[i] = li[i] + Math.max(dp[i - 3] + li[i - 1], dp[i - 2]);
			}

			System.out.println(dp[n]);
		}
	}
}
