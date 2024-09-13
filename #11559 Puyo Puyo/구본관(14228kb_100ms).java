import java.io.*;
import java.util.*;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder bw = new StringBuilder();
	static StringTokenizer st;
	static int N, M, ans;
	static char[][] graph;
	static boolean[][] visited;
	static int[][] dydx = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

	public static void main(String[] args) throws Exception {
		N = 12;
		M = 6;
		ans = 0;


		graph = new char[N][M];
		for (int i = 0; i < N; i++) {
			graph [i] = br.readLine().toCharArray();
		}

		List<int[]> list = new ArrayList<>();

		while (true){
			visited = new boolean[N][M];
			list.clear();
			boolean flag = true;
			for (int y = 0; y < N; y++) {
				for (int x = 0; x < M; x++) {
					if (graph[y][x] != '.'){
						visited[y][x] = true;
						List <int[]> temp = dfs(y, x, graph[y][x], new ArrayList<>());
						if (temp.size() >= 4){
							list.addAll(temp);
							flag = false;
						}
					}
				}
			}
			if(flag){
				break;
			}
			ans+=1;

			Collections.sort(list, (a, b) -> {
				return a[0] - b[0];
			});

			for (int i = 0; i < list.size(); i++) {
				int[] cur = list.get(i);
				remove(cur[0], cur[1]);
			}



		}
		System.out.println(ans);
	}

	static List<int[]> dfs(int y, int x, int s, List<int[]> list){
		list.add(new int[]{y, x});
		visited[y][x] = true;
		for(int[] dy : dydx){
			int ny = y + dy[0];
			int nx = x + dy[1];

			if(ny < 0 || ny >= N || nx < 0 || nx >= M) continue;
			if(graph[ny][nx] == '.' || visited[ny][nx]) continue;

			if(graph[ny][nx] == s){
				dfs(ny, nx, s, list);
			}
		}

		return list;
	}

	static void remove(int y, int x){
		for (int i = y; i > 0; i--) {
			graph[i][x] = graph[i-1][x];
		}
		graph[0][x] = '.';
	}
}
