import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 50092KB_388MS
public class 집합의표현 {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer tokens;
    private static StringBuilder sb = new StringBuilder();

    private static int N;
    private static int M;
    private static int[] parents;

    private static void init() {
        parents = new int[N + 1];

        // 각 트리의 높이를 음수로 표현
        for (int i = 0; i < N + 1; i++) {
            parents[i] = -1;
        }
    }

    private static int findSet(int a) {
        if (parents[a] == -1) {
            return a;
        }

        parents[a] = findSet(parents[a]);
        return parents[a];
    }

    private static boolean isSameSet(int a, int b) {
        return findSet(a) == findSet(b);
    }

    private static boolean union(int a, int b) {
        if (isSameSet(a, b)) {
            return false;
        }

        int aRoot = findSet(a);
        int bRoot = findSet(b);

        // 두 트리의 높이가 같다면
        if (aRoot == bRoot) {
            parents[aRoot] = bRoot;
            parents[bRoot] = -1; // 높이 1 증가
        // b 트리의 높이가 더 크다면 a 트리를 b 트리에 흡수
        } else if (aRoot > bRoot) {
            parents[aRoot] = bRoot;
        // a 트리의 높이가 더 크다면 b 트리를 a 트리에 흡수
        } else {
            parents[bRoot] = aRoot;
        }

        return true;
    }

    private static void solve() throws IOException {
        for (int i = 0; i < M; i++) {
            tokens = new StringTokenizer(br.readLine());
            int cmd = Integer.parseInt(tokens.nextToken());
            int a = Integer.parseInt(tokens.nextToken());
            int b = Integer.parseInt(tokens.nextToken());

            switch (cmd) {
                case 0:
                    union(a, b);
                    break;
                case 1:
                    if (isSameSet(a, b)) {
                        sb.append("YES\n");
                    } else {
                        sb.append("NO\n");
                    }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        tokens = new StringTokenizer(br.readLine());
        N = Integer.parseInt(tokens.nextToken());
        M = Integer.parseInt(tokens.nextToken());

        init();
        solve();
        System.out.println(sb);
    }
}
