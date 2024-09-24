import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class 게리맨더링 {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer tokens;

    private static int N;
    private static int[] areas;
    private static boolean[][] matrix; // 인접 행렬

    private static boolean[] choosed;
    private static int minDiffer;

    // 모든 부분 집합 구하기
    private static void makeSet(int depth) {
        if (depth == N + 1) {
            // 인접해 있으면 인구의 차이 계산
            if (isNear()) {
                minDiffer = Math.min(minDiffer, calcPopulation());
            }
            return;
        }

        choosed[depth] = true;
        makeSet(depth + 1);
        choosed[depth] = false;
        makeSet(depth + 1);
    }

    private static int calcPopulation() {
        int a = 0;
        int b = 0;

        for (int i = 1; i <= N; i++) {
            if (choosed[i]) {
                a += areas[i];
            } else {
                b += areas[i];
            }
        }

        return Math.abs(a - b);
    }

    // 인접해 있는지 확인
    private static boolean isNear() {
        ArrayDeque<Integer> dq1 = new ArrayDeque<>();
        ArrayDeque<Integer> dq2 = new ArrayDeque<>();
        boolean[] visited1 = new boolean[N + 1];
        boolean[] visited2 = new boolean[N + 1];

        for (int i = 1; i <= N; i++) {
            if (choosed[i]) {
                dq1.offer(i);
                break;
            }
        }

        for (int i = 1; i <= N; i++) {
            if (!choosed[i]) {
                dq2.offer(i);
                break;
            }
        }

        // 각 선거구가 적어도 하나의 구역을 포함하고 있지 않다면
        if (dq1.isEmpty() || dq2.isEmpty()) {
            return false;
        }

        // 첫 번째 선거구에 대하여 모두 인접한지 확인
        while (!dq1.isEmpty()) {
            int cur = dq1.pollFirst();

            if (visited1[cur]) {
                continue;
            }

            visited1[cur] = true;

            for (int i = 1; i <= N; i++) {
                if (matrix[cur][i] && choosed[i]) {
                    dq1.addLast(i);
                }
            }
        }

        for (int i = 1; i <= N; i++) {
            if (choosed[i] && !visited1[i]) {
                return false;
            }
        }

        // 두 번째 선거구에 대하여 모두 인접한지 확인
        while (!dq2.isEmpty()) {
            int cur = dq2.pollFirst();

            if (visited2[cur]) {
                continue;
            }

            visited2[cur] = true;

            for (int i = 1; i <= N; i++) {
                if (matrix[cur][i] && !choosed[i]) {
                    dq2.addLast(i);
                }
            }
        }

        for (int i = 1; i <= N; i++) {
            if (!choosed[i] && !visited2[i]) {
                return false;
            }
        }

        return true;
    }

    private static void init() throws IOException {
        N = Integer.parseInt(br.readLine());
        areas = new int[N + 1];
        matrix = new boolean[N + 1][N + 1];
        choosed = new boolean[N + 1];
        minDiffer = Integer.MAX_VALUE;

        tokens = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            areas[i] = Integer.parseInt(tokens.nextToken());
        }

        for (int from = 1; from <= N; from++) {
            tokens = new StringTokenizer(br.readLine());
            int M = Integer.parseInt(tokens.nextToken());
            for (int i = 0; i < M; i++) {
                int to = Integer.parseInt(tokens.nextToken());
                matrix[from][to] = true;
                matrix[to][from] = true;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        init();
        makeSet(1);
        if (minDiffer == Integer.MAX_VALUE) {
            System.out.println(-1);
        } else {
            System.out.println(minDiffer);
        }
    }
}
