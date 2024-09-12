
import java.util.*;
import java.io.*;

public class Main {
    static BufferedReader br;
    static StringBuilder sb;
    static BufferedWriter bw;
    static StringTokenizer st;
    static int n, m, parents[];
    
    static void makeSet() {
        parents = new int[n+1];
        Arrays.fill(parents, -1);
    }

    static int findSet(int a) {
        if (parents[a] < 0) return a;
        return parents[a] = findSet(parents[a]);
    }

    static boolean union(int a, int b) {
        int aRoot = findSet(a);
        int bRoot = findSet(b);
        if (aRoot == bRoot) return false;
        parents[aRoot] = bRoot;
        return true;
    }

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        sb = new StringBuilder();
        st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        int count = 1;
        makeSet();
        int[][] edges = new int[m][2];
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int v1 = Integer.parseInt(st.nextToken());
            int v2 = Integer.parseInt(st.nextToken());
            edges[i][0] = v1;
            edges[i][1] = v2;
        }

        for (int[] edge : edges) {
            if (!union(edge[0], edge[1])) break;
            count++;
        }
        
        sb.append(count == m+1 ? 0 : count);

        bw.write(sb.toString());
        bw.flush();
        br.close();
        bw.close();
    }
}
