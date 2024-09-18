
import java.util.*;
import java.io.*;
import java.lang.reflect.Array;

public class Main {
    static int N, M, parents[], size[];

    static void make() {
        parents = new int[N+1];
        size = new int[N+1];
        Arrays.fill(parents, -1);
        Arrays.fill(size, 1);
    }

    static class Edge {
        int a, b;
        Edge(int a, int b) {
            this.a = a;
            this.b = b;
        }
    }

    static int findSet(int a) {
        if (parents[a] < 0) return a;
        return parents[a] = findSet(parents[a]);
    }

    static boolean union(int a, int b){
        int aRoot = findSet(a);
        int bRoot = findSet(b);
        if (aRoot == bRoot) return false;
        if (size[bRoot] > size[aRoot]) {
            size[bRoot] += size[aRoot];
            parents[aRoot] = bRoot;
        } else {
            size[aRoot] += size[bRoot];
            parents[bRoot] = aRoot;
        }
        return true;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder output = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());

        int T = Integer.parseInt(st.nextToken());
        for (int t = 1; t <= T; t++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());

            make();
            ArrayDeque<Edge> edges = new ArrayDeque<>();
            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                edges.add(new Edge(a, b));
            }

            int cnt = 0, cost = 0;
            while (!edges.isEmpty()) {
                Edge edge = edges.poll();
                if (union(edge.a, edge.b)) {
                    cost ++;
                    if (++cnt == N) {
                        break;
                    }
                }
            }
            output.append(cost).append("\n");
        } 


        bw.write(output.toString());
        bw.flush();
        br.close();
        bw.close();
    }
}
