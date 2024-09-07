package com.example.javacodingtest.boj.gold;

import java.io.*;
import java.util.*;

/*
 @author ranuinclulus
 @since 2024.09.06
 @link https://www.acmicpc.net/problem/4386
 @timecomplex
 @performance 15624kb, 144ms
 @category Prim
 @note
 */
public class three4386 {
    class Edge implements Comparable<Edge> {
        int node;
        double cost;

        public Edge(int node, double cost) {
            this.node = node;
            this.cost = cost;
        }

        @Override
        public int compareTo(Edge o) {
            return Double.compare(cost, o.cost);
        }
    }
    class Star {
        double x;
        double y;

        public Star(double x, double y) {
            this.x = x;
            this.y = y;
        }
    }
    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder builder = new StringBuilder();
    static StringTokenizer tokenizer;
    static int n;
    static Star[] stars;
    static List<List<Edge>> distances;
    static boolean[] visited;
    static double total;

    public void solution() throws IOException {
        n = Integer.parseInt(reader.readLine());
        stars = new Star[n];
        for (int i = 0; i < n; i++) {
            tokenizer = new StringTokenizer(reader.readLine());
            stars[i] = new Star(Double.parseDouble(tokenizer.nextToken()), Double.parseDouble(tokenizer.nextToken()));
        }
        distances = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            distances.add(new LinkedList<>());
        }

        for (int i = 0; i < n - 1; i++) {
            for (int j = i; j < n; j++) {
                double dist = Math.sqrt(Math.pow(stars[i].x - stars[j].x, 2) + Math.pow(stars[i].y - stars[j].y, 2));
                distances.get(i).add(new Edge(j, dist));
                distances.get(j).add(new Edge(i, dist));
            }
        }

        visited = new boolean[n];
        PriorityQueue<Edge> toVisit = new PriorityQueue<>();
        toVisit.add(new Edge(0, 0));
        while(!toVisit.isEmpty()) {
            Edge now = toVisit.poll();

            if(visited[now.node]) continue;

            visited[now.node] = true;
            total += now.cost;

            for (Edge edge : distances.get(now.node)) {
                if (visited[edge.node]) continue;
                toVisit.add(edge);
            }
        }
        builder.append(String.format("%.2f", total));
        writer.write(builder.toString());
        writer.flush();

    }

    public static void main(String[] args) throws IOException {
        new three4386().solution();
    }
}
