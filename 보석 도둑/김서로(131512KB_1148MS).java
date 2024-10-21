import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class Main {
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static StringTokenizer tokens;

	private static int N; // 보석의 개수
	private static int K; // 가방의 개수

	private static PriorityQueue<int[]> jewels;
	// 가장 큰 가방부터 정렬
	private static TreeMap<Integer, Integer> bags;
	private static long maxPrice; // 담을 수 있는 보석의 최대 가격

	private static void solution() {
		while (!jewels.isEmpty()) {
			int[] jewel = jewels.poll();
			int M = jewel[0];
			int V = jewel[1];

			// 가방에 넣을 수 있으면 가격 더하기
			Integer bag = bags.ceilingKey(M);
			if (bag != null) {
				bags.put(bag, bags.get(bag) - 1);
				if (bags.get(bag) == 0) {
					bags.remove(bag);
				}
				maxPrice += V;
			}

			// 사용 가능한 가방이 없으면 종료
			if (bags.size() == 0) {
				return;
			}
		}
	}

	private static void init() throws IOException {
		tokens = new StringTokenizer(br.readLine());
		N = Integer.parseInt(tokens.nextToken());
		K = Integer.parseInt(tokens.nextToken());

		jewels = new PriorityQueue<>(new Comparator<int[]>() {

			// 보석의 가치를 기준으로 내림차순으로 정렬
			@Override
			public int compare(int[] o1, int[] o2) {
				return Integer.compare(o2[1], o1[1]);
			}

		});

		for (int i = 0; i < N; i++) {
			tokens = new StringTokenizer(br.readLine());
			int M = Integer.parseInt(tokens.nextToken());
			int K = Integer.parseInt(tokens.nextToken());
			jewels.add(new int[] { M, K });
		}

		bags = new TreeMap<>();

		for (int i = 0; i < K; i++) {
			int bag = Integer.parseInt(br.readLine());
			bags.put(bag, bags.getOrDefault(bag, 0) + 1);
		}

		maxPrice = 0L;
	}

	public static void main(String[] args) throws IOException {
		init();
		solution();
		System.out.println(maxPrice);
	}

}
