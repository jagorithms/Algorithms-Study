import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int N;
    static boolean[][] graph;
    static boolean[] visit;
    static boolean[] cycle;
    static boolean isEnd = false;
    static int[] distance;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        graph = new boolean[N + 1][N + 1];
        visit = new boolean[N + 1];
        cycle = new boolean[N + 1];
        distance = new int[N + 1];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            graph[a][b] = true;
            graph[b][a] = true;
        }

        dfs(1, 0);

        bfs();

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= N; i++) {
            sb.append(distance[i]).append(" ");
        }
        System.out.println(sb.toString().trim());
    }

    public static boolean dfs(int index, int parent) {
        visit[index] = true;
        for (int dest = 1; dest <= N; dest++) {
            if (graph[index][dest] && dest != parent) { // 경로가 있고 부모와 다를 때
                if (!visit[dest]) { // 방문한 적이 없을 때
                    if (dfs(dest, index)) {
                        if (!cycle[index]) {
                            if (isEnd) return false;
                            cycle[index] = true;
                            return true;
                        } else {
                            isEnd = true;
                        }
                    }
                } else if (!cycle[dest]) { // 이미 방문했고 순환 발견 시
                    cycle[index] = true;
                    cycle[dest] = true;
                    return true;
                }
            }
        }
        return false;
    }

    public static void bfs() {
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 1; i <= N; i++) {
            if (cycle[i]) {
                queue.add(i);
            }
        }

        while (!queue.isEmpty()) {
            int st = queue.poll();
            for (int i = 1; i <= N; i++) {
                if (graph[st][i] && !cycle[i] && distance[i] == 0) {
                    distance[i] = distance[st] + 1;
                    queue.add(i);
                }
            }
        }
    }
}
