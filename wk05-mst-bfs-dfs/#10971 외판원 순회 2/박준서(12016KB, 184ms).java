import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	private static int N;
	private static int[][] weight;
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static int min = Integer.MAX_VALUE;
	public static void main(String[] args) throws IOException {
		N = Integer.parseInt(br.readLine());
		weight = new int[N][N];
		
		for(int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++) {
				weight[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for(int i = 0; i < N; i++) {
			boolean[] visited = new boolean[N];
			visited[i] = true;
			dfs(0,i,visited, i, 0);
		}
		System.out.println(min);
	}
	
	private static void dfs(int depth, int start, boolean[] visited, int cur, int curCost) {
		if(depth == N-1) {
			if(weight[cur][start] != 0) {
				curCost += weight[cur][start];
				min = Math.min(min, curCost);
			}
			return;
		}
		for(int i = 0; i < N; i++) {
			if(!visited[i] && weight[cur][i] != 0) {
				visited[i] = true;
				dfs(depth + 1, start, visited, i, curCost +  weight[cur][i]);
				visited[i] = false;
			}
		}
	}
}
