import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;

public class PuyoPuyo {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    private static char[][] map = new char[12][6];
    private static int[][] moves = new int[][] {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    private static int solve() {
        boolean flag;
        int count = 0;

        while (true) {
            flag = false;

            // 뿌요 터뜨리기
            for (int i = 0; i < 12; i++) {
                for (int j = 0; j < 6; j++) {
                    if (map[i][j] != '.') {
                        if (bfs(i, j)) {
                            flag = true;
                        }
                    }
                }
            }

            if (!flag) {
                return count;
            }

            count++;

            // 아래로 하강
            for (int i = 0; i < 6; i++) {
                fall(i);
            }
        }
    }

    // 뿌요 아래로 하강
    private static void fall(int col) {
        int cursor = 11;
        for (int i = 11; i >= 0; i--) {
            if (map[i][col] != '.') {
                map[cursor][col] = map[i][col];
                cursor--;
            }
        }

        for (int i = cursor; i >= 0; i--) {
            map[i][col] = '.';
        }
    }

    // 4개 이상 인접해 있는 뿌요 터뜨리기
    private static boolean bfs(int startR, int startC) {
        ArrayDeque<int[]> dq = new ArrayDeque<>();
        boolean[][] visited = new boolean[12][6];

        dq.offer(new int[]{startR, startC});
        visited[startR][startC] = true;

        char color = map[startR][startC];
        int count = 0;

        while (!dq.isEmpty()) {
            int[] cur = dq.pollFirst();
            int r = cur[0];
            int c = cur[1];

            // 인접한 뿌요 개수 증가
            count++;

            for (int[] move : moves) {
                int nr = r + move[0];
                int nc = c + move[1];

                // 범위를 벗어나면
                if (nr < 0 || nc < 0 || nr >= 12 || nc >= 6) {
                    continue;
                }

                // 이미 방문했으면
                if (visited[nr][nc]) {
                    continue;
                }

                // 같은 색깔이 아니면
                if (color != map[nr][nc]) {
                    continue;
                }

                // 방문 처리
                visited[nr][nc] = true;

                // 같은 색깔이면 탐색
                dq.addLast(new int[]{nr, nc});
            }
        }

        // 인접한 뿌요가 4개 이상이면 터뜨리기
        if (count < 4) {
            return false;
        }

        // 터뜨린 뿌요는 . 으로 표시
        for (int i = 0; i < 12; i++) {
            for (int j = 0; j < 6; j++) {
                if (visited[i][j]) {
                    map[i][j] = '.';
                }
            }
        }

        return true;
    }

    private static void init() throws IOException {
        for (int i = 0; i < 12; i++) {
            String[] rows = br.readLine().split("");
            for (int j = 0; j < 6; j++) {
                map[i][j] = rows[j].charAt(0);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        init();
        System.out.println(solve());
    }
}
