import java.util.*;
import java.io.*;

public class Main {

    static int N, parents[], size[];
    static double[][] coordinates;

    private static void make() {
        parents = new int[N];
        size = new int[N];
        Arrays.fill(parents, -1);
        Arrays.fill(size ,1);
    }

    private static int findSet(int a) {
        if (parents[a] < 0) return a;
        return parents[a] = findSet(parents[a]);
    }

    private static boolean union(int a, int b) {
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

    private static double solveDist(Star star1, Star star2) {
        return Math.sqrt(Math.pow(Math.abs(star1.x - star2.x), 2) + Math.pow(Math.abs(star1.y - star2.y), 2));
    }

    static class Star {
        double y, x;
        int idx;
        static int order = 0;
        Star(double y, double x) {
            this.y = y;
            this.x = x;
            idx = order++;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        coordinates = new double[N][2];

        Star[] stars = new Star[N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            double x = Double.parseDouble(st.nextToken());
            double y = Double.parseDouble(st.nextToken()); 
            stars[i] = new Star(y, x);
        }

        make();

        PriorityQueue<double[]> costs = new PriorityQueue<>((a, b) -> Double.compare(a[2], b[2]));
        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) {
                costs.add(new double[] {stars[i].idx, stars[j].idx, solveDist(stars[i], stars[j])});
            }
        }

        int cnt = 0;
        double cost = 0.0;
        while (!costs.isEmpty()) {
            double[] node = costs.poll();
            if (union((int) node[0], (int) node[1])) {
                cost += node[2];
                if (++cnt == N) {
                    break;
                }
            }
        }

        sb.append(String.format("%.2f", cost));
         
        bw.write(sb.toString());
        bw.flush();
        br.close();
        bw.close();
    }
    
}
