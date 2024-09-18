package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 메모리: 14520 KB, 시간: 100 ms

public class 암호만들기 {
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static StringTokenizer tokens;
	private static StringBuilder sb = new StringBuilder();

	private static int L; // 암호 길이
	private static int C; // 알파벳 개수
	private static char[] available; // 가능한 알파벳
	private static char[] choosed;

	private static char[] vowels = new char[] { 'a', 'e', 'i', 'o', 'u' };

	// 유효한 모음 개수인지 판단
	private static boolean isValidVowelCount() {
		int count = 0;
		for (int i = 0; i < L; i++) {
			for (int vowel : vowels) {
				if (choosed[i] == vowel) {
					count++;
					break;
				}
			}
		}

		if (count >= 1 && count <= L - 2) {
			return true;
		}
		return false;
	}

	private static void makeCombination(int depth, int start) {
		if (depth == L) {
			// 모음 개수가 유효하면 출력
			if (isValidVowelCount()) {
				for (int i = 0; i < L; i++) {
					sb.append(choosed[i]);
				}
				sb.append("\n");
			}
			return;
		}

		for (int i = start; i < C; i++) {
			choosed[depth] = available[i];
			makeCombination(depth + 1, i + 1);
		}
	}

	private static void init() throws IOException {
		tokens = new StringTokenizer(br.readLine());
		L = Integer.parseInt(tokens.nextToken());
		C = Integer.parseInt(tokens.nextToken());

		tokens = new StringTokenizer(br.readLine());
		choosed = new char[L];
		available = new char[C];

		for (int i = 0; i < C; i++) {
			available[i] = tokens.nextToken().charAt(0);
		}

		Arrays.sort(available);
	}

	public static void main(String[] args) throws IOException {
		init();
		makeCombination(0, 0);
		System.out.println(sb);
	}

}
