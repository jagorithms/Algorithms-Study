import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.StringTokenizer;

class Main{
	static class Node implements Comparable<Node>{
		int v, weight;
		
		public Node(int v, int weight) {
			this.v = v;
			this.weight = weight;
		}
		
		@Override
		public int compareTo(Node o) {
			return this.weight - o.weight;
		}
		
		
	}
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static int N,M,K,X;
	private static List<List<Node>> graph = new ArrayList<>();
	private static int[] dist;
	private static List<Integer> answer = new ArrayList<>();
	public static void main(String[] args) throws IOException {
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken()); 
		K = Integer.parseInt(st.nextToken()); // 거리 정보
		X = Integer.parseInt(st.nextToken()); // 출발 도시의 번호
		
		dist = new int[N];
		
		for(int i = 0; i < N; i++) {
			dist[i] = Integer.MAX_VALUE;
		}
		
		for(int i = 0; i < N; i++) {
			graph.add(new ArrayList<>());
		}
		
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken()) - 1;
			int b = Integer.parseInt(st.nextToken()) - 1;
			graph.get(a).add(new Node(b,1));
		}
		
		dist[X-1] = 0;
		
		dijkstra();
		
		for(int i = 0; i < N; i++) {
			if(dist[i] == K) answer.add(i + 1);
		}
		
		if(answer.size() == 0) {
			System.out.println(-1);
		} else {
			for( int i = 0; i < answer.size(); i++) {
				System.out.println(answer.get(i));
			}
		}
	}
	
	static void dijkstra() {
		PriorityQueue<Node> pq = new PriorityQueue<>((o1, o2) -> o1.weight - o2.weight);
		pq.add(new Node(X-1,0));
		while(!pq.isEmpty()) {
			Node temp = pq.poll();
			for(Node next : graph.get(temp.v)) {
				if(dist[next.v] > next.weight + temp.weight) {
					dist[next.v] = next.weight + temp.weight;
					pq.add(new Node(next.v, dist[next.v]));
				}
			}
		}
	}
}
