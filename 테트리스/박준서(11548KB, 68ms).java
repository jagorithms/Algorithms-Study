import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	private static int C,P;
	private static int[] tetris;
	private static String[][] boards;
	private static int answer = 0;
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	public static void main(String[] args) throws IOException {
		StringTokenizer st = new StringTokenizer(br.readLine());
		C = Integer.parseInt(st.nextToken());
		P = Integer.parseInt(st.nextToken());
		
		tetris = new int[C];
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < C; i++) {
			tetris[i] = Integer.parseInt(st.nextToken());
		}
		
		boards = new String[][] {
			{"0", "0000"},
			{"00"},
			{"001", "10"},
			{"100", "01"},
			{"000", "01", "101", "10"},
			{"000", "00", "011", "20"},
			{"000", "02", "110","00"}
		};
		
		for(String block : boards[P-1]) { // 해당 도형에 있는 가능한 경우의 수 별로 함수 돌리기
			playTetris(block);
		}
		
		System.out.println(answer);
	}
	
	private static void playTetris(String block) {
		int len = block.length();
		for(int i = 0; i <= C - len; i++) {
			boolean isPossible = true;
			for(int j = 0; j < len - 1; j++) {
				if(tetris[i + j] - tetris[i + j + 1] != block.charAt(j) - block.charAt(j + 1)) {
					isPossible = false;
					break;
				}
			}
			if(isPossible) answer++;
		}
	}
}
