package com.example.javacodingtest.boj.silver;

import java.io.*;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 @author ranuinclulus
 @since 2024.09.01
 @link https://www.acmicpc.net/problem/10971
 @timecomplex
 @performance 15664 KB, 428 MS
 @category
 @note
 */
public class two10971 {
    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder builder = new StringBuilder();
    static StringTokenizer tokenizer;
    static int n;
    static int[][] map;
    static boolean[] visited;
    static long minCost = Long.MAX_VALUE;

    public void solution() throws IOException {
        n = Integer.parseInt(reader.readLine());
        map = new int[n][n];
        for (int i = 0; i < n; i++) {
            tokenizer = new StringTokenizer(reader.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(tokenizer.nextToken());
            }
        }

        for (int i = 0; i < n; i++) {
            visited = new boolean[n];
            visited[i] = true;
            dfs(i, i, 0);
        }
        builder.append(minCost);
        writer.write(builder.toString());
        writer.flush();
    }

    private void dfs(int start, int now, long cost) {
        if (allVsited()) {
            if (map[now][start] != 0) {
                minCost = Math.min(minCost, cost + map[now][start]);
            }

        }
        for (int i = 0; i < n; i++) {
            if (visited[i]) continue;
            if (map[now][i] == 0) continue;
            visited[i] = true;
            dfs(start, i, cost + map[now][i]);
            visited[i] = false;
        }
    }

    private boolean allVsited() {
        for(boolean visit : visited) {
            if (!visit) return false;
        }
        return true;
    }


    public static void main(String[] args) throws IOException {
        new two10971().solution();
    }
}
