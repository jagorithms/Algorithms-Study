package com.example.javacodingtest.boj.gold;

import java.io.*;
import java.util.*;

/*
 @author ranuinclulus
 @since 2024.09.22
 @link https://www.acmicpc.net/problem/16236
 @timecomplex
 @performance 302452kb, 1032ms
 @category
 @note
 */
public class three16236 {
    class Shark {
        int row;
        int col;
        int size;
        int eatingCount;
        PriorityQueue<Fish> canEat;

        public Shark(int row, int col) {
            this.row = row;
            this.col = col;
            this.size = 2;
            this.eatingCount = 0;
        }

        public void searchFishes() {
            this.canEat = new PriorityQueue<>();
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (ocean[i][j] < shark.size && ocean[i][j] != 0) {
                        int distance = calculateDistance(i, j);
                        if (distance == -1) continue;
                        canEat.add(new Fish(i, j, ocean[i][j], distance));
                    }
                }
            }
        }

        public void printCanEat() {
            for(Fish fish : canEat) {
                System.out.println(fish);
            }
        }

        public void eatFish() {
            eatingCount++;
            if (eatingCount == size) {
                size++;
                eatingCount = 0;
            }
        }
    }

    private int calculateDistance(int row, int col) {
        Deque<int[]> toVisit = new ArrayDeque<>();
        boolean[][] visited = new boolean[n][n];
        toVisit.add(new int[] {shark.row, shark.col, 0});
        visited[shark.row][shark.col] = true;

        while(!toVisit.isEmpty()) {
            int[] now = toVisit.poll();
            if (now[0] == row && now[1] == col) return now[2];

            for (int i = 0; i < 4; i++) {
                int nextRow = now[0] + deltas[i][0];
                int nextCol = now[1] + deltas[i][1];

                if (nextRow < 0 || nextRow >= n || nextCol < 0 || nextCol >= n) continue;
                if (visited[nextRow][nextCol]) continue;
                if (ocean[nextRow][nextCol] > shark.size) continue;

                toVisit.add(new int[] {nextRow, nextCol, now[2] + 1});
                visited[nextRow][nextCol] = true;
            }
        }
        return -1;
    }

    class Fish implements Comparable<Fish>{
        int row;
        int col;
        int size;
        int distance;

        public Fish(int row, int col, int size, int distance) {
            this.row = row;
            this.col = col;
            this.size = size;
            this.distance = distance;
        }

        @Override
        public int compareTo(Fish o) {
            if (this.distance == o.distance) {
                if (this.row == o.row) return Integer.compare(this.col, o.col);
                else return Integer.compare(this.row, o.row);
            }
            else return Integer.compare(this.distance, o.distance);
        }
    }
    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder builder = new StringBuilder();
    static StringTokenizer tokenizer;
    static int[][] deltas = new int[][] {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    static int n, time;
    static int[][] ocean;
    static Shark shark;


    public void solution() throws IOException {
        n = Integer.parseInt(reader.readLine());
        ocean = new int[n][n];
        for (int i = 0; i < n; i++) {
            tokenizer = new StringTokenizer(reader.readLine());
            for (int j = 0; j < n; j++) {
                ocean[i][j] = Integer.parseInt(tokenizer.nextToken());
                if (ocean[i][j] == 9) {
                    shark = new Shark(i, j);
                }
            }
        }


        time = 0;
        while(true) {
            shark.searchFishes();
            if (shark.canEat.size() == 0) break;

            Fish prey = shark.canEat.poll();
            time += prey.distance;
            ocean[prey.row][prey.col] = 9;
            ocean[shark.row][shark.col] = 0;
            shark.row = prey.row;
            shark.col = prey.col;
            shark.eatFish();
        }

        builder.append(time);
        writer.write(builder.toString());
        writer.flush();
    }

    public static void main(String[] args) throws IOException {
        new three16236().solution();
    }
}
