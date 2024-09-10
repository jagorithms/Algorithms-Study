import java.io.*;

public class Main {

    static int N, stairs[];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder output = new StringBuilder();

        N = Integer.parseInt(br.readLine());
        stairs = new int[N+1];
        for (int i = 1; i <= N; i++) {
            stairs[i] = Integer.parseInt(br.readLine());
        }
        
        int[][] dp = new int[N+1][2];

        for (int i = 1; i <= N; i++) {
            dp[i][0] = dp[i-1][1] + stairs[i]; 
            if (i == 2) dp[i][0] = Math.max(dp[i][0], dp[i-1][0] + stairs[i]);

            if (i - 2 < 0) continue;
            dp[i][1] = Math.max(dp[i-2][0], dp[i-2][1]) + stairs[i];
        }


        output.append(Math.max(dp[N][0], dp[N][1]));

        bw.write(output.toString());
        bw.flush();
        br.close();
        bw.close();
    }
}
