
import java.util.*;
import java.io.*;

public class Main {
    static char[][] grid;
    static final int DY[] = {-1, 1, 0, 0}, DX[] = {0, 0, -1, 1}, H = 12, W = 6;

    static boolean play() {
        boolean success = false;
        for (int i =0; i < H; i++) {
            for (int j = 0; j < W; j++) {
                boolean[][] visited = new boolean[H][W];
                if (grid[i][j] != '.') {
                    if (pangpang(i, j, visited)) {
                        success = true;
                    }
                }
            }
        }
        gravity();
        return success;
    }

    static char[][] initializeGrid(char[][] grid) {
        for (int i = 0; i < grid.length; i++) {
            for (int j =0; j < grid[0].length; j++) {
                grid[i][j] = '.';
            }
        }
        return grid;
    }
    static void gravity() {
        char[][] newGrid = new char[H][W];
        newGrid = initializeGrid(newGrid);
        for (int col = 0; col < W; col++) {
            ArrayDeque<Character> chars= new ArrayDeque<>();
            for (int row = 0; row < H; row++) {
                if (grid[row][col] != '.') {
                    chars.add(grid[row][col]);
                }
            }            
            int row = H-1;
            while (!chars.isEmpty()) {
                newGrid[row--][col] = chars.pollLast();
            }
        }
        grid = newGrid;
    
    }

    static boolean isValidCoordinate(int y, int x) {
        return y >= 0 && y < H && x >= 0 && x < W;
    }

    static boolean pangpang(int startY, int startX, boolean[][] visited) {
        char color = grid[startY][startX];
        ArrayDeque<int[]> sameColor = new ArrayDeque<>();
        List<int[]> result = new ArrayList<>();

        sameColor.add(new int[] {startY, startX});
        visited[startY][startX] = true;
        result.add(new int[]{startY, startX});

        while (!sameColor.isEmpty()) {
            int[] node = sameColor.pollFirst();
            int y = node[0];
            int x = node[1];

            for (int d = 0; d < 4; d++) {
                int ny = DY[d] + y;
                int nx = DX[d] + x;
                if (isValidCoordinate(ny, nx) && !visited[ny][nx] && color == grid[ny][nx]) {
                    visited[ny][nx] = true;
                    result.add(new int[] {ny, nx});
                    sameColor.add(new int[] {ny, nx});
                }
            }
        }

        if (result.size() >= 4) {
            for (int[] coordinate : result) {
                int y = coordinate[0];
                int x = coordinate[1];
                grid[y][x] = '.';
            }

            return true;
        }
        return false;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder output = new StringBuilder();

        grid = new char[H][W];
        for (int i = 0; i < 12; i++) {
            grid[i] = br.readLine().toCharArray();
        }

        int count = 0;
        while (play()) {
            count++;
        }
        output.append(count);
        bw.write(output.toString());
        bw.flush();
        br.close();
        bw.close();
    }
}
