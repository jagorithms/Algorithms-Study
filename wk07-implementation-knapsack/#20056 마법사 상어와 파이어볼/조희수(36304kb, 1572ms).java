package com.example.javacodingtest.boj.gold;

import java.io.*;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

/*
 @author ranuinclulus
 @since 2024.09.23
 @link https://www.acmicpc.net/problem/20056
 @timecomplex
 @performance 36304kb, 1572ms
 @category
 @note
 */
public class four20056 {
    class Fireball {
        int row;
        int col;
        int weight;
        int direction;
        int speed;

        public Fireball(int row, int col, int weight, int speed, int direction) {
            this.row = row;
            this.col = col;
            this.weight = weight;
            this.speed = speed;
            this.direction = direction;
        }

        public void move() {
            int nextRow = (row + deltas[direction][0] * speed) % n;
            int nextCol = (col + deltas[direction][1] * speed)% n;
            if (nextRow < 0) nextRow = n + nextRow;
            if (nextCol < 0) nextCol = n + nextCol;
            this.row = nextRow;
            this.col = nextCol;
            map[nextRow][nextCol].add(this);
        }
    }
    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder builder = new StringBuilder();
    static StringTokenizer tokenizer;
    static int n, m, k;
    static int[][] deltas = new int[][]
            {{-1, 0}, {-1, 1}, {0, 1}, {1, 1}, {1, 0}, {1, -1}, {0, -1}, {-1, -1}};
    static List<Fireball> fireballs;
    static List<Fireball>[][] map;

    public void solution() throws IOException {
        tokenizer = new StringTokenizer(reader.readLine());
        n = Integer.parseInt(tokenizer.nextToken());
        m = Integer.parseInt(tokenizer.nextToken());
        k = Integer.parseInt(tokenizer.nextToken());
        map = new List[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                map[i][j] = new LinkedList<>();
            }
        }

        fireballs = new LinkedList<>();
        for (int i = 0; i < m; i++) {
            tokenizer = new StringTokenizer(reader.readLine());
            Fireball fireball = new Fireball(
                    Integer.parseInt(tokenizer.nextToken()) - 1,
                    Integer.parseInt(tokenizer.nextToken()) - 1,
                    Integer.parseInt(tokenizer.nextToken()),
                    Integer.parseInt(tokenizer.nextToken()),
                    Integer.parseInt(tokenizer.nextToken()));
            fireballs.add(fireball);
        }

        while (k --> 0) {
            fireballs.forEach(fireball -> fireball.move());

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {

                    if (map[i][j].size() < 2) {
                        map[i][j].clear();
                        continue;
                    }

                    int totalWeight = 0;
                    int totalSpeed = 0;
                    int oddCount = 0;
                    int evenCount = 0;
                    for (Fireball fireball : map[i][j]) {
                        totalWeight += fireball.weight;
                        totalSpeed += fireball.speed;
                        if (fireball.direction % 2 == 0) evenCount++;
                        else oddCount++;
                        fireballs.remove(fireball);
                    }
                    map[i][j].clear();

                    boolean allSame = !(oddCount != 0 && evenCount != 0);

                    totalWeight /= 5;
                    totalSpeed /= (oddCount + evenCount);
                    if (totalWeight != 0) {
                        int start = 0;
                        if (!allSame) start = 1;

                        for (int l = 0; l < 4; l++) {
                            int newDirection = 2 * l + start;
                            fireballs.add(new Fireball(i, j, totalWeight, totalSpeed, newDirection));
                        }
                    }
                }
            }
        }
        int answer = 0;
        for(Fireball fireball : fireballs) {
            answer += fireball.weight;
        }
        builder.append(answer);
        writer.write(builder.toString());
        writer.flush();
    }

    public static void main(String[] args) throws IOException {
        new four20056().solution();
    }
}
