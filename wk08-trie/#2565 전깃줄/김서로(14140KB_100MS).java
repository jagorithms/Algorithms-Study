import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer tokens;

    private static int N;
    private static int[][] edges;
    private static int[] lis;

    private static void init() throws IOException {
        N = Integer.parseInt(br.readLine());
        edges = new int[N][2];
        lis = new int[N];

        for (int i = 0; i < N; i++) {
            tokens = new StringTokenizer(br.readLine());
            edges[i][0] = Integer.parseInt(tokens.nextToken());
            edges[i][1] = Integer.parseInt(tokens.nextToken());
        }

        Arrays.sort(edges, (a, b) -> {
            return a[0] - b[0];
        });
    }

    private static int binarySearch(int left, int right, int value) {
        while (left < right) {
            int mid = (left + right) / 2;
            if (lis[mid] < value) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        return right;
    }

    private static int solve() {
        int len = 0; // lis 배열의 길이
        int idx = 0;
        lis[len] = edges[0][1];

        for (int i = 1; i < N; i++) {
            if (lis[len] < edges[i][1]) {
                len++;
                lis[len] = edges[i][1];
            } else {
                idx = binarySearch(0, len, edges[i][1]);
                lis[idx] = edges[i][1];
            }
        }

        return N - len - 1;
    }

    public static void main(String[] args) throws IOException {
        init();
        System.out.println(solve());
    }
}
