import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {
	private static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	private static StringTokenizer tokens;
	private static StringBuilder output = new StringBuilder();

	public static void main(String[] args) throws NumberFormatException, IOException {
		int N = Integer.parseInt(input.readLine());

		// { 풍선 번호, 이동할 값 }
		Deque<int[]> balloons = new ArrayDeque<>();

		tokens = new StringTokenizer(input.readLine());
		for (int i = 0; i < N; i++) {
			int delta = Integer.parseInt(tokens.nextToken());
			balloons.add(new int[] { i + 1, delta });
		}

		while (!balloons.isEmpty()) {
			int[] balloon = balloons.pollFirst();
			int index = balloon[0];
			int delta = balloon[1];
			output.append(index).append(" ");

			if (balloons.isEmpty())
				break;

			// 오른쪽으로 이동할 경우
			if (delta > 0) {
				for (int i = 0; i < delta - 1; i++) {
					balloons.addLast(balloons.pollFirst());
				}
			// 왼쪽으로 이동할 경우
			} else {
				for (int i = 0; i < -delta; i++) {
					balloons.addFirst(balloons.pollLast());
				}
			}
		}

		// 출력
		System.out.println(output);
	}
}
