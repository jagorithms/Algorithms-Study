package com.example.javacodingtest.boj.gold;

import java.io.*;
import java.util.*;

/*
 @author ranuinclulus
 @since 2024.08.30
 @link https://www.acmicpc.net/problem/16234
 @timecomplex
 @performance 297964 KB, 664 MS
 @category
 @note
 */
public class four16234 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static int n;
    static int lowNum;
    static int highNum;
    static int[][] map;
    static int time;
    static int[][] deltas = new int[][] {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    public void solution() throws IOException {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        lowNum = Integer.parseInt(st.nextToken());
        highNum = Integer.parseInt(st.nextToken());

        map = new int[n][n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        time = 0;
        while (true) {

            boolean[][] visited = new boolean[n][n];
            boolean flag = false;

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (visited[i][j]) continue;
                    List<int[]> union = bfs(i, j, visited);

                    if (union.size() > 1) {
                        flag = true;
                        int sum = 0;
                        for(int[] position : union) {
                            sum += map[position[0]][position[1]];
                        }
                        for(int[] position : union) {
                            map[position[0]][position[1]] = sum / union.size();
                        }
                    }

                }
            }

            if (!flag) break;
            time++;
        }
        sb.append(time);
        bw.write(sb.toString());
        bw.flush();
    }

    private List<int[]> bfs(int row, int col, boolean[][] visited) {
        List<int[]> union = new LinkedList<>();
        Deque<int[]> toVisit = new ArrayDeque<>();
        visited[row][col] = true;
        toVisit.add(new int[] {row, col});
        union.add(new int[] {row, col});

        while(!toVisit.isEmpty()) {
            int[] now = toVisit.poll();
            for (int i = 0; i < 4; i++) {
                int nextRow = now[0] + deltas[i][0];
                int nextCol = now[1] + deltas[i][1];

                if (nextRow < 0 || nextRow >= n || nextCol < 0 || nextCol >= n) continue;
                int diff = Math.abs(map[now[0]][now[1]] - map[nextRow][nextCol]);
                if (diff < lowNum || diff > highNum) continue;
                if (visited[nextRow][nextCol]) continue;

                toVisit.add(new int[] {nextRow, nextCol});
                union.add(new int[] {nextRow, nextCol});
                visited[nextRow][nextCol] = true;
            }
        }
        return union;
    }


    public static void main(String[] args) throws IOException {
        new four16234().solution();
    }
}
