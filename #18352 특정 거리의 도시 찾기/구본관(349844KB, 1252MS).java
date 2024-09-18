import java.io.*;
import java.util.*;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;

	static int N, M, K, X;
	static boolean[] visited;
	static HashMap<Integer, List<Integer>> map;

	public static void main(String[] args) throws Exception {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		X = Integer.parseInt(st.nextToken());

		map = new HashMap<>();
		visited = new boolean[N + 1];
		visited[X] = true;

		for (int i = 0; i< M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			if(!map.containsKey(a)){
				List<Integer> list = new ArrayList<>();
				list.add(b);
				map.put(a, list);
			}else{
				map.get(a).add(b);
			}
		}
		//System.out.println(map);

		bfs(X);
	}
	static void bfs(int start) {
		Deque<Integer> dq2 = new ArrayDeque<>();
		Deque<Integer> dq = new ArrayDeque<>();
		dq2.add(start);

		int count = 0;
		List<Integer> list = new ArrayList<>();

		while(!dq2.isEmpty()){
			count++;
			if(count>K){
				break;
			}
			dq = dq2;
			dq2 = new ArrayDeque<>();
			while(!dq.isEmpty()){
				int st = dq.poll();
				if(map.containsKey(st)){
					for(int i : map.get(st)){
						if(!visited[i]){
							visited[i] = true;
							dq2.add(i);
							if(count == K){
								list.add(i);
							}
						}
					}
				}
			}
		}
		if(list.size()>0){
			Collections.sort(list);
			for (int i = 0; i < list.size(); i++) {
				System.out.println(list.get(i));
			}
			return;
		}
		System.out.println(-1);
	}
}
