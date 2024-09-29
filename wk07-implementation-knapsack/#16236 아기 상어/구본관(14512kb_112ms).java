import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int N;
    static int[][] graph;
    static int[][] dydx = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public static void main(String[] args) throws Exception {
        N = Integer.parseInt(br.readLine());
        graph = new int[N][N];
        int startY = 0, startX = 0;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                graph[i][j] = Integer.parseInt(st.nextToken());
                if (graph[i][j] == 9) {
                    startY = i;
                    startX = j;
                    graph[i][j] = 0;
                }
            }
        }

        int result = 0;
        int fishSize = 2;
        int eatCount = 0;

        while (true) {
            Deque<int[]> dq = new ArrayDeque<>();
            dq.add(new int[]{startY, startX});
            boolean[][] visit = new boolean[N][N];
            visit[startY][startX] = true;

            List<int[]> fishList = new ArrayList<>();

            int dist = 0;
            while (!dq.isEmpty()) {
                dist+=1;
                int size = dq.size();
                for (int i = 0; i < size; i++) {
                    int[] pos = dq.poll();
                    int y = pos[0];
                    int x = pos[1];

                    for (int[] dyx : dydx) {
                        int ny = y + dyx[0];
                        int nx = x + dyx[1];

                        if (0 <= ny && ny < N && 0 <= nx && nx < N && !visit[ny][nx]) {
                            visit[ny][nx] = true;

                            if (graph[ny][nx] > 0 && graph[ny][nx] < fishSize) {
                                fishList.add(new int[]{ny, nx});
                            }

                            if (graph[ny][nx] == 0 || graph[ny][nx] == fishSize) {
                                dq.offerLast(new int[]{ny, nx});
                            }
                        }
                    }
                }
                if(!fishList.isEmpty()) {
                    break;
                }
            }

            if (fishList.isEmpty()) {
                break;
            }

            Collections.sort(fishList, (a, b) -> {
                if (a[0] == b[0]) {
                    return a[1] - b[1];
                }
                return a[0] - b[0];
            });

            int[] fish = fishList.get(0);
            startY = fish[0];
            startX = fish[1];
            result += dist;
            graph[startY][startX] = 0;

            eatCount++;

            if (eatCount == fishSize) {
                fishSize++;
                eatCount = 0;
            }
        }

        System.out.println(result);
    }
}
