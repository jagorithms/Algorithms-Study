package com.example.javacodingtest.boj.gold;

import java.io.*;
import java.util.*;

/*
 @author ranuinclulus
 @since 2024.10.28
 @link https://www.acmicpc.net/problem/17089
 @timecomplex
 @performance 33804kb 292ms
 @category
 @note
 */
public class five17089 {
    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder builder = new StringBuilder();
    static StringTokenizer tokenizer;
    static int n, m;
    static boolean[][] friends;
    static int[] friendsNum;
    static int minValue = Integer.MAX_VALUE;

    public void solution() throws IOException {
        tokenizer = new StringTokenizer(reader.readLine());
        n = Integer.parseInt(tokenizer.nextToken());
        m = Integer.parseInt(tokenizer.nextToken());

        friends = new boolean[n][n];
        friendsNum = new int[n];

        for (int i = 0; i < m; i++) {
            tokenizer = new StringTokenizer(reader.readLine());
            int a = Integer.parseInt(tokenizer.nextToken()) - 1;
            int b = Integer.parseInt(tokenizer.nextToken()) - 1;
            friends[a][b] = true;
            friends[b][a] = true;
            friendsNum[a]++;
            friendsNum[b]++;
        }

        for (int first = 0; first < n; first++) {
            for (int second = first + 1; second < n; second++) {
                if (!friends[first][second]) continue;
                for (int third = second + 1; third < n; third++) {
                    if (!friends[first][third] || !friends[second][third]) continue;
                    int count = friendsNum[first] + friendsNum[second] + friendsNum[third] - 6;
                    minValue = Math.min(minValue, count);
                }
            }
        }
        if (minValue == Integer.MAX_VALUE) minValue = -1;
        builder.append(minValue);
        writer.write(builder.toString());
        writer.flush();

    }



    public static void main(String[] args) throws IOException {
        new five17089().solution();
    }
}
