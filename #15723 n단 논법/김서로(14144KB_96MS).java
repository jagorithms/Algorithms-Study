import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.HashMap;

public class Main {
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static StringBuilder sb = new StringBuilder();

	private static HashMap<Character, Character> graph;

	private static void solve() throws NumberFormatException, IOException {
		int N = Integer.parseInt(br.readLine());
		graph = new HashMap<>();

		for (int i = 0; i < N; i++) {
			String[] tokens = br.readLine().split(" ");
			char start = tokens[0].charAt(0);
			char end = tokens[2].charAt(0);
			graph.put(start, end);
		}

		int M = Integer.parseInt(br.readLine());

		for (int i = 0; i < M; i++) {
			String[] tokens = br.readLine().split(" ");
			char start = tokens[0].charAt(0);
			char end = tokens[2].charAt(0);
			boolean conclusion = bfs(start, end);
			if (conclusion) {
				sb.append("T").append("\n");
			} else {
				sb.append("F").append("\n");
			}
		}
	}

	private static boolean bfs(char start, char end) {
		ArrayDeque<Character> dq = new ArrayDeque<>();
		boolean[] visited = new boolean[26];

		dq.offer(start);

		// 너비 우선 탐색 시 end가 검색되는지 확인
		while (!dq.isEmpty()) {
			char cur = dq.pollFirst();

			if (cur == end) {
				return true;
			}

			if (visited[cur - 97]) {
				continue;
			}

			visited[cur - 97] = true;

			if (graph.containsKey(cur)) {
				dq.addLast(graph.get(cur));
			}
		}

		return false;
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		solve();
		System.out.println(sb);
	}

}
