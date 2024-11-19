package com.example.javacodingtest.boj.gold;

import java.io.*;
import java.util.StringTokenizer;

/*
 @author ranuinclulus
 @since 2024.11.18
 @link https://www.acmicpc.net/problem/15684
 @timecomplex
 @performance 16940kb 1884ms
 @category
 @note
 */
public class three15684 {
    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder builder = new StringBuilder();
    static StringTokenizer tokenizer;
    static int n, m, h, answer;
    static int[][] map;
    static boolean finish;

    public void solution() throws IOException {
        tokenizer = new StringTokenizer(reader.readLine());
        n = Integer.parseInt(tokenizer.nextToken());
        m = Integer.parseInt(tokenizer.nextToken());
        h = Integer.parseInt(tokenizer.nextToken());

        map = new int[h + 1][n + 1];


        for (int i = 0; i < m; i++) {
            tokenizer = new StringTokenizer(reader.readLine());
            int col = Integer.parseInt(tokenizer.nextToken());
            int row = Integer.parseInt(tokenizer.nextToken());
            map[col][row] = 1;
            map[col][row + 1] = 2;
        }

        for (int i = 0; i <= 3; i++) {
            answer = i;
            dfs(1, 0);
            if (finish) break;
        }
        if (!finish) answer = -1;
        builder.append(answer);
        writer.write(builder.toString());
        writer.flush();
    }

    private void dfs(int col, int count) {
        if (finish) return;
        if (answer == count) {
            if (check()) finish = true;
            return;
        }

        for (int i = col; i <= h; i++) {
            for (int j = 1; j < n; j++) {
                if (map[i][j] == 0 && map[i][j + 1] == 0) {
                    map[i][j] = 1;
                    map[i][j + 1] = 2;
                    dfs(col, count + 1);
                    map[i][j] = 0;
                    map[i][j + 1] = 0;
                }
            }
        }
    }

    private boolean check() {
        for (int i = 1; i <= n; i++) {
            int col = 1;
            int row = i;
            for (int j = 0; j < h; j++) {
                if (map[col][row] == 1) row++;
                else if (map[col][row] == 2) row--;
                col++;
            }
            if (row != i) return false;
        }
        return true;
    }

    public static void main(String[] args) throws IOException {
        new three15684().solution();
    }
}
