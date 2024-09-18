import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class 알고스팟 {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer tokens;
    private static StringBuilder sb = new StringBuilder();

    private static int N;
    private static int M;
    private static int[][] map;

    private static final int[][] deltas = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    private static ArrayDeque<int[]> dq;
    private static boolean[][] visited;

    private static int bfs() {
        dq = new ArrayDeque<>();
        visited = new boolean[N][M];

        // 현재 좌표, 벽을 부순 횟수
        dq.offer(new int[]{0, 0, 0});
        visited[0][0] = true;

        while (!dq.isEmpty()) {
            int[] cur = dq.poll();
            int r = cur[0];
            int c = cur[1];
            int count = cur[2];

            // 목적지에 도착하면
            if (r == N - 1 && c == M - 1) {
                return count;
            }

            // 사방 탐색
            for (int[] delta : deltas) {
                int nr = r + delta[0];
                int nc = c + delta[1];
                // 범위를 벗어나면
                if (nr < 0 || nc < 0 || nr >= N || nc >= M) {
                    continue;
                }
                // 이미 방문했으면
                if (visited[nr][nc]) {
                    continue;
                }
                // 방문 처리
                visited[nr][nc] = true;
                // 벽이면 부순 뒤 덱의 맨 뒤에 추가
                if (map[nr][nc] == 1) {
                    dq.addLast(new int[]{nr, nc, count + 1});
                } else {
                    dq.addFirst(new int[]{nr, nc, count});
                }
            }
        }
        // 목적지에 도착할 수 없으면 -1 반환
        return -1;
    }

    private static void init() throws IOException {
        tokens = new StringTokenizer(br.readLine());
        M = Integer.parseInt(tokens.nextToken());
        N = Integer.parseInt(tokens.nextToken());
        map = new int[N][M];

        for (int i = 0; i < N; i++) {
            String[] tokens = br.readLine().split("");
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(tokens[j]);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        init();
        System.out.println(bfs());
    }
}
