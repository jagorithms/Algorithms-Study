import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1717 {
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static int N,M;
	private static int[] parents;
	private static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws IOException {
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		parents = new int[N + 1];
		for(int i = 1; i <= N; i++) {
			parents[i] = i;
		}
		
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int command = Integer.parseInt(st.nextToken());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			if(command == 0) {
				if(a != b) {
					union(a, b);
				}
			} else if(command == 1) {
				int rootA = find(a);
				int rootB = find(b);
				if(rootA == rootB) sb.append("YES").append("\n");
				else sb.append("NO").append("\n");
			}
		}
		System.out.println(sb);
	}
	
	static int find(int v) {
		if(parents[v] == v) return v;
		return parents[v] = find(parents[v]);
	}
	
	static void union(int a, int b){
		int rootA = find(a);
		int rootB = find(b);
		if(rootA != rootB) {
			if(rootA < rootB) parents[rootB] = rootA;
			else parents[rootA] = rootB;
		}
	}

}
