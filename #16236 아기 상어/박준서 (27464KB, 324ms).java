import java.util.*;

public class Main {
    static int N;
    static int[][] space;
    static int[] dx = {-1, 1, 0, 0}; // 상하좌우 방향
    static int[] dy = {0, 0, -1, 1}; // 상하좌우 방향
    static Queue<int[]> sharkPos = new LinkedList<>();
    static List<int[]> fish = new ArrayList<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        space = new int[N][N];

        // 입력 받기
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                space[i][j] = sc.nextInt();
                if (space[i][j] == 9) {
                    sharkPos.add(new int[]{i, j}); // 상어 위치
                    space[i][j] = 0; // 상어가 있던 자리는 빈 칸으로
                }
                if (space[i][j] > 0 && space[i][j] < 7) {
                    fish.add(new int[]{i, j}); // 물고기 위치 저장
                }
            }
        }

        int sharkSize = 2; // 아기 상어 초기 크기
        int time = 0; // 엄마 부를 때까지 걸린 시간
        int fishCount = 0; // 상어가 먹은 물고기 수

        while (true) {
            if (fishCount == sharkSize) {
                sharkSize++; // 상어 크기 증가
                fishCount = 0;
            }

            int[] currentPos = sharkPos.peek();
            int sharkX = currentPos[0];
            int sharkY = currentPos[1];
            int[][] distance = bfs(sharkX, sharkY, sharkSize);

            List<int[]> candidates = new ArrayList<>();
            int minDistance = Integer.MAX_VALUE;

            for (int[] f : fish) {
                int x = f[0];
                int y = f[1];
                if (distance[x][y] != 0 && space[x][y] < sharkSize) { // 상어가 먹을 수 있는 물고기
                    if (distance[x][y] < minDistance) {
                        minDistance = distance[x][y];
                        candidates.clear();
                        candidates.add(new int[]{x, y});
                    } else if (distance[x][y] == minDistance) {
                        candidates.add(new int[]{x, y});
                    }
                }
            }

            if (candidates.size() == 0) {
                break; // 더 이상 먹을 수 있는 물고기가 없으면 종료
            }

            candidates.sort((a, b) -> {
                if (a[0] != b[0]) return Integer.compare(a[0], b[0]);
                else return Integer.compare(a[1], b[1]);
            });

            int[] targetFish = candidates.get(0); // 가장 위, 가장 왼쪽에 있는 물고기 선택
            int targetX = targetFish[0];
            int targetY = targetFish[1];

            space[targetX][targetY] = 0; // 물고기 먹힘
            fishCount++; // 먹은 물고기 수 증가
            time += minDistance; // 시간 누적

            // 상어 위치 이동
            sharkPos.poll();
            sharkPos.add(new int[]{targetX, targetY});

            // 물고기 리스트에서 해당 물고기 제거
            fish.removeIf(f -> f[0] == targetX && f[1] == targetY);
        }

        // 결과 출력
        System.out.println(time);
    }

    // BFS 탐색
    public static int[][] bfs(int sharkX, int sharkY, int sharkSize) {
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{sharkX, sharkY});
        boolean[][] visited = new boolean[N][N];
        int[][] distance = new int[N][N];

        visited[sharkX][sharkY] = true;

        while (!q.isEmpty()) {
            int[] current = q.poll();
            int x = current[0];
            int y = current[1];

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (nx >= 0 && nx < N && ny >= 0 && ny < N && !visited[nx][ny] && sharkSize >= space[nx][ny]) {
                    q.add(new int[]{nx, ny});
                    visited[nx][ny] = true;
                    distance[nx][ny] = distance[x][y] + 1;
                }
            }
        }

        return distance;
    }
}
