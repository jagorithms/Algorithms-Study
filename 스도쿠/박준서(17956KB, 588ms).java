import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static int N = 9;
	private static int[][] grid;
	public static void main(String[] args) throws IOException {
		grid = new int[N][N];
		
		for(int i = 0; i < N; i++) {
			String temp = br.readLine();
			for (int j = 0; j < N; j++) {
				grid[i][j] = temp.charAt(j) - '0';
			}
		}
		
		backtracking(0,0);
		
	}
	
	
	private static void backtracking(int row, int col) { 
		if(col == N) {
			backtracking(row + 1, 0);
			return;
		}
		
		if(row == N) { // 끝내야 함 
			printGrid();
			System.exit(0);
		}
		
		if(grid[row][col] != 0) {
			backtracking(row, col + 1);
			return;
		}
		
		for(int i = 1; i <= N; i++) {
			if(isValid(row, col, i)) {
				grid[row][col] = i;
				backtracking(row, col + 1);
				grid[row][col] = 0;
			}
		}
		
	}
	
	private static boolean isValid(int row, int col, int num) {
		for(int i = 0; i < N; i++) {
			if(grid[i][col] == num) {
				return false;
			}
		}
		
		for(int i = 0; i < N; i++) {
			if(grid[row][i] == num) {
				return false;
			}
		}
		
		int startRow = row / 3 * 3;
		int startCol= col / 3 * 3;
		
		for(int i = startRow; i < startRow + 3; i++) {
			for(int j = startCol; j < startCol + 3; j++) {
				if(grid[i][j] == num) {
					return false;
				}
			}
		}
		return true;
	}
	
	private static void printGrid() {
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				System.out.print(grid[i][j]);
			}
			System.out.println();
		}
	}
}
