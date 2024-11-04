package com.example.javacodingtest.boj.gold;

import java.io.*;
import java.util.*;

/*
 @author ranuinclulus
 @since 2024.11.01
 @link https://www.acmicpc.net/problem/2637
 @timecomplex
 @performance 14108kb 104ms
 @category
 @note
 */
public class two2637 {
    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder builder = new StringBuilder();
    static StringTokenizer tokenizer;
    static int n, m;
    static List<List<int[]>> adjList;
    static int[] xCount, yCount, result;

    public void solution() throws IOException {
        n = Integer.parseInt(reader.readLine());
        adjList = new LinkedList<>();

        for (int i = 0; i <= n; i++) {
            adjList.add(new LinkedList<>());
        }

        xCount = new int[n + 1];
        yCount = new int[n + 1];
        m = Integer.parseInt(reader.readLine());
        for (int i = 0; i < m; i++) {
            // 중간 부품이나 완제품 X를 만드는데 중간 부품 혹은 기본 부품 Y가 K개 필요하다
            tokenizer = new StringTokenizer(reader.readLine());
            int x = Integer.parseInt(tokenizer.nextToken());
            int y = Integer.parseInt(tokenizer.nextToken());
            int k = Integer.parseInt(tokenizer.nextToken());
            adjList.get(x).add(new int[] {y, k});
            xCount[x]++;
            yCount[y]++;
        }

        result = new int[n + 1];
        topologySort();

        for (int i = 1; i <= n; i++) {
            if (xCount[i] == 0) builder.append(i).append(" ").append(result[i]).append('\n');
        }
        writer.write(builder.toString());
        writer.flush();
    }

    private void topologySort() {
        Deque<int[]> toVisit = new ArrayDeque<>();
        toVisit.add(new int[] {n, 1});
        result[n] = 1;

        while(!toVisit.isEmpty()) {
            int[] current = toVisit.poll();
            for(int[] pre : adjList.get(current[0])) {
                result[pre[0]] += result[current[0]] * pre[1];
                yCount[pre[0]]--;
                if (yCount[pre[0]] == 0) toVisit.add(new int[] {pre[0], result[pre[0]]});
            }
        }
    }

    public static void main(String[] args) throws IOException {
        new two2637().solution();
    }
}
