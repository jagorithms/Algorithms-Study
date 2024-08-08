import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static StringTokenizer tokens;
	private static StringBuilder sb = new StringBuilder();

	private static int N;
	private static int K;
    
	private static List<Integer> numbers = new ArrayList<>();
	private static List<Integer> josephus = new ArrayList<>();

	private static void init() throws IOException {
		tokens = new StringTokenizer(br.readLine());
		N = Integer.parseInt(tokens.nextToken());
		K = Integer.parseInt(tokens.nextToken());

		for (int i = 0; i < N; i++) {
			numbers.add(i + 1);
		}
	}

	private static void solve() {
		int index = 0;

		for (int i = 0; i < N; i++) {
			index = (index + K - 1) % numbers.size();
			josephus.add(numbers.remove(index));
		}
	}

	private static void output() {
		sb.append("<");

		for (int i = 0; i < N; i++) {
			sb.append(josephus.get(i));
			if (i != josephus.size() - 1) {
				sb.append(", ");
			}
		}

		sb.append(">");
		System.out.println(sb);
	}

	public static void main(String[] args) throws IOException {
		init();
		solve();
		output();
	}
}
