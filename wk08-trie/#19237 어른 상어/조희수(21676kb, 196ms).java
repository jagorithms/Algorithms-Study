package com.example.javacodingtest.boj.gold;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;


/*
 @author ranuinclulus
 @since 2024.09.26
 @link
 @timecomplex
 @performance 21676kb, 196ms
 @category
 @note
 */
public class two19237_2 {
    class Shark {
        int row;
        int col;
        int direction;
        int[][] priority = new int[5][4];

        public Shark(int row, int col, int direction) {
            this.row = row;
            this.col = col;
            this.direction = direction;
        }
    }

    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder builder = new StringBuilder();
    static StringTokenizer tokenizer;
    static int n, m, k, time;
    // 위, 아래, 왼쪽, 오른쪽
    static int[] dRow = {0, -1, 1, 0, 0};
    static int[] dCol = {0, 0, 0, -1, 1};
     static int[][] restTime;
    static int[][] smell;
    static Shark[] sharks;

    public void solution() throws IOException {
        tokenizer = new StringTokenizer(reader.readLine());
        n = Integer.parseInt(tokenizer.nextToken());
        m = Integer.parseInt(tokenizer.nextToken());
        k = Integer.parseInt(tokenizer.nextToken());

        restTime = new int[n + 1][n + 1];
        smell = new int[n + 1][n + 1];
        sharks = new Shark[m + 1];

        for (int i = 1; i <= n; i++) {
            tokenizer = new StringTokenizer(reader.readLine());
            for (int j = 1; j <= n; j++) {
                int value = Integer.parseInt(tokenizer.nextToken());
                if (value > 0) {
                    sharks[value] = new Shark(i, j, 0);
                    restTime[i][j] = k;
                    smell[i][j] = value;
                }
            }
        }

        tokenizer = new StringTokenizer(reader.readLine());
        for (int i = 1; i <= m; i++) {
            sharks[i].direction = Integer.parseInt(tokenizer.nextToken());
        }

        for(int i = 1; i <= m; i++) {
            for (int j = 1; j <= 4; j++) {
                tokenizer = new StringTokenizer(reader.readLine());
                for (int k = 0; k < 4; k++) {
                    sharks[i].priority[j][k] = Integer.parseInt(tokenizer.nextToken());
                }
            }
        }

        time = 0;
        while (true) {
            int count = 0;
            for (int i = 1; i <= m; i++) {
                if (sharks[i] != null) count++;
            }

            if (count == 1 && sharks[1] != null) {
                break;
            }

            if (time >= 1000) {
                time = -1;
                break;
            }

            int[][] temp = new int[n + 1][n + 1];

            for (int i = 1; i <= m; i++) {
                if (sharks[i] != null) moveShark(temp, i);
            }

            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    if (restTime[i][j] > 0) restTime[i][j]--;
                    if (restTime[i][j] == 0) smell[i][j] = 0;
                }
            }

            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    if (temp[i][j] > 0) {
                        restTime[i][j] = k;
                        smell[i][j] = temp[i][j];
                    }
                }
            }
            time++;
        }

        builder.append(time);
        writer.write(builder.toString());
        writer.flush();
    }

    private void moveShark(int[][] temp, int index) {
        int nextRow = 0;
        int nextCol = 0;
        int newDirection = 0;
        boolean flag = false;

        for (int i = 0; i < 4; i++) {
            newDirection = sharks[index].priority[sharks[index].direction][i];
            nextRow = sharks[index].row + dRow[newDirection];
            nextCol = sharks[index].col + dCol[newDirection];

            if (nextRow > 0 && nextRow <= n && nextCol > 0 && nextCol <= n && smell[nextRow][nextCol] == 0) {
                flag = true;
                break;
            }
        }

        if (!flag) {
            for (int i = 0; i < 4; i++) {
                newDirection = sharks[index].priority[sharks[index].direction][i];
                nextRow = sharks[index].row + dRow[newDirection];
                nextCol = sharks[index].col + dCol[newDirection];

                if (nextRow > 0 && nextRow <= n && nextCol > 0 && nextCol <= n && smell[nextRow][nextCol] == index) {
                    flag = true;
                    break;
                }
            }
        }

        if (temp[nextRow][nextCol] == 0) {
            temp[nextRow][nextCol] = index;
            sharks[index].row = nextRow;
            sharks[index].col = nextCol;
            sharks[index].direction = newDirection;
        } else {
            sharks[index] = null;
        }
    }


    public static void main(String[] args) throws IOException {
        new two19237_2().solution();
    }
}
