import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static class Command {
		int r,c,s;
		public Command(int r, int c, int s) {
			super();
			this.r = r;
			this.c = c;
			this.s = s;
		}
	}
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static int N,M,K;
	private static int[][] grid;
	private static Command[] commands;
	private static List<int[]> sequences = new ArrayList<>();
	private static int answer = Integer.MAX_VALUE;
	public static void main(String[] args) throws IOException {
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		grid = new int[N][M];
		commands = new Command[K];
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < M; j++) {
				grid[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for(int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken()) - 1;
			int c = Integer.parseInt(st.nextToken()) - 1;
			int s = Integer.parseInt(st.nextToken());
			commands[i] = new Command(r, c, s);
		}
		
		permutation(0, new int[K], new boolean[K]);
		
		for(int[] sequence : sequences) {
			int[][] temp = new int[N][M];
			
			for(int i = 0; i < N; i++) {
				System.arraycopy(grid[i], 0, temp[i], 0, M);
			}
			for(int idx : sequence) {
				rotateArray(commands[idx], temp);
			}
			calculate(temp);
		}
		
		System.out.println(answer);
		
	}
	
	private static void permutation(int depth, int[] chosen, boolean[] isSelected) {
		if(depth == K) {
			sequences.add(chosen.clone());
			return;
		}
		for(int i = 0; i < K; i++) {
			if(!isSelected[i]) {                                                                                                  
				chosen[depth] = i;
				isSelected[i] = true;
				permutation(depth + 1, chosen, isSelected);
				isSelected[i] = false;
			}
		}
	}
	
	private static void rotateArray(Command command, int[][] temp) {
		int r = command.r;
		int c = command.c;
		int s = command.s;
		
		for(int layer = 1; layer <= s; layer++) {
			int startX = r - layer;
			int startY = c - layer;
			int endX = r + layer;
			int endY = c + layer;
			
	        // 왼쪽, 아래, 오른쪽, 위 테두리를 회전시키기 위해 첫 번째 값을 임시로 저장
	        int start = temp[startX][startY];
	        
	        
	        // 왼쪽 테두리 위로 이동
	        for (int i = startX; i < endX; i++) temp[i][startY] = temp[i + 1][startY];
	       
	        // 아래쪽 테두리 왼쪽으로 이동
	        for (int i = startY; i < endY; i++) temp[endX][i] = temp[endX][i + 1];        
	        
	        // 오른쪽 테두리 아래로 이동
	        for (int i = endX; i > startX; i--) temp[i][endY] = temp[i - 1][endY];

	        // 위쪽 테두리 오른쪽으로 이동
	        for (int i = endY; i > startY + 1; i--) temp[startX][i] = temp[startX][i - 1];
	        

	        // 저장해둔 temp 값을 빈 자리인 시작 위치에 배치
	        temp[startX][startY + 1] = start;
			
		}
	
	}
		
	private static void calculate(int[][] temp) {
		for(int i = 0; i < N; i++) {
			int num = 0;
			for(int j = 0; j < M; j++) {
				num += temp[i][j];
			}
			
			answer = Math.min(answer, num);
		}
	}

}
