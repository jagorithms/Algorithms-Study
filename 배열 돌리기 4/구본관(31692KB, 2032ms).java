import java.io.*;
import java.util.*;

public class Main {
    static int N, M, K;
    static int[][] graph;
    static int[][] rotations;
    static boolean[] visit;
    static int result = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        graph = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                graph[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        rotations = new int[K][3];
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            rotations[i][0] = Integer.parseInt(st.nextToken()) - 1;
            rotations[i][1] = Integer.parseInt(st.nextToken()) - 1;
            rotations[i][2] = Integer.parseInt(st.nextToken());
        }

        visit = new boolean[K];
        dfs(graph);
        System.out.println(result);
    }

    public static void rotate(int y, int x, int t, int[][] gra) {
        int ny = y + t, nx = x + t;
        int st = gra[ny][nx];

        for (int i = 0; i < t * 2; i++) {
            gra[ny][nx] = gra[ny - 1][nx];
            ny -= 1;
        }
        for (int i = 0; i < t * 2; i++) {
            gra[ny][nx] = gra[ny][nx - 1];
            nx -= 1;
        }
        for (int i = 0; i < t * 2; i++) {
            gra[ny][nx] = gra[ny + 1][nx];
            ny += 1;
        }
        for (int i = 0; i < t * 2; i++) {
            gra[ny][nx] = gra[ny][nx + 1];
            nx += 1;
        }
        gra[y + t][x + t - 1] = st;
    }

    public static void dfs(int[][] tgraph) {
        boolean allVisited = true;

        for (int i = 0; i < K; i++) {
            if (!visit[i]) {
                visit[i] = true;
                int[][] ngraph = copyGraph(tgraph);
                
                for (int j = 1; j <= rotations[i][2]; j++) {
                    rotate(rotations[i][0], rotations[i][1], j, ngraph);
                }
                
                dfs(ngraph);
                visit[i] = false;
                allVisited = false;
            }
        }

        if (allVisited) {
            int minSum = Integer.MAX_VALUE;
            for (int i = 0; i < N; i++) {
                int rowSum = 0;
                for (int j = 0; j < M; j++) {
                    rowSum += tgraph[i][j];
                }
                minSum = Math.min(minSum, rowSum);
            }
            result = Math.min(result, minSum);
        }
    }

    public static int[][] copyGraph(int[][] original) {
        int[][] copy = new int[N][M];
        for (int i = 0; i < N; i++) {
            System.arraycopy(original[i], 0, copy[i], 0, M);
        }
        return copy;
    }
}

