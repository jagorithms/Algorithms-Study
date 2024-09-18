import java.util.*;
import java.io.*;

public class Main {

    static int N, M, grid[][], connections[][];
    static long ans = Long.MAX_VALUE;

    static void dfs(int start, int initial, int count, long res, int[] route, int[] cost, boolean visited[]) {
        if (count == N-1) {
            if (grid[start][initial] > 0) {
                ans = Math.min(res+grid[start][initial], ans);
            }
            return;
        }


        for (int i = 0; i < N; i++) {
            if (grid[start][i] > 0 && !visited[i]) {
                visited[i] = true;
                route[count] = i;
                cost[count] = grid[start][i];
                dfs(i, initial, count+1, res + grid[start][i], route, cost, visited);
                visited[i] = false;
            }
        }
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());        
        grid = new int[N][N];

        for (int n = 0; n < N; n++) {
            st = new StringTokenizer(br.readLine());
            int i = 0;
            while (st.hasMoreTokens()) {
                grid[n][i++] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < N; i++) {
            boolean[] visited = new boolean[N];
            visited[i] = true;
            dfs(i, i, 0, 0L, new int[N], new int[N], visited);
        }


        sb.append(ans);
        bw.write(sb.toString());
        bw.flush();
        br.close();
        bw.close();
    }

}
