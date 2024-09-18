import java.io.*;
import java.util.*;

class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;

	static int N, M;
	static List<Integer> result = new ArrayList<>();
	static PriorityQueue<Integer> pq = new PriorityQueue<>();

	public static void main(String[] args) throws Exception {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		HashMap<Integer, List<Integer>> dic = new HashMap<>();
		int[] dept = new int[N+1];

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());

			dept[b]++;
			if (dic.containsKey(a)) {
				dic.get(a).add(b);
			} else {
				List<Integer> list = new ArrayList<>();
				list.add(b);
				dic.put(a, list);
			}
		}

		for (int i = 1; i < N+1; i++) {
			if(dept[i] == 0){
				pq.add(i);
			}
		}

		while (!pq.isEmpty()){
			int temp = pq.poll();
			result.add(temp);
			if(dic.containsKey(temp)){
				for (int i : dic.get(temp)) {
					dept[i]--;
					if(dept[i] == 0){
						pq.add(i);
					}
				}
			}
		}

		for (int i : result) {
			sb.append(i).append(" ");
		}
		System.out.println(sb);
	}

}
