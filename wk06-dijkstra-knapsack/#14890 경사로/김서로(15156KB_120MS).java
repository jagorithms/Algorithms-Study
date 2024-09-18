import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 경사로 {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer tokens;

    private static int N;
    private static int L; // 경사로 길이
    private static int[][] matrix;
    private static int[][] transposeMatrix;
    private static int count;

    private static void solution() {
        // 모든 행에 대하여 경사로인지 판단
        for (int i = 0; i < N; i++) {
            if (isRamp(matrix[i])) {
                count++;
            }
        }

        // 모든 열에 대하여 경사로인지 판단
        for (int i = 0; i < N; i++) {
            if (isRamp(transposeMatrix[i])) {
                count++;
            }
        }
    }

    // 경사로인지 판단
    private static boolean isRamp(int[] way) {
        boolean[] visited = new boolean[N];

        for (int i = 1; i < N; i++) {
            // 이전 칸과 높이가 같으면
            if (way[i - 1] == way[i]) {
                continue;
            // 이전 칸보다 1칸 높으면
            } else if (way[i - 1] + 1 == way[i]) {
                int idx = i;
                for (int j = 0; j < L; j++) {
                    idx--;
                    if (idx < 0) return false;
                    if (visited[idx]) return false;
                    if (way[idx] != way[i - 1]) return false;
                    visited[idx] = true;
                }
            // 이전 칸보다 1칸 낮으면
            } else if (way[i - 1] - 1 == way[i]) {
                int idx = i - 1;
                for (int j = 0; j < L; j++) {
                    idx++;
                    if (idx >= N) return false;
                    if (visited[idx]) return false;
                    if (way[idx] != way[i]) return false;
                    visited[idx] = true;
                }
            // 이전 칸과 2칸 이상 차이 나면
            } else {
                return false;
            }
        }
        return true;
    }

    private static void init() throws IOException {
        tokens = new StringTokenizer(br.readLine());
        N = Integer.parseInt(tokens.nextToken());
        L = Integer.parseInt(tokens.nextToken());

        matrix = new int[N][N];
        transposeMatrix = new int[N][N];

        for (int i = 0; i < N; i++) {
            tokens = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                matrix[i][j] = Integer.parseInt(tokens.nextToken());
                transposeMatrix[j][i] = matrix[i][j];
            }
        }
    }

    public static void main(String[] args) throws IOException {
        init();
        solution();
        System.out.println(count);
    }
}
