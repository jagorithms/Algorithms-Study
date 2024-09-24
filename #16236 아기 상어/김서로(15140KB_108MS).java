import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class 아기상어 {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer tokens;
    private static StringBuilder sb = new StringBuilder();

    private static int N;
    private static int[][] map;
    private static int fishCount; // 전체 물고기 수

    private static boolean[][] visited;
    private static boolean[][] canEat;
    private static ArrayDeque<int[]> dq;
    private static int[][] deltas = new int[][] {{-1, 0}, {0, -1}, {0, 1}, {1, 0}};

    private static Shark shark;

    private static int bfs() {
        dq = new ArrayDeque<>();
        dq.add(new int[]{shark.r, shark.c, 0});
        visited = new boolean[N][N];
        canEat = new boolean[N][N];
        int minDistance = Integer.MAX_VALUE;

        while (!dq.isEmpty()) {
            int[] cur = dq.pollFirst();
            int r = cur[0];
            int c = cur[1];
            int move = cur[2];

            // 범위를 벗어나면
            if (r < 0 || r >= N || c < 0 || c >= N) {
                continue;
            }

            // 이미 방문했으면
            if (visited[r][c]) {
                continue;
            }

            // 방문 처리
            visited[r][c] = true;

            // 먹을 수 있는 가장 가까운 물고기보다 거리가 멀면
            if (move > minDistance) {
                break;
            }

            // 물고기가 있으면
            if (map[r][c] > 0) {
                // 물고기가 상어보다 크면 pass
                if (map[r][c] > shark.size) {
                    continue;
                }
                // 상어가 물고기보다 크면 먹음
                if (map[r][c] < shark.size) {
                    minDistance = move;
                    canEat[r][c] = true;
                    continue;
                }
            }

            // 사방 탐색
            for (int[] delta : deltas) {
                int nr = r + delta[0];
                int nc = c + delta[1];
                dq.addLast(new int[]{nr, nc, move + 1});
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (canEat[i][j]) {
                    shark.eat();
                    shark.r = i;
                    shark.c = j;
                    map[i][j] = 0;
                    return minDistance;
                }
            }
        }

        return -1;
    }

    private static int solution() {
        int time = 0;
        while (fishCount > 0) {
            int move = bfs();
            if (move == -1) {
                break;
            }
            time += move;
            fishCount--;
        }
        return time;
    }

    private static void init() throws IOException {
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        fishCount = 0;

        for (int i = 0; i < N; i++) {
            tokens = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(tokens.nextToken());
                if (map[i][j] == 9) {
                    // 아기 상어 객체 생성
                    shark = new Shark(i, j);
                    map[i][j] = 0;
                } else if (map[i][j] > 0) {
                    // 전체 물고기 수 카운트
                    fishCount++;
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        init();
        System.out.println(solution());
    }

    static class Shark {
        int r;
        int c;
        int size;
        int eatCount;

        Shark(int r, int c) {
            this.r = r;
            this.c = c;
            this.size = 2;
            this.eatCount = 0;
        }

        void eat() {
            eatCount++;
            // 현재 먹은 물고기가 2마리면 상어 크기 증가
            if (eatCount == this.size) {
                eatCount = 0;
                size++;
            }
        }
    }
}
