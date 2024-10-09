import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer tokens;

    private static int N;
    private static int M;
    private static int[] streetlamp;

    private static void init() throws IOException {
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        streetlamp = new int[M];

        tokens = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            streetlamp[i] = Integer.parseInt(tokens.nextToken());
        }
    }

    private static int solve() {
        int mx = streetlamp[0];

        for (int i = 1; i < M; i++) {
            int distance = streetlamp[i] - streetlamp[i - 1];
            mx = Math.max(mx, (distance + 1) / 2);
        }

        mx = Math.max(mx, N - streetlamp[M - 1]);

        return mx;
    }

    public static void main(String[] args) throws IOException {
        init();
        System.out.println(solve());
    }
}
