import java.io.*;
import java.util.*;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;

	static int N;
	static int[][] graph;
	static HashMap<Integer, Integer> dp;

	public static void main(String[] args) throws Exception {
		N = Integer.parseInt(br.readLine());
		int INF = 1000000000;

		graph = new int[N][N];

		for(int i=0; i<N; i++){
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++){
				graph[i][j] = Integer.parseInt(st.nextToken());
				if(graph[i][j] == 0){
					graph[i][j] = INF;
				}
			}
		}

		dp = new HashMap<>();
		System.out.println(dfs(0, 1));
	}
	static int dfs(int city, int visit){
		if(visit == (1<<N)-1){
			if(graph[city][0] == 0){
				return 1000000000;
			}
			return graph[city][0];
		}
		if(dp.containsKey(visit*10+city)){
			return dp.get(visit*10+city);
		}
		int result = 1000000000;
		for(int i=0; i<N; i++){
			if((visit & 1<<i) == 0 && graph[city][i] != 0){
				result = Math.min(result, dfs(i, visit | 1<<i) + graph[city][i]);
			}
		}
		dp.put(visit*10+city, result);
		return result;
	}
}
