package com.example.javacodingtest.boj.gold;

import java.io.*;
import java.util.StringTokenizer;

/*
 @author ranuinclulus
 @since 2024.09.19
 @link https://www.acmicpc.net/problem/9084
 @timecomplex
 @performance 14036kb 96ms
 @category
 @note
 */
public class five9084_2 {
    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder builder = new StringBuilder();
    static StringTokenizer tokenizer;
    static int testNum, n, m;
    static int[] coins;
    static int[] dp;

    public void solution() throws IOException {
        testNum = Integer.parseInt(reader.readLine());
        for (int test = 0; test < testNum; test++) {


            n = Integer.parseInt(reader.readLine());
            coins = new int[n];

            tokenizer = new StringTokenizer(reader.readLine());
            for (int i = 0; i < n; i++) {
                coins[i] = Integer.parseInt(tokenizer.nextToken());
            }

            m = Integer.parseInt(reader.readLine());
            dp = new int[m + 1];

            for (int coin : coins) {
                for (int i = 1; i <= m; i++) {
                    if (i - coin > 0) {
                        dp[i] += dp[i - coin];
                    } else if (i - coin == 0) {
                        dp[i]++;
                    }
                }
            }
            builder.append(dp[m]).append('\n');
        }
        writer.write(builder.toString());
        writer.flush();
    }

    public static void main(String[] args) throws IOException {
        new five9084_2().solution();
    }
}
