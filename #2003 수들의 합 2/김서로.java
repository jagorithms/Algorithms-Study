package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 메모리: 14992 KB, 시간: 128 ms

public class 수들의합2 {
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static StringTokenizer tokens;

	private static int N;
	private static int M;
	private static int[] P;

	private static int[] accSum;
	private static int count = 0;

	// 투 포인터 활용 
	private static void solution() {
		int left = 1;
		int right = 1;
		int sum = 0;

		while (left <= N && right <= N) {
			sum = accSum[right] - accSum[left - 1];

			if (sum == M) {
				right++;
				count++;
			} else if (sum > M) {
				left++;
			} else {
				right++;
			}
		}
	}

	// 누적 합 미리 구하기
	private static void calcAccSum() {
		for (int i = 1; i < N + 1; i++) {
			accSum[i] = accSum[i - 1] + P[i - 1];
		}
	}

	// 입력
	private static void init() throws IOException {
		tokens = new StringTokenizer(br.readLine());
		N = Integer.parseInt(tokens.nextToken());
		M = Integer.parseInt(tokens.nextToken());
		P = new int[N];

		tokens = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			P[i] = Integer.parseInt(tokens.nextToken());
		}

		accSum = new int[N + 1];
	}

	public static void main(String[] args) throws IOException {
		init();
		calcAccSum();
		solution();
		System.out.println(count);
	}

}
