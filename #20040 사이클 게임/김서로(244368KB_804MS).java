import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 사이클게임 {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer tokens;

    private static int n;
    private static int m;

    private static int[][] edges;
    private static int[] parents;

    // 집합 초기화
    private static void initSet() {
        parents = new int[n];

        // 각 트리의 높이를 음수로 표현
        for (int i = 0; i < n; i++) {
            parents[i] = -1;
        }
    }

    // 집합의 대표자 찾기
    private static int findSet(int a) {
        if (parents[a] == -1) {
            return a;
        }

        parents[a] = findSet(parents[a]);
        return parents[a];
    }

    // 같은 집합에 속해있는지 확인
    private static boolean isSameSet(int a, int b) {
        return findSet(a) == findSet(b);
    }

    // 합집합 연산
    private static boolean union(int a, int b) {
        if (isSameSet(a, b)) {
            return false;
        }

        int aRoot = findSet(a);
        int bRoot = findSet(b);

        // 두 트리의 높이가 같다면
        if (aRoot == bRoot) {
            parents[aRoot] = bRoot;
            parents[bRoot] -= 1; // 높이 1 증가
        // b 트리의 높이가 더 크다면 a 트리를 b 트리에 흡수
        } else if (aRoot > bRoot) {
            parents[aRoot] = bRoot;
        // a 트리의 높이가 더 크다면 b 트리를 a 트리에 흡수
        } else {
            parents[bRoot] = aRoot;
        }

        return true;
    }

    private static int solve() {
        initSet();
        int count = 0;

        for (int[] edge : edges) {
            count++;
            int a = edge[0];
            int b = edge[1];

            if (!union(a, b)) {
                return count;
            }
        }

        return 0;
    }

    private static void init() throws IOException {
        tokens = new StringTokenizer(br.readLine());
        n = Integer.parseInt(tokens.nextToken());
        m = Integer.parseInt(tokens.nextToken());

        edges = new int[m][2];
        for (int i = 0; i < m; i++) {
            tokens = new StringTokenizer(br.readLine());
            edges[i][0] = Integer.parseInt(tokens.nextToken());
            edges[i][1] = Integer.parseInt(tokens.nextToken());
        }
    }

    public static void main(String[] args) throws IOException {
        init();
        System.out.println(solve());
    }
}
