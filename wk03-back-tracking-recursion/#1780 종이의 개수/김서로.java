package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 메모리: 310660 KB, 시간: 900 ms

public class 종이의개수 {
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static StringTokenizer tokens;
	private static StringBuilder sb = new StringBuilder();

	private static int N;
	private static int[][] paper;
	private static int[] count = new int[3];

	// 같은 수로 이루어진 종이로 자르기
	private static void cut(int startR, int startC, int length) {
		if (length == 0) {
			return;
		}

		int num = paper[startR][startC];

		for (int i = startR; i < startR + length; i++) {
			for (int j = startC; j < startC + length; j++) {
				if (paper[i][j] != num) {
					// 재귀
					cut(startR, startC, length / 3);
					cut(startR + length / 3, startC, length / 3);
					cut(startR + length / 3 * 2, startC, length / 3);

					cut(startR, startC + length / 3, length / 3);
					cut(startR + length / 3, startC + length / 3, length / 3);
					cut(startR + length / 3 * 2, startC + length / 3, length / 3);

					cut(startR, startC + length / 3 * 2, length / 3);
					cut(startR + length / 3, startC + length / 3 * 2, length / 3);
					cut(startR + length / 3 * 2, startC + length / 3 * 2, length / 3);
					return;
				}
			}
		}
		count[num + 1]++;
	}

	private static void init() throws NumberFormatException, IOException {
		N = Integer.parseInt(br.readLine());

		paper = new int[N][N];
		for (int i = 0; i < N; i++) {
			tokens = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				paper[i][j] = Integer.parseInt(tokens.nextToken());
			}
		}
	}

	private static void output() {
		for (int i = 0; i < 3; i++) {
			sb.append(count[i]).append("\n");
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		init();
		cut(0, 0, N);
		output();
		System.out.println(sb);
	}

}
