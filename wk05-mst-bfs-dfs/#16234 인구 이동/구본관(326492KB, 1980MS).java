import java.io.*;
import java.util.*;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;

	static int N, L, R;
	static int[][] graph;
	static int result = 0;
	static HashMap<Integer, List<Integer>> dic = new HashMap<>();
	static int[][] dir = {{0,1},{1,0}};

	public static void main(String[] args) throws Exception {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());

		graph = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				graph[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		boolean flag = true;

		while(flag){
			flag = false;

			int[][] parent = new int[N][N];
			for (int y = 0; y < N; y++) {
				for (int x = 0; x < N; x++) {
					parent[y][x] = y*N+x;
					List<Integer> li = new ArrayList<>();
					li.add(y*N+x);
					dic.put(y*N+x, li);
				}
			}

			for (int y = 0; y < N; y++) {
				for (int x = 0; x < N; x++) {
					for (int i = 0; i < 2; i++) {
						int ny = y + dir[i][0];
						int nx = x + dir[i][1];

						if(ny < 0 || nx < 0 || ny >= N || nx >= N) continue;

						int diff = Math.abs(graph[y][x] - graph[ny][nx]);
						if(L <= diff && diff <= R){
							if(findParent(y, x, parent) != findParent(ny, nx, parent)){
								unionParent(y, x, ny, nx, parent);
								flag = true;
							}
						}
					}

				}
			}

			if(!flag)
				break;
			result++;

			for (int key : dic.keySet()) {
				int sum = 0;
				for (int idx : dic.get(key)) {
					sum += graph[idx/N][idx%N];
				}
				sum /= dic.get(key).size();
				for (int idx : dic.get(key)) {
					graph[idx/N][idx%N] = sum;
				}
			}


		}

		// for (int i = 0; i < N; i++) {
		// 	for (int j = 0; j < N; j++) {
		// 		System.out.print(graph[i][j] + " ");
		// 	}
		// 	System.out.println();
		// }
		System.out.println(result);

	}

	static int findParent(int y, int x, int[][] parent){
		if(parent[y][x] == y*N+x)
			return parent[y][x];
		return parent[y][x] = findParent(parent[y][x]/N, parent[y][x]%N, parent);
	}

	static void unionParent(int y1, int x1, int y2, int x2, int[][] parent){
		int p1 = findParent(y1, x1, parent);
		int p2 = findParent(y2, x2, parent);

		if(p1 < p2) {
			parent[p2 / N][p2 % N] = p1;
			dic.get(p1).addAll(dic.get(p2));
			dic.remove(p2);
		}
		else {
			parent[p1 / N][p1 % N] = p2;
			dic.get(p2).addAll(dic.get(p1));
			dic.remove(p1);
		}
	}
}
