package com.example.javacodingtest.boj.gold;

import java.io.*;
import java.util.*;

/*
 @author ranuinclulus
 @since 2024.10.26
 @link https://www.acmicpc.net/problem/16947
 @timecomplex
 @performance 143900kb 732ms
 @category
 @note
 */
public class three16947 {
    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder builder = new StringBuilder();
    static StringTokenizer tokenizer;
    static int n;
    static List<Integer>[] subways;
    static boolean[] isCyled;
    static int[] distance;

    public void solution() throws IOException {
        n = Integer.parseInt(reader.readLine());
        subways = new List[n];

        for (int i = 0; i < n; i++) {
            subways[i] = new LinkedList<>();
        }
        for (int i = 0; i < n; i++) {
            tokenizer = new StringTokenizer(reader.readLine());
            int from = Integer.parseInt(tokenizer.nextToken()) - 1;
            int to = Integer.parseInt(tokenizer.nextToken()) - 1;
            subways[from].add(to);
            subways[to].add(from);
        }

        isCyled = new boolean[n];
        for (int i = 0; i < n; i++) {
            if (checkCycle(i, i, i)) break;
        }

        distance = new int[n];
        for (int i = 0; i < n; i++) {
            if (isCyled[i]) continue;
            distance[i] = checkDistance(i);
        }

        for (int i = 0; i < n; i++) {
            builder.append(distance[i]).append(" ");
        }
        writer.write(builder.toString());
        writer.flush();
    }

    private int checkDistance(int node) {
        Deque<int[]> toVisit = new ArrayDeque<>();
        boolean[] visited = new boolean[n];

        toVisit.add(new int[] {node, 0});
        visited[node] = true;

        while(!toVisit.isEmpty()) {
            int[] now = toVisit.poll();
            if (isCyled[now[0]]) return now[1];

            for(int next : subways[now[0]]) {
                if (visited[next]) continue;

                toVisit.add(new int[] {next, now[1] + 1});
                visited[next] = true;
            }
        }
        return -1;
    }

    public static boolean checkCycle(int prev, int node, int start) {
        isCyled[node] = true;

        for(int next : subways[node]) {
            if(!isCyled[next]) {
                if(checkCycle(node, next, start)) return true;
            } else if(next != prev && next == start) return true;

        }
        isCyled[node] = false;
        return false;
    }

    public static void main(String[] args) throws IOException {
        new three16947().solution();
    }
}
