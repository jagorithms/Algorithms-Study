package com.example.javacodingtest.boj.gold;

/*
 @author ranuinclulus
 @since 2024.09.22
 @link
 @timecomplex
 @performance 14564kb, 108ms
 @category
 @note
 */
import java.io.*;
import java.util.*;

public class two19236_2 {
    class Fish {
        int row;
        int col;
        int number;
        int direction;
        boolean isAlive;


        public Fish(int row, int col, int number, int direction, boolean isAlive) {
            this.row = row;
            this.col = col;
            this.number = number;
            this.direction = direction;
            this.isAlive = isAlive;
        }
    }

    class Shark {
        int row;
        int col;
        int direction;
        int eatSum;

        public Shark(int row, int col, int direction, int eatSum) {
            this.row = row;
            this.col = col;
            this.direction = direction;
            this.eatSum = eatSum;
        }
    }
    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder builder = new StringBuilder();
    static StringTokenizer tokenizer;

    // 1부터 순서 대로 ↑, ↖, ←, ↙, ↓, ↘, →, ↗
    static int[] dRow = {-1, -1, 0, 1, 1, 1, 0, -1};
    static int[] dCol = {0, -1, -1, -1, 0, 1, 1, 1};
    static int maxSum;


    public void solution() throws IOException {
        List<Fish> fishes = new ArrayList<>();
        int[][] map = new int[4][4];

        for (int i = 0; i < 4; i++) {
            tokenizer = new StringTokenizer(reader.readLine());
            for (int j = 0; j < 4; j++) {
                int number = Integer.parseInt(tokenizer.nextToken());
                int direction = Integer.parseInt(tokenizer.nextToken()) - 1;
                fishes.add(new Fish(i, j, number, direction, true));
                map[i][j] = number;
            }
        }

        Collections.sort(fishes, new Comparator<Fish>() {
            @Override
            public int compare(Fish o1, Fish o2) {
                return o1.number - o2.number;
            }
        });

        // 상어는 (0, 0) 위치 물고기 먹고 시작
        Fish start = fishes.get(map[0][0] - 1);
        Shark shark = new Shark(0, 0, start.direction, start.number);
        start.isAlive = false;
        map[0][0] = -1;

        dfs(map, shark, fishes);
        builder.append(maxSum);
        writer.write(builder.toString());
        writer.flush();
    }

    // 상어가 이동할 수 없을 때까지 반복
    private void dfs(int[][] map, Shark shark, List<Fish> fishes) {
        if (shark.eatSum > maxSum) {
            maxSum = shark.eatSum;
        }

        fishes.forEach(fish -> moveFish(fish, map, fishes));

        for (int dist = 1; dist < 4; dist++) {
            int nextRow = shark.row + dRow[shark.direction] * dist;
            int nextCol = shark.col + dCol[shark.direction] * dist;

            if (nextRow < 0 || nextRow >= 4 || nextCol < 0 || nextCol >= 4) continue;
            if (map[nextRow][nextCol] <= 0) continue;

            int[][] copyMap = copyMap(map);
            List<Fish> copyFishes = copyFish(fishes);

            copyMap[shark.row][shark.col] = 0;
            Fish nextTarget = copyFishes.get(map[nextRow][nextCol] - 1);
            Shark nextShark = new Shark(nextTarget.row, nextTarget.col, nextTarget.direction, shark.eatSum + nextTarget.number);
            nextTarget.isAlive = false;
            copyMap[nextTarget.row][nextTarget.col] = -1;

            dfs(copyMap, nextShark, copyFishes);
        }

    }

    private List<Fish> copyFish(List<Fish> fishes) {
        List<Fish> result = new ArrayList<>();
        for (Fish fish : fishes) {
            result.add(new Fish(fish.row, fish.col, fish.number, fish.direction, fish.isAlive));
        }
        return result;
    }

    private int[][] copyMap(int[][] map) {
        int[][] result = new int[4][4];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                result[i][j] = map[i][j];
            }
        }
        return result;
    }

    private void moveFish(Fish fish, int[][] map, List<Fish> fishes) {
        if (!fish.isAlive) return;

        for (int i = 0; i < 8; i++) {
            int nextDirection = (fish.direction + i) % 8;
            int nextRow = fish.row + dRow[nextDirection];
            int nextCol = fish.col + dCol[nextDirection];

            // 범위 밖
            if (nextRow < 0 || nextRow >= 4 || nextCol < 0 || nextCol >= 4) continue;
            // 상어가 있는 곳
            if (map[nextRow][nextCol] == -1) continue;

            map[fish.row][fish.col] = 0;
            // 이동 가능한 칸이 빈칸이면
            if (map[nextRow][nextCol] == 0) {
                fish.row = nextRow;
                fish.col = nextCol;
            } else { // 빈칸이 아니라면 교환
                Fish temp = fishes.get(map[nextRow][nextCol] - 1);
                temp.row = fish.row;
                temp.col = fish.col;
                map[fish.row][fish.col] = temp.number;

                fish.row = nextRow;
                fish.col = nextCol;
            }
            map[nextRow][nextCol] = fish.number;
            fish.direction = nextDirection;
            return;
        }
    }

    public static void main(String[] args) throws IOException {
        new two19236_2().solution();
    }
}
