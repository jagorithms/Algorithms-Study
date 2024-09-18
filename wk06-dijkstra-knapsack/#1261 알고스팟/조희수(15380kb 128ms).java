package com.example.javacodingtest.boj.gold;

import java.io.*;
import java.util.*;

/*
 @author ranuinclulus
 @since 2024.09.04
 @link https://www.acmicpc.net/problem/1261
 @timecomplex
 @performance 15380kb 128ms
 @category BFS
 @note
 */
public class four1261 {
    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder builder = new StringBuilder();
    static StringTokenizer tokenizer;
    static int n, m;
    static int[][] map;
    static boolean[][] visited;
    static char[] input;
    static int[][] deltas = new int[][] {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    static int minPush = Integer.MAX_VALUE;

    public void solution() throws IOException {
        tokenizer = new StringTokenizer(reader.readLine());
        m = Integer.parseInt(tokenizer.nextToken());
        n = Integer.parseInt(tokenizer.nextToken());
        map = new int[n][m];
        visited = new boolean[n][m];

        for (int i = 0; i < n; i++) {
            input = reader.readLine().toCharArray();
            for (int j = 0; j < m; j++) {
                map[i][j] = Character.getNumericValue(input[j]);
            }
        }

        PriorityQueue<int[]> toVisit = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return Integer.compare(o1[2], o2[2]);
            }
        });

        toVisit.add(new int[] {0, 0, 0});
        visited[0][0] = true;
        while(!toVisit.isEmpty()) {
            int[] now = toVisit.poll();
            if (now[0] == n - 1 && now[1] == m - 1) {
                minPush = Math.min(minPush, now[2]);
                break;
            }

            for (int i = 0; i < 4; i++) {
                int nextRow = now[0] + deltas[i][0];
                int nextCol = now[1] + deltas[i][1];

                if (nextRow < 0 || nextRow >= n || nextCol < 0 || nextCol >= m) continue;
                if (visited[nextRow][nextCol]) continue;

                visited[nextRow][nextCol] = true;
                if (map[nextRow][nextCol] == 1) toVisit.add(new int[] {nextRow, nextCol, now[2] + 1});
                else toVisit.add(new int[] {nextRow, nextCol, now[2]});
            }
        }
        builder.append(minPush);
        writer.write(builder.toString());
        writer.flush();
    }

    public static void main(String[] args) throws IOException {
        new four1261().solution();
    }
}
