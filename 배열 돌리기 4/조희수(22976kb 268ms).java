package com.example.javacodingtest.boj.gold;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 @author ranuinclulus
 @since 2024.10.25
 @link https://www.acmicpc.net/problem/17406
 @timecomplex
 @performance 22976kb 268ms
 @category
 @note
 */
public class four17406 {
    class Spin{
        int r;
        int c;
        int s;

        public Spin(int r, int c, int s) {
            this.r = r;
            this.c = c;
            this.s = s;
        }
    }
    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder builder = new StringBuilder();
    static StringTokenizer tokenizer;
    static int n, m, k, r, c, s;
    static int minValue = Integer.MAX_VALUE;
    static int[][] array;
    static int[][] arrayCopy;
    static Spin[] spins;
    static int[] permutation;
    static boolean[] visited;

    public void solution() throws IOException {
        tokenizer = new StringTokenizer(reader.readLine());
        n = Integer.parseInt(tokenizer.nextToken());
        m = Integer.parseInt(tokenizer.nextToken());
        k = Integer.parseInt(tokenizer.nextToken());

        permutation = new int[k];
        visited = new boolean[k];

        array = new int[n][m];
        arrayCopy = new int[n][m];
        spins = new Spin[k];

        for (int i = 0; i < n; i++) {
            tokenizer = new StringTokenizer(reader.readLine());
            for (int j = 0; j < m; j++) {
                array[i][j] = Integer.parseInt(tokenizer.nextToken());
            }
        }

        for (int i = 0; i < k; i++) {
            tokenizer = new StringTokenizer(reader.readLine());
            spins[i] = new Spin(Integer.parseInt(tokenizer.nextToken()) - 1,
                    Integer.parseInt(tokenizer.nextToken()) - 1,
                    Integer.parseInt(tokenizer.nextToken()));
        }

        makePermutation(0);

        builder.append(minValue);
        writer.write(builder.toString());
        writer.flush();
    }

    private void makePermutation(int depth) {
        if (depth == k) {
            spinArrays();
        }

        for (int i = 0; i < k; i++) {
            if (visited[i]) continue;
            visited[i] = true;
            permutation[depth] = i;
            makePermutation(depth + 1);
            visited[i] = false;
        }
    }

    private void spinArrays() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                arrayCopy[i][j] = array[i][j];
            }
        }

        for (int i = 0; i < k; i++) {
            Spin now = spins[permutation[i]];
            r = now.r;
            c = now.c;
            s = now.s;

            for(int padding = 1; padding <= s; padding++) {
                // 위
                int upTemp = arrayCopy[r - padding][c + padding];
                for (int j = c + padding; j > c - padding; j--) {
                    arrayCopy[r - padding][j] = arrayCopy[r - padding][j - 1];
                }
                // 오른쪽
                int rightTemp = arrayCopy[r + padding][c + padding];
                for (int j = r + padding; j > r - padding; j--) {
                    arrayCopy[j][c + padding] = arrayCopy[j - 1][c + padding];
                }
                arrayCopy[r - padding + 1][c + padding] = upTemp;
                // 아래
                int downTemp = arrayCopy[r + padding][c - padding];
                for (int j = c - padding; j < c + padding; j++) {
                    arrayCopy[r + padding][j] = arrayCopy[r + padding][j + 1];
                }
                arrayCopy[r + padding][c + padding - 1] = rightTemp;
                // 왼
                for (int j = r - padding; j < r + padding; j++) {
                    arrayCopy[j][c - padding] = arrayCopy[j + 1][c - padding];
                }
                arrayCopy[r + padding - 1][c - padding] = downTemp;
            }
        }

        for (int j = 0; j < n; j++) {
            int sum = 0;
            for (int l = 0; l < m; l++) {
                sum += arrayCopy[j][l];
            }
            minValue = Math.min(minValue, sum);
        }
    }

    public static void main(String[] args) throws IOException {
        new four17406().solution();
    }
}
