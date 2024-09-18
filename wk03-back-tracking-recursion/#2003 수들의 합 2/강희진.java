import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());

        int[] nums = new int[N+1];
        int idx = 0;
        while (st.hasMoreTokens()) {
            nums[1+idx++] = Integer.parseInt(st.nextToken());
        }

        int[] dp = new int[N+1];

        for (int i = 1; i <= N; i++) {
            dp[i] = nums[i] + dp[i-1];
        }
        int count = 0;

        for (int i = 0; i <= N; i++) {
            for (int j = i+1; j <= N; j++) {
                if (dp[j] - dp[i] == M) count++;
            }
        }
        sb.append(count);
        bw.write(sb.toString());
        bw.flush();
        br.close();
    }

    
}
