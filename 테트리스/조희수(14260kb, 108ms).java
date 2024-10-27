package com.example.javacodingtest.boj.gold;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

/*
 @author ranuinclulus
 @since 2024.10.26.
 @link https://www.acmicpc.net/problem/3019
 @timecomplex
 @performance 14260kb, 108ms
 @category
 @note
 */
public class five3019 {
    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder builder = new StringBuilder();
    static StringTokenizer tokenizer;
    static int c, p, answer;
    static int[] map;
    // 각 블럭 번호마다 돌렸을 때 어떤 모양이어야 테트리스가 가능해지는지
    static List<List<int[]>> tetris = new ArrayList<>(Arrays.asList(
            Arrays.asList(new int[] {0}, new int[] {0, 0, 0, 0}),
            Arrays.asList(new int[] {0, 0}),
            Arrays.asList(new int[] {0, 0, 1}, new int[] {0, -1}),
            Arrays.asList(new int[] {0, -1, -1}, new int[] {0, 1}),
            Arrays.asList(new int[] {0, 0, 0}, new int[] {0, -1}, new int[] {0, 1}, new int[] {0, -1, 0}),
            Arrays.asList(new int[] {0, 0, 0}, new int[] {0, 0}, new int[] {0, -2}, new int[] {0, 1, 1}),
            Arrays.asList(new int[] {0, 0, 0}, new int[] {0, 0}, new int[] {0, 2}, new int[] {0, 0, -1})));

    public void solution() throws IOException {
        tokenizer = new StringTokenizer(reader.readLine());
        c = Integer.parseInt(tokenizer.nextToken());
        p = Integer.parseInt(tokenizer.nextToken()) - 1;
//        map = new int[111][c];
//
//        tokenizer = new StringTokenizer(reader.readLine());
//        for (int i = 0; i < c; i++) {
//            int number = Integer.parseInt(tokenizer.nextToken());
//            for (int j = 110; j > 110 - number; j--) {
//                map[j][i] = 1;
//            }
//        }
//
//        for(int[] row : map) {
//            System.out.println(Arrays.toString(row));
//        }
        map = new int[c];
        tokenizer = new StringTokenizer(reader.readLine());
        for (int i = 0; i < c; i++) {
            map[i] = Integer.parseInt(tokenizer.nextToken());
        }

        List<int[]> floors = tetris.get(p);

        for (int i = 0; i < c; i++) { // i : 시작점
            for(int[] floor : floors) { // floor: 회전마다의 블록 모양
                int length = floor.length; // length: 블록의 길이
                if (i + length > c) continue; // 시작점 + 블록 길이가 맵을 벗어난다면 패스
                int height = map[i]; // 시작점의 높이

                boolean signal = true;
                for (int j = 0; j < length; j++) { // 블록마다 돌아가면서
                    if ((map[i + j] - height) != floor[j]) {
                        signal = false;
                        break;
                    }
                }
                if (signal) answer++;
            }
        }
        builder.append(answer);
        writer.write(builder.toString());
        writer.flush();

    }

    public static void main(String[] args) throws IOException {
        new five3019().solution();
    }
}
