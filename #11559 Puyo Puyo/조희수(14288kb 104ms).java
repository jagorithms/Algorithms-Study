package com.example.javacodingtest.boj.gold;

import java.io.*;
import java.util.*;

/*
 @author ranuinclulus
 @since 2024.09.12
 @link https://www.acmicpc.net/problem/11559
 @timecomplex
 @performance 14288kb 104ms
 @category
 @note
 */
public class four11559 {
    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder builder = new StringBuilder();
    static char[][] puyo;
    static char color;
    static int[][] deltas = new int[][] {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    static int count;
    static boolean isPuyo;

    public void solution() throws IOException {
        puyo = new char[12][6];
        for (int i = 0; i < 12; i++) {
            puyo[i] = reader.readLine().toCharArray();
        }

        while(true) {
            for(char[] row : puyo) {
                System.out.println(Arrays.toString(row));
            }
            System.out.println();
            boolean isContinue = game();
            onFloor();
            if (!isContinue) break;
            count++;
        }

        builder.append(count);
        writer.write(builder.toString());
        writer.flush();
    }

    private void onFloor() {
        for (int i = 0; i < 6; i++) {
            falling(i);
        }
    }

    private boolean game() {
        boolean[][] visited = new boolean[12][6];
        Deque<int[]> toVisit = new ArrayDeque<>();
        boolean isPop = false;

        for (int i = 0; i < 12; i++) {
            for (int j = 0; j < 6; j++) {
                List<int[]> result = new LinkedList<>();
                if (puyo[i][j] == '.') continue;
                if (visited[i][j]) continue;

                visited[i][j] = true;
                toVisit.add(new int[] {i, j});
                result.add(new int[] {i, j});

                while (!toVisit.isEmpty()) {
                    int[] now = toVisit.poll();
                    color = puyo[now[0]][now[1]];
                    for (int d = 0; d < 4; d++) {
                        int nextRow = now[0] + deltas[d][0];
                        int nextCol = now[1] + deltas[d][1];

                        if (nextRow < 0 || nextRow >= 12 || nextCol < 0 || nextCol >= 6) continue;
                        if (visited[nextRow][nextCol]) continue;
                        if (puyo[nextRow][nextCol] != color) continue;

                        toVisit.add(new int[] {nextRow, nextCol});
                        result.add(new int[] {nextRow, nextCol});
                        visited[nextRow][nextCol] = true;
                    }
                }

                if (result.size() >= 4) {
                    isPuyo = true;
                    for (int k = 0; k < result.size(); k++) {
                        puyo[result.get(k)[0]][result.get(k)[1]] = '.';
                        isPop = true;
                    }
                }
            }
        }
        return isPop;
    }

    private void falling(int col) {;
        Deque<Character> puyoQueue = new ArrayDeque<>();
        for (int i = 11; i >= 0; i--) {
            if (puyo[i][col] != '.') {
                puyoQueue.add(puyo[i][col]);
            }
            puyo[i][col] = '.';
        }

        for (int i = 11; i >= 0; i--) {
            if (puyoQueue.isEmpty()) break;
            puyo[i][col] = puyoQueue.poll();
        }
    }

    public static void main(String[] args) throws IOException {
        new four11559().solution();
    }
}
