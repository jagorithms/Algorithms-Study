import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	
	static class Star {
		double x, y;
		public Star(double x, double y) {
			this.x = x; 
			this.y = y;
		}
		
	}
	
	static class Edge implements Comparable<Edge>{
		int first, second;
		double weight;
		
		public Edge(int first, int second, double weight) {
			this.first = first;
			this.second = second;
			this.weight = weight;
		}
		
		@Override
		public int compareTo(Edge o) {
			return Double.compare(this.weight, o.weight);
		}
		
	}
	
	static void makeSet() {
		parents = new int[N];
		for(int i = 0; i < N; i++) parents[i] = -1;
	}
	
	static int find(int v) {
		if(parents[v] < 0) return v;
		return parents[v] = find(parents[v]);
	}
	
	static boolean union(int a, int b) {
		int rootA = find(a);
		int rootB = find(b);
		if(rootA == rootB) return false;
		if(rootA < rootB) parents[rootB] = rootA;
		else parents[rootA] = rootB;
		return true;
	}
	
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static int N;
	private static int[] parents;
	private static Star[] stars;
	private static List<Edge> edges = new ArrayList<>();
	public static void main(String[] args) throws IOException {
		N = Integer.parseInt(br.readLine());
		
		stars = new Star[N];
		
		for(int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			stars[i] = new Star(Double.parseDouble(st.nextToken()), Double.parseDouble(st.nextToken()));
		}
		
		for(int i = 0; i < N; i++) {
			for(int j = i + 1; j < N; j++) {
				double weight = makeWeight(i, j);
				edges.add(new Edge(i, j, weight));
			}
		}
		
		makeSet();
		
		Collections.sort(edges);
		int cnt = 0; double cost = 0;
		
		for(Edge edge : edges) {
			if(union(edge.first, edge.second)) {
				cost += edge.weight;
				if(++cnt == N-1) break;
			}
		}
		System.out.println(String.format("%.2f", cost));
		
	}
	
	static double makeWeight(int i, int j) {
		Star first = stars[i];
		Star second = stars[j];
		return Math.sqrt(Math.pow(first.x - second.x, 2) + Math.pow(first.y - second.y, 2));
	}

}
