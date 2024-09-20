package com.example.javacodingtest.boj.gold;

import java.io.*;
import java.util.*;

/*
 @author ranuinclulus
 @since
 @link https://www.acmicpc.net/problem/17471
 @timecomplex
 @performance 18216kb, 148ms
 @category
 @note
 */
public class three17471 {
    class Election {
        List<Integer> districts = new LinkedList<>();

        public boolean connect() {
            Deque<Integer> toVisit = new ArrayDeque<>();
            boolean[] visited = new boolean[n + 1];

            toVisit.add(districts.get(0));
            visited[districts.get(0)] = true;

            while (!toVisit.isEmpty()) {
                int now = toVisit.poll();
                for (int next : edges.get(now)) {
                    if (!districts.contains(next)) continue;
                    if (visited[next]) continue;
                    visited[next] = true;
                    toVisit.add(next);
                }
            }

            for(int point : districts) {
                if (!visited[point]) return false;
            }
            return true;
        }

        public int totalPop() {
            int totalPop = 0;
            for (int point : districts) {
                totalPop += population[point];
            }
            return totalPop;
        }
    }

    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder builder = new StringBuilder();
    static StringTokenizer tokenizer;
    static int n, edgeNum;
    static int[] population;
    static List<List<Integer>> edges;
    static Election one;
    static Election two;
    static int minDiff = Integer.MAX_VALUE;


    public void solution() throws IOException {
        n = Integer.parseInt(reader.readLine());
        population = new int[n + 1];

        tokenizer = new StringTokenizer(reader.readLine());
        for (int i = 1; i <= n; i++) {
            population[i] = Integer.parseInt(tokenizer.nextToken());
        }

        edges = new LinkedList<>();
        for (int i = 0; i <= n; i++) {
            edges.add(new LinkedList<>());
        }

        for (int i = 1; i <= n; i++) {
            tokenizer = new StringTokenizer(reader.readLine());
            edgeNum = Integer.parseInt(tokenizer.nextToken());
            for (int j = 0; j < edgeNum; j++) {
                int edge = Integer.parseInt(tokenizer.nextToken());
                edges.get(i).add(edge);
                edges.get(edge).add(i);
            }
        }

        makeCombination(0, 0, 0);
        if (minDiff == Integer.MAX_VALUE) minDiff = -1;
        builder.append(minDiff);
        writer.write(builder.toString());
        writer.flush();

    }

    private void makeCombination(int depth, int start, int bitmask) {
        if (depth == n) {
            if (bitmask == 0 || bitmask == (int) Math.pow(2, n) - 1) return;
            makeElectionDistrict(bitmask);
            return;
        }
        if (start > n) return;

        makeCombination(depth + 1, start + 1, bitmask);
        makeCombination(depth + 1, start + 1, (bitmask | (1 << start)));
    }

    private void makeElectionDistrict(int bitmask) {
        one = new Election();
        two = new Election();

        for (int i = 0; i < n; i++) {
            if ((bitmask & (1 << i)) != 0) {
                one.districts.add(i + 1);
            } else {
                two.districts.add(i + 1);
            }
        }

        if (!one.connect() || !two.connect()) return;
        minDiff = Math.min(minDiff, Math.abs(one.totalPop() - two.totalPop()));
    }

    public static void main(String[] args) throws IOException {
        new three17471().solution();
    }
}
