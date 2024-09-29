import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 시험감독 {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer tokens;

    private static int N;
    private static int[] A;
    private static int B;
    private static int C;
    // 시험장의 개수(백만) * 응시자의 수(백만) = 1조
    // count가 1조까지 커질 수 있으므로 long type 사용
    private static long count;

    private static void solution() {
        for (int i = 0; i < N; i++) {
            int rest = A[i] - B;
            count++;
            if (rest > 0) {
                count += (int) Math.ceil((double) rest / C);
            }
        }
    }

    private static void init() throws IOException {
        N = Integer.parseInt(br.readLine());
        A = new int[N];

        tokens = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(tokens.nextToken());
        }

        tokens = new StringTokenizer(br.readLine());
        B = Integer.parseInt(tokens.nextToken());
        C = Integer.parseInt(tokens.nextToken());
        count = 0;
    }

    public static void main(String[] args) throws IOException {
        init();
        solution();
        System.out.println(count);
    }
}
