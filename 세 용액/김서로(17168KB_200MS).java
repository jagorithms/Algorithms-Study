import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer tokens;

    private static int N;
    private static long[] solutions; // 모든 용액의 특성값

    private static long[] answer;
    private static long closestToZeroValue; // 0에 가까운 특성값

    private static void solve() {
        for (int i = 0; i < N - 2; i++) {
            twoPointer(i);
            if (closestToZeroValue == 0) {
                return;
            }
        }
    }

    // 투 포인터
    private static void twoPointer(int left) {
        int mid = left + 1;
        int right = N - 1;

        while (mid < right) {
            long sum = solutions[left] + solutions[mid] + solutions[right];
            if (closestToZeroValue > Math.abs(sum)) {
                closestToZeroValue = Math.abs(sum);
                answer[0] = solutions[left];
                answer[1] = solutions[mid];
                answer[2] = solutions[right];
            }

            if (sum > 0) {
                right--;
            } else if (sum < 0) {
                mid++;
            } else {
                return;
            }
        }
    }

    // 입력
    private static void init() throws NumberFormatException, IOException {
        N = Integer.parseInt(br.readLine());
        solutions = new long[N];
        answer = new long[3];
        closestToZeroValue = 3000000000L;

        tokens = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            solutions[i] = Integer.parseInt(tokens.nextToken());
        }

        Arrays.sort(solutions);
    }

    public static void main(String[] args) throws NumberFormatException, IOException {
        init();
        solve();
        System.out.println(answer[0] + " " + answer[1] + " " + answer[2]);
    }

}
