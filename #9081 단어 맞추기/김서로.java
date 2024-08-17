package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 메모리: 14004 KB, 시간: 92 ms

public class 단어맞추기 {
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static StringBuilder sb = new StringBuilder();

	private static char[] word;

	// 다음 단어 찾기 
	private static void nextPermutation() {
		for (int i = word.length - 1; i > 0; i--) {
			if (word[i - 1] < word[i]) {
				for (int j = word.length - 1; j > i - 1; j--) {
					if (word[i - 1] < word[j]) {
						char temp = word[i - 1];
						word[i - 1] = word[j];
						word[j] = temp;
						reverse(i, word.length - 1);
						return;
					}
				}
			}
		}
	}

	// 특정 범위의 배열 뒤집기
	private static void reverse(int start, int end) {
		while (start < end) {
			char temp = word[start];
			word[start] = word[end];
			word[end] = temp;
			start++;
			end--;
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		int T = Integer.parseInt(br.readLine());

		for (int t = 0; t < T; t++) {
			word = br.readLine().toCharArray();
			nextPermutation();

			for (int j = 0; j < word.length; j++) {
				sb.append(word[j]);
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}

}
