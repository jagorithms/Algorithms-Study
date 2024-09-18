package com.example.javacodingtest.boj.gold;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 @author ranuinclulus
 @since 2024.09.06
 @link https://www.acmicpc.net/problem/14890
 @timecomplex
 @performance 15520kb, 120ms
 @category
 @note
 */
public class three14890_2 {
    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder builder = new StringBuilder();
    static StringTokenizer tokenizer;
    static int n, l;
    static int[][] map;
    static int count;

    public void solution() throws IOException {
        tokenizer = new StringTokenizer(reader.readLine());
        n = Integer.parseInt(tokenizer.nextToken());
        l = Integer.parseInt(tokenizer.nextToken());
        map = new int[n][n];

        for (int i = 0; i < n; i++) {
            tokenizer = new StringTokenizer(reader.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(tokenizer.nextToken());
            }
        }

        count = 0;

        // 가로 먼저 검사
        for (int i = 0; i < n; i++) {
            if (checkPossible(map[i])) count++;
        }
        for (int i = 0; i < n; i++) {
            int[] row = new int[n];
            for (int j = 0; j < n; j++) {
                row[j] = map[j][i];
            }
            if (checkPossible(row)) count++;
        }


        builder.append(count);
        writer.write(builder.toString());
        writer.flush();

    }

    private boolean checkPossible(int[] row) {
        boolean[] inclined = new boolean[n];
        for (int i = 0; i < n - 1; i++) {
            int diff = row[i] - row[i + 1];
            if (diff < -1 || diff > 1) return false;
            else if (diff == 1) { //3 2 2 2 2
                for (int j = 1; j <= l; j++) {
                    if (i + j >= n || inclined[i + j]) return false;
                    if (row[i] - 1 != row[i + j]) return false;
                    inclined[i + j] = true;
                }
            } else if (diff == -1) { // 2 2 2 2 3
                for (int j = 0; j < l; j++) {
                    if (i - j < 0 || inclined[i - j]) return false;
                    if (row[i] != row[i - j]) return false;
                    inclined[i - j] = true;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) throws IOException {
        new three14890_2().solution();
    }
}
