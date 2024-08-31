package com.example.javacodingtest.boj.silver;

import java.io.*;
import java.util.*;

/*
 @author ranuinclulus
 @since 2024.08.31
 @link https://www.acmicpc.net/problem/18352
 @timecomplex
 @performance 356528 KB, 1504 MS
 @category
 @note
 */
public class two18352 {
    class Node implements Comparable<Node>{
        int index;
        int distance;

        public Node(int index, int distance) {
            this.index = index;
            this.distance = distance;
        }

        @Override
        public int compareTo(Node o) {
            return Integer.compare(this.distance, o.distance);
        }
    }
    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder builder = new StringBuilder();
    static StringTokenizer tokenizer;
    static int n;
    static int m;
    static int k;
    static int x;
    static List<Node>[] edges;
    static List<Integer> result;
    static boolean[] visited;
    static int[] distance;


    public void solution() throws IOException {
        tokenizer = new StringTokenizer(reader.readLine());
        n = Integer.parseInt(tokenizer.nextToken());
        m = Integer.parseInt(tokenizer.nextToken());
        k = Integer.parseInt(tokenizer.nextToken());
        x = Integer.parseInt(tokenizer.nextToken());

        edges = new List[n + 1];
        for (int i = 0; i <= n; i++) {
            edges[i] = new LinkedList<>();
        }

        for (int i = 0; i < m; i++) {
            tokenizer = new StringTokenizer(reader.readLine());
            edges[Integer.parseInt(tokenizer.nextToken())].add(new Node(Integer.parseInt(tokenizer.nextToken()), 1));
        }

        result = new LinkedList<>();
        visited = new boolean[n + 1];
        distance = new int[n + 1];
        Arrays.fill(distance, Integer.MAX_VALUE);
        
        bfs(x);

        boolean isFind = false;
        for (int i = 1; i <= n; i++) {
            if (distance[i] == k) {
                isFind = true;
                builder.append(i).append('\n');
            }
        }

        if (!isFind) builder.append(-1);

        writer.write(builder.toString());
        writer.flush();

    }

    private void bfs(int start) {
        PriorityQueue<Node> priorityQueue = new PriorityQueue<>();
        priorityQueue.add(new Node(start, 0));
        distance[start] = 0;
        while (!priorityQueue.isEmpty()) {
            Node now = priorityQueue.poll();
            if (visited[now.index]) continue;

            visited[now.index] = true;
            for(Node next : edges[now.index]) {
                if (visited[next.index]) continue;
                if (distance[next.index] < (distance[now.index] + next.distance)) continue;
                distance[next.index] = (distance[now.index] + next.distance);
                priorityQueue.add(new Node(next.index, distance[next.index]));
            }
        }
    }

    public static void main(String[] args) throws IOException {
        new two18352().solution();
    }
}
