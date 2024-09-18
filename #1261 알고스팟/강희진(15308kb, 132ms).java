import java.util.*;
import java.io.*;

public class Main {

    static int N, M, grid[][];    
    static final int DY[] = {-1, 1, 0, 0}, DX[] = {0, 0, -1, 1}, INF = Integer.MAX_VALUE;


    static int dijkstra(int startY, int startX, int destY, int destX) {
        boolean[][] visited = new boolean[M][N];
        int[][] wallsBroken = new int[M][N];
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> Integer.compare(a[2], b[2]));

        for (int i = 0; i < M; i++) {
            Arrays.fill(wallsBroken[i], INF);
        }
        wallsBroken[startY][startX] = 0;
        pq.offer(new int[]{startY, startX, 0});

        while (!pq.isEmpty()) {
            int[] node = pq.poll();
            int y = node[0];
            int x = node[1];
            int walls = node[2];

            if (visited[y][x]) continue;
            visited[y][x] = true;

            if (y == destY && x == destX) return walls;

            for (int d = 0; d < 4; d++) {
                int ny = DY[d] + y;
                int nx = x + DX[d];

                if (isValidCoordinate(ny, nx) && !visited[ny][nx]) {
                    if (grid[ny][nx] == 1 && walls + 1 < wallsBroken[ny][nx]) {
                        wallsBroken[ny][nx] = walls + 1;
                        pq.offer(new int[]{ny, nx, wallsBroken[ny][nx]});
                    } else if (grid[ny][nx] == 0 && walls < wallsBroken[ny][nx]) {
                        wallsBroken[ny][nx] = walls;
                        pq.offer(new int[]{ny, nx, wallsBroken[ny][nx]});
                    }
                }
            }
        }
        return -1;
    }

    static boolean isValidCoordinate(int y, int x) {
        return y >= 0 && x >= 0 && y < M && x < N;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder output = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        grid = new int[M][N];

        for (int i = 0; i < M; i++) {
            String input = br.readLine();
            for (int j = 0; j < N; j++) {
                grid[i][j] = input.charAt(j) - '0';
            }
        } 
        output.append(dijkstra(0, 0, M-1, N-1));

        bw.write(output.toString());
        bw.flush();
        br.close();
        bw.close();
    }
}
