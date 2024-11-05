import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main { 
	private static int N,M;
	private static char[][] board;
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static int[] dx = {0,0,-1,1}; // 왼 오 위 아
	private static int[] dy = {-1,1,0,0};
	private static int redX, redY, blueX, blueY;
	private static int answer = 0;
	private static boolean[][][][] visited;
	public static void main(String[] args) throws IOException {
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		board = new char[N][M];
		visited = new boolean[N][M][N][M];
		
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
		
		bfs();
		System.out.println(answer);
	}
	
	private static void bfs() {
		Queue<int[]> queue = new LinkedList<>();
		queue.add(new int[] {redX, redY, blueX, blueY, 1});
		visited[redX][redY][blueX][blueY] = true;
		while(!queue.isEmpty()) {
			int[] temp = queue.poll();
			int tRedX = temp[0];
			int tRedY = temp[1];
			int tBlueX = temp[2];
			int tBlueY = temp[3];
			int time = temp[4];
			
			for(int i = 0; i < 4; i++) {
				MoveResult result = isMove(tRedX, tRedY, tBlueX, tBlueY, i);
				
				if(result.blueEscaped) continue;
				if(result.redEscaped) {
					answer = 1;
					return;
				}
				
				if(!visited[result.nRedX][result.nRedY][result.nBlueX][result.nBlueY]) {
					visited[result.nRedX][result.nRedY][result.nBlueX][result.nBlueY] = true;
					queue.add(new int[] {result.nRedX, result.nRedY, result.nBlueX, result.nBlueY, time + 1});
				}
			}
			
			if(time > 10) return;
		}
	}
	
	private static MoveResult isMove(int nRedX, int nRedY, int nBlueX, int nBlueY, int dir) {
		boolean redEscape = false;
		boolean blueEscape = false;
		int redMove = 0;
		int blueMove = 0;
		
		while(true) {
			int nx = nRedX + dx[dir];
			int ny = nRedY + dy[dir];
			
			if(nx < 0 || nx >= N || ny < 0 || ny >= M || board[nx][ny] == '#') break;
			
			redMove++;
			nRedX = nx;
			nRedY = ny;
			if(board[nx][ny] == 'O') {
				redEscape = true;
				break;
			}	
		}
		
		while(true) {
			int nx = nBlueX + dx[dir];
			int ny = nBlueY + dy[dir];
			
			if(nx < 0 || nx >= N || ny < 0 || ny >= M || board[nx][ny] == '#') break;
			
			if(board[nx][ny] == '#') break;
			
			blueMove++;
			nBlueX = nx;
			nBlueY = ny;
			if(board[nx][ny] == 'O') {
				blueEscape = true;
				break;
			}	
		}
		
		if(nRedX == nBlueX && nBlueY == nRedY) {
			if(redMove > blueMove) {
				nRedX -= dx[dir];
				nRedY -= dy[dir];
			} else {
				nBlueX -= dx[dir];
				nBlueY -= dy[dir];
			}
		}
		
		return new MoveResult(nRedX, nRedY, nBlueX, nBlueY, redEscape, blueEscape);
	}
	
	static class MoveResult {
		int nRedX, nRedY, nBlueX, nBlueY;
		boolean redEscaped, blueEscaped;
		
		public MoveResult(int nRedX, int nRedY, int nBlueX, int nBlueY, boolean redEscaped, boolean blueEscaped) {
			super();
			this.nRedX = nRedX;
			this.nRedY = nRedY;
			this.nBlueX = nBlueX;
			this.nBlueY = nBlueY;
			this.redEscaped = redEscaped;
			this.blueEscaped = blueEscaped;
		}
		
		
	}
}
