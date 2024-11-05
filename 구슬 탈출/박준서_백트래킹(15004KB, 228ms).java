package november.four;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ13459 { 
	private static int N,M;
	private static char[][] board;
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static int[] dx = {0,0,-1,1}; // 왼 오 위 아
	private static int[] dy = {-1,1,0,0};
	private static int redX, redY, blueX, blueY;
	private static int answer = 0;
	public static void main(String[] args) throws IOException {
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		board = new char[N][M];
		
		for(int i = 0; i < N; i++) {
			String temp = br.readLine();
			for(int j = 0; j < M; j++) {
				board[i][j] = temp.charAt(j);
			}
		}
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				if(board[i][j] == 'R') {
					redX = i;
					redY = j;
				} else if(board[i][j] == 'B') {
					blueX = i;
					blueY = j;
				}
			}
		}
		
		
		dfs(1);
		System.out.println(answer);
	}
	
	private static void dfs(int depth) {
		if(depth > 10) {
			return;
		}
		
		for(int i = 0; i < 4; i++) {
			int tRedX = redX;
			int tRedY = redY;
			int tBlueX = blueX;
			int tBlueY = blueY;
			
			boolean result = move(i);
			
			if(result && answer == 0) {
				dfs(depth + 1);
			}
			
			redX = tRedX;
			redY = tRedY;
			blueX = tBlueX;
			blueY = tBlueY;
		}
	}
	
	private static boolean move(int dir) {
		boolean redEscape = false;
		boolean blueEscape = false;
		int redMove = 0;
		int blueMove = 0;
		
		while(true) {
			int nx = redX + dx[dir];
			int ny = redY + dy[dir];
			
			if(nx < 0 || nx >= N || ny < 0 || ny >= M || board[nx][ny] == '#') break;
			
			redMove++;
			
			redX = nx;
			redY = ny;
			
			if(board[redX][redY] == 'O') {
				redEscape = true;
				break;
			}
			
		}
		
		while(true) {
			int nx = blueX + dx[dir];
			int ny = blueY + dy[dir];
			
			if(nx < 0 || nx >= N || ny < 0 || ny >= M || board[nx][ny] == '#') break;
			
			blueMove++;
			
			blueX = nx;
			blueY = ny;
			
			if(board[blueX][blueY] == 'O') {
				blueEscape = true;
				break;
			}
		}
		
		if(blueEscape) return false;
		if(redEscape && !blueEscape) {
			answer = 1;
			return true;
		}
		
		if(redX == blueX && redY == blueY) {
			if(redMove < blueMove) {
				blueX -= dx[dir];
				blueY -= dy[dir];
			} else {
				redX -= dx[dir];
				redY -= dy[dir];
			}
		}
		
		return true;
	}

}
