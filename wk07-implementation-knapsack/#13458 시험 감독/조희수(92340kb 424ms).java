package com.example.javacodingtest.boj.bronze;

import java.io.*;
import java.util.StringTokenizer;

/*
 @author ranuinclulus
 @since 2024.09.16
 @link https://www.acmicpc.net/problem/13458
 @timecomplex
 @performance
 @category
 @note
 */
public class two13458_2 {
    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder builder = new StringBuilder();
    static StringTokenizer tokenizer;
    static int n, b, c;
    static long answer;
    static int[] tests;

    public void solution() throws IOException {
        n = Integer.parseInt(reader.readLine());
        tests = new int[n];

        tokenizer = new StringTokenizer(reader.readLine());
        for (int i = 0; i < n; i++) {
            tests[i] = Integer.parseInt(tokenizer.nextToken());
        }

        tokenizer = new StringTokenizer(reader.readLine());
        b = Integer.parseInt(tokenizer.nextToken());
        c = Integer.parseInt(tokenizer.nextToken());

        answer = n;
        for (int i = 0; i < n; i++) {
            tests[i] -= b;
            if (tests[i] > 0) answer += (tests[i] % c == 0 ? tests[i] / c : tests[i] / c + 1);
        }
        builder.append(answer);
        writer.write(builder.toString());
        writer.flush();

    }

    public static void main(String[] args) throws IOException {
        new two13458_2().solution();
    }
}
