import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// 44820KB_448MS
public class 문제집 {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer tokens;
    private static StringBuilder sb = new StringBuilder();

    private static int N;
    private static int M;

    private static ArrayList<ArrayList<Integer>> graph;
    private static int[] inDegree;
    private static PriorityQueue<Integer> minHeap;

    private static void solve() {
        // 진입 차수가 0인 노드 큐에 삽입
        for (int i = 1; i <= N; i++) {
            if (inDegree[i] == 0) {
                minHeap.add(i);
            }
        }

        while (!minHeap.isEmpty()) {
            int node = minHeap.poll();
            sb.append(node).append(" ");

            for (int nextNode : graph.get(node)) {
                inDegree[nextNode]--;
                if (inDegree[nextNode] == 0) {
                    minHeap.add(nextNode);
                }
            }
        }
    }

    private static void init() throws IOException {
        tokens = new StringTokenizer(br.readLine());
        N = Integer.parseInt(tokens.nextToken());
        M = Integer.parseInt(tokens.nextToken());

        graph = new ArrayList<>();
        inDegree = new int[N + 1];
        minHeap = new PriorityQueue<>();

        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            tokens = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(tokens.nextToken());
            int B = Integer.parseInt(tokens.nextToken());

            graph.get(A).add(B);
            inDegree[B]++;
        }
    }

    public static void main(String[] args) throws IOException {
        init();
        solve();
        System.out.println(sb);
    }
}
