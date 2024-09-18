package com.example.javacodingtest.boj.gold;

import java.io.*;
import java.util.StringTokenizer;

/*
 @author ranuinclulus
 @since
 @link https://www.acmicpc.net/problem/29704
 @timecomplex
 @performance 18684kb, 140ms
 @category DP, 배낭문제
 @note
 */
public class five29704 {
    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder builder = new StringBuilder();
    static StringTokenizer tokenizer;
    static int n, t, fine;
    static int[][] algorithms;
    static int[][] dp;

    public void solution() throws IOException {
        tokenizer = new StringTokenizer(reader.readLine());
        n = Integer.parseInt(tokenizer.nextToken());
        t = Integer.parseInt(tokenizer.nextToken());
        algorithms = new int[n + 1][2];
        for (int i = 1; i <= n; i++) {
            tokenizer = new StringTokenizer(reader.readLine());
            algorithms[i][0] = Integer.parseInt(tokenizer.nextToken());
            algorithms[i][1] = Integer.parseInt(tokenizer.nextToken());
            fine += algorithms[i][1];
        }

        dp = new int[n + 1][t + 1];

        for (int time = 1; time <= t; time++) {
            for (int problem = 1; problem <= n; problem++) {
                dp[problem][time] = dp[problem - 1][time];
                if (time >= algorithms[problem][0]) {
                    dp[problem][time] = Math.max(dp[problem - 1][time], algorithms[problem][1] + dp[problem - 1][time - algorithms[problem][0]]);

                }

            }
        }

        builder.append(fine - dp[n][t] >= 0 ? fine - dp[n][t] : 0);
        writer.write(builder.toString());
        writer.flush();
    }

    public static void main(String[] args) throws IOException {
        new five29704().solution();
    }
}
