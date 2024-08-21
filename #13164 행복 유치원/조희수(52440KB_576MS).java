package com.example.javacodingtest.boj.gold;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 @author ranuinclulus
 @since 2024.08.20
 @link https://www.acmicpc.net/problem/13164
 @timecomplex
 @performance 52440KB, 576MS
 @category
 @note
 */
public class five13164 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static int n;
    static int m;
    static int[] children;
    static int[] costs;
    static int totalCost;

    public void solution() throws IOException {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        children = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            children[i] = Integer.parseInt(st.nextToken());
        }

        costs = new int[n - 1];
        for (int i = 0; i < n - 1; i++) {
            costs[i] = children[i + 1] - children[i];
        }

        Arrays.sort(costs);
        for (int i = 1; i < m; i++) {
            costs[n - i - 1] = 0;
        }

        totalCost = 0;
        for (int i = 0; i < n - 1; i++) {
            totalCost += costs[i];
        }

        sb.append(totalCost);
        bw.write(sb.toString());
        bw.flush();
    }



    public static void main(String[] args) throws IOException {
        new five13164().solution();
    }
}
