package com.example.javacodingtest.boj.silver;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 @author ranuinclulus
 @since 2024.09.04
 @link https://www.acmicpc.net/problem/2579
 @timecomplex
 @performance 14224kb, 108ms
 @category DP
 @note
 */
public class two2579 {
    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder builder = new StringBuilder();
    static int n;
    static int[] stairs;
    static int[] dp;

    public void solution() throws IOException {
        n = Integer.parseInt(reader.readLine());
        stairs = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            stairs[i] = Integer.parseInt(reader.readLine());
        }

        dp = new int[n + 1];
        dp[1] = stairs[1];
        if (n > 1) {
            dp[2] = dp[1] + stairs[2];

            for (int i = 3; i <= n; i++) {
                dp[i] = Math.max(dp[i - 2] + stairs[i], dp[i - 3] + stairs[i - 1] + stairs[i]);
            }
        }
        builder.append(dp[n]);
        writer.write(builder.toString());
        writer.flush();
    }

    public static void main(String[] args) throws IOException {
        new two2579().solution();
    }
}
