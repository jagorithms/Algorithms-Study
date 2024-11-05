import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine()); 

        int[] indegree = new int[N + 1]; 
        int[][] grid = new int[N + 1][N + 1]; 
        List<Integer>[] adj = new ArrayList[N + 1]; 
        
        for (int i = 0; i <= N; i++) {
            adj[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int X = Integer.parseInt(st.nextToken());
            int Y = Integer.parseInt(st.nextToken());
            int K = Integer.parseInt(st.nextToken());
            grid[X][Y] = K; 
            adj[Y].add(X);
            indegree[X]++; 
        }


        int[][] dp = new int[N + 1][N + 1]; 
        ArrayDeque<Integer> queue = new ArrayDeque<>();
        
        for (int i = 1; i <= N; i++) {
            if (indegree[i] == 0) {
                queue.add(i);
                dp[i][i] = 1;
            }
        }

        while (!queue.isEmpty()) {
            int part = queue.pollFirst();
            for (int next : adj[part]) { 
                for (int i = 1; i <= N; i++) {
                    dp[next][i] += dp[part][i] * grid[next][part];
                }
                indegree[next]--;
                if (indegree[next] == 0) {
                    queue.add(next);
                }
            }
        }

        for (int i = 1; i <= N; i++) {
            if (dp[N][i] > 0) {
                sb.append(i).append(" ").append(dp[N][i]).append("\n");
            }
        }
        System.out.println(sb);
    }

}
