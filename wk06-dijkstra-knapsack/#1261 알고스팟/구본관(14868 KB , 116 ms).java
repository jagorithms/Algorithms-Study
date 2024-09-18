import java.io.*;
import java.util.*;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;

	public static void main(String[] args) throws Exception {
		st = new StringTokenizer(br.readLine());
		int m = Integer.parseInt(st.nextToken());
		int n = Integer.parseInt(st.nextToken());

		char[][] graph = new char[n][m];
		for (int i = 0; i < n; i++) {
			graph[i] = br.readLine().toCharArray();
		}

		int[][] visit = new int[n][m];
		PriorityQueue<Node> hq = new PriorityQueue<>();
		hq.add(new Node(0, 0, 0)); // 시작점 추가

		int[][] dxy = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}}; // 방향 배열

		while (!hq.isEmpty()) {
			Node current = hq.poll();
			int k = current.k, x = current.x, y = current.y;

			if (x == m - 1 && y == n - 1) {
				System.out.println(k);
				break;
			}

			for (int i = 0; i < 4; i++) {
				int nx = x + dxy[i][0];
				int ny = y + dxy[i][1];

				if (nx >= 0 && nx < m && ny >= 0 && ny < n && visit[ny][nx] == 0) {
					visit[ny][nx] = 1;
					if (graph[ny][nx] == '0') {
						hq.add(new Node(k, nx, ny));
					} else {
						hq.add(new Node(k + 1, nx, ny));
					}
				}
			}
		}
	}

	static class Node implements Comparable<Node> {
		int k, x, y;

		public Node(int k, int x, int y) {
			this.k = k;
			this.x = x;
			this.y = y;
		}

		@Override
		public int compareTo(Node other) {
			return Integer.compare(this.k, other.k);
		}
	}
}
