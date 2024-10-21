import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	private static int N;
	// 하나의 행에 하나의 퀸만 위치할 수 있음!
	private static int[] choosed;
	private static int count;
	
	private static boolean validQueen(int r, int c) {
		for (int i = 0; i < r; i++) {
			if (c == choosed[i] || Math.abs(r - i) == Math.abs(c - choosed[i])) {
				return false;
			}
		}
		return true;
	}
	
	private static void makeCombination(int depth) {
		if (depth == N) {
			count++;
			return;
		}
		
		for (int c = 0; c < N; c++) {
			if (validQueen(depth, c)) {
				choosed[depth] = c;
				makeCombination(depth + 1);
			}
		}
	}
	
	private static void init() throws NumberFormatException, IOException {
		N = Integer.parseInt(br.readLine());
		choosed = new int[N];
		count = 0;
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		init();
		makeCombination(0);
		System.out.println(count);
	}

}
