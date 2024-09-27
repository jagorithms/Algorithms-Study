package com.example.javacodingtest.boj.silver;

import java.io.*;
import java.util.*;

/*
 @author ranuinclulus
 @since
 @link https://www.acmicpc.net/problem/15723
 @timecomplex
 @performance 14068kb, 100ms
 @category
 @note
 */
public class one15723 {
    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder builder = new StringBuilder();
    static StringTokenizer tokenizer;
    static int n, m;
    static List<List<Integer>> edges;

    public void solution() throws IOException {
        n = Integer.parseInt(reader.readLine());
        edges = new LinkedList<>();
        for (int i = 0; i < 26; i++) {
            edges.add(new LinkedList<>());
        }

        for (int i = 0; i < n; i++) {
            String input = reader.readLine();
            int start = (int) input.charAt(0) - 97;
            int end = (int) input.charAt(5) - 97;
            edges.get(start).add(end);
        }

        m = Integer.parseInt(reader.readLine());

        for (int i = 0; i < m; i++) {
            String input = reader.readLine();
            int start = (int) input.charAt(0) - 97;
            int end = (int) input.charAt(5) - 97;

            builder.append(connect(start, end)).append('\n');
        }
        writer.write(builder.toString());
        writer.flush();
    }

    private String connect(int start, int end) {
        Deque<Integer> toVisit = new ArrayDeque<>();
        boolean[] visited = new boolean[26];
        toVisit.add(start);
        visited[start] = true;
        while(!toVisit.isEmpty()) {
            int now = toVisit.poll();
            for(int next : edges.get(now)) {
                if (visited[next]) continue;
                if (next == end) return "T";
                toVisit.add(next);
                visited[next] = true;
            }
        }
        return "F";
    }

    public static void main(String[] args) throws IOException {
        new one15723().solution();
    }
}
