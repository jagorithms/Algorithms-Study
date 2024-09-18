import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// 277688KB_1072MS
public class 특정거리의도시찾기 {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer tokens;
    private static StringBuilder sb = new StringBuilder();

    private static int cityNum;
    private static int roadNum;
    private static int length;
    private static int start;

    private static ArrayList<ArrayList<Integer>> graph;
    private static PriorityQueue<int[]> pq;
    private static boolean[] visited;
    private static int[] distance;

    private static void solve() {
        boolean flag = false;

        for (int i = 1; i < cityNum + 1; i++) {
            if (distance[i] == length) {
                flag = true;
                sb.append(i).append("\n");
            }
        }

        if (!flag) {
            sb.append(-1);
        }
    }

    private static void dijkstra() {
        pq.add(new int[] {0, start});
        distance[start] = 0;

        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            int curCost = cur[0];
            int curCity = cur[1];

            // 방문 여부 확인
            if (visited[curCity]) {
                continue;
            }
            // 방문 처리
            visited[curCity] = true;
            // 다른 도시로 이동
            for (int nextCity : graph.get(curCity)) {
                int cost = curCost + 1;
                if (distance[nextCity] > cost) {
                    distance[nextCity] = cost;
                    pq.add(new int[] {cost, nextCity});
                }
            }
        }
    }

    private static void init() throws IOException {
        tokens = new StringTokenizer(br.readLine());
        cityNum = Integer.parseInt(tokens.nextToken());
        roadNum = Integer.parseInt(tokens.nextToken());
        length = Integer.parseInt(tokens.nextToken());
        start = Integer.parseInt(tokens.nextToken());

        graph = new ArrayList<>();
        for (int i = 0; i < cityNum + 1; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < roadNum; i++) {
            tokens = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(tokens.nextToken());
            int B = Integer.parseInt(tokens.nextToken());
            // A 도시에서 B 도시로 이동 가능
            graph.get(A).add(B);
        }

        pq = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return Integer.compare(o1[0], o2[0]);
            }
        });

        visited = new boolean[cityNum + 1];
        distance = new int[cityNum + 1];

        for (int i = 0; i < cityNum + 1; i++) {
            distance[i] = Integer.MAX_VALUE;
        }
    }

    public static void main(String[] args) throws IOException {
        init();
        dijkstra();
        solve();
        System.out.println(sb);
    }
}
