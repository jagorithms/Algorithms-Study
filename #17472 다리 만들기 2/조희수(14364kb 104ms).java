package com.example.javacodingtest.boj.gold;

import java.io.*;
import java.util.*;

/*
 @author ranuinclulus
 @since 2024.09.17
 @link https://www.acmicpc.net/problem/17472
 @timecomplex
 @performance
 @category
 @note
 */
public class one17472 {
    class Node {
        int row;
        int col;

        public Node(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }

    class Edge implements Comparable<Edge> {
        int start;
        int end;
        int cost;

        public Edge(int start, int end, int cost) {
            this.start = start;
            this.end = end;
            this.cost = cost;
        }

        @Override
        public int compareTo(Edge o) {
            return Integer.compare(this.cost, o.cost);
        }
    }
    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder builder = new StringBuilder();
    static StringTokenizer tokenizer;
    static int n, m;
    static int[][] map;
    static List<Node>[] islands;
    static boolean[][] visited;
    static int[][] deltas = new int[][] {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    static PriorityQueue<Edge> edges;
    static int[] parents;
    static int minCost;
    public void solution() throws IOException {
        tokenizer = new StringTokenizer(reader.readLine());
        n = Integer.parseInt(tokenizer.nextToken());
        m = Integer.parseInt(tokenizer.nextToken());
        map = new int[n][m];
        visited = new boolean[n][m];

        for (int i = 0; i < n; i++) {
            tokenizer = new StringTokenizer(reader.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(tokenizer.nextToken());
            }
        }

        islands = new List[7];
        int num = 1;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (!visited[i][j] && map[i][j] == 1) {
                    islands[num] = new LinkedList<>();
                    bfs(i, j, num);
                    num++;
                }
            }
        }

        edges = new PriorityQueue<>();
        for (int i = 1; i < num; i++) {
            for (int j = 0; j < islands[i].size(); j++) {
                Node island = islands[i].get(j);
                for (int k = 0; k < 4; k++) {
                    findEdge(island.row, island.col, i, k, -1);
                }
            }
        }

        builder.append(kruskal(num));
        writer.write(builder.toString());
        writer.flush();
    }
    public int kruskal(int num) {
        parents = new int[num];
        for(int i = 1; i < num; i++) {
            parents[i] = i;
        }

        boolean[] link = new boolean[num];
        int result = 0;
        int bridge = 0;
        while(!edges.isEmpty()) {
            Edge current = edges.poll();

            int p1 = find(current.start);
            int p2 = find(current.end);

            if(p1 != p2) {
                union(p1, p2);
                link[current.start] = true;
                link[current.end] = true;
                result += current.cost;
                bridge++;
            }
        }

        if(result == 0) return -1;
        if(bridge != num - 2) return -1;
        return result;
    }

    private void union(int start, int end) {
        int startParent = find(start);
        int endParent = find(end);
        if (startParent < endParent) parents[endParent] = startParent;
        else parents[startParent] = endParent;
    }

    private int find(int target) {
        if (parents[target] == target) return target;
        return parents[target] = find(parents[target]);
    }

    private void findEdge(int row, int col, int num, int direction, int distance) {
        if(map[row][col] != 0 &&map[row][col] != num) {
            if(distance >= 2) edges.offer(new Edge(num, map[row][col], distance));
            return;
        }

        int nextRow = row + deltas[direction][0];
        int nextCol = col + deltas[direction][1];
        if(nextRow < 0 || nextCol < 0 || nextRow >= n || nextCol >= m) return;
        if(map[nextRow][nextCol] == num) return;
        findEdge(nextRow, nextCol, num, direction, distance + 1);

    }

    private void bfs(int row, int col, int num) {
        Deque<int[]> toVisit = new ArrayDeque<>();
        toVisit.add(new int[] {row, col});
        visited[row][col] = true;
        while(!toVisit.isEmpty()) {
            int[] now = toVisit.poll();
            map[now[0]][now[1]] = num;
            islands[num].add(new Node(now[0], now[1]));
            for (int i = 0; i < 4; i++) {
                int nextRow = now[0] + deltas[i][0];
                int nextCol = now[1] + deltas[i][1];

                if (nextRow < 0 || nextRow >= n || nextCol < 0 || nextCol >= m) continue;
                if (visited[nextRow][nextCol]) continue;
                if (map[nextRow][nextCol] != 1) continue;

                toVisit.add(new int[] {nextRow, nextCol});
                visited[nextRow][nextCol] = true;
            }
        }


    }

    public static void main(String[] args) throws IOException {
        new one17472().solution();
    }
}



