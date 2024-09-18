import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 14616KB_272MS
public class 외판원순회2 {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer tokens;
    private static StringBuilder sb = new StringBuilder();

    private static int N;
    private static int[][] costs;
    private static boolean[] visited;
    private static int minCost;

    private static void solve() {
        for (int i = 0; i < N; i++) {
            visited[i] = true;
            dfs(i, 0, i, 0);
            visited[i] = false;
        }
    }

    private static void dfs(int startCity, int depth, int curCity, int curCost) {
        if (depth == N - 1) {
            // 처음 출발했던 도시로 돌아갈 수 없는 경우
            if (costs[curCity][startCity] == 0) {
                return;
            }
            int totalCost = costs[curCity][startCity] + curCost;
            minCost = Math.min(minCost, totalCost);
            return;
        }

        for (int i = 0; i < N; i++) {
            if (!visited[i] && costs[curCity][i] != 0) {
                visited[i] = true;
                dfs(startCity, depth + 1, i, curCost + costs[curCity][i]);
                visited[i] = false;
            }
        }
    }

    private static void init() throws IOException {
        N = Integer.parseInt(br.readLine());
        costs = new int[N][N];
        visited = new boolean[N];
        minCost = Integer.MAX_VALUE;

        for (int i = 0; i < N; i++) {
            tokens = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                costs[i][j] = Integer.parseInt(tokens.nextToken());
            }
        }
    }

    public static void main(String[] args) throws IOException {
        init();
        solve();
        System.out.println(minCost);
    }
}
