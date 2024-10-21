import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer tokens;

    private static int N;
    private static int[][] classes;
    // 현재 진행 중인 수업
    private static PriorityQueue<Integer> pq;

    private static int solution() {
        // 첫 번째 수업의 끝나는 시간을 우선순위 큐에 삽입
        pq.add(classes[0][1]);

        for (int i = 1; i < N; i++) {
            int start = classes[i][0];
            int end = classes[i][1];

            if (pq.peek() <= start) {
                pq.poll();
            }

            pq.add(end);
        }

        return pq.size();
    }

    private static void init() throws IOException {
        N = Integer.parseInt(br.readLine());
        classes = new int[N][2];
        pq = new PriorityQueue<>();

        for (int i = 0; i < N; i++) {
            tokens = new StringTokenizer(br.readLine());
            classes[i][0] = Integer.parseInt(tokens.nextToken());
            classes[i][1] = Integer.parseInt(tokens.nextToken());
        }

        // 1. 수업이 시작하는 시간을 기준으로 정렬
        // 2. 수업이 끝나는 시간을 기준으로 정렬
        Arrays.sort(classes, new Comparator<int[]>() {

            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[0] != o2[0]) {
                    return Integer.compare(o1[0], o2[0]);
                }

                return Integer.compare(o1[1], o2[1]);
            }
        });
    }

    public static void main(String[] args) throws IOException {
        init();
        System.out.println(solution());
    }
}
