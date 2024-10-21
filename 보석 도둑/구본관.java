import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int N, K;
    static PriorityQueue<int[]> jewPQ = new PriorityQueue<>((a, b) -> a[0] - b[0]);
    static PriorityQueue<Integer> jewValPQ = new PriorityQueue<>(Collections.reverseOrder());
    static PriorityQueue<Integer> bags = new PriorityQueue<>();
    static long result = 0;

    public static void main(String[] args) throws Exception {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int weight = Integer.parseInt(st.nextToken());
            int value = Integer.parseInt(st.nextToken());
            jewPQ.add(new int[]{weight, value});
        }

        for (int i = 0; i < K; i++) {
            bags.add(Integer.parseInt(br.readLine()));
        }

        while (!bags.isEmpty()){
            int bag = bags.poll();
            while (!jewPQ.isEmpty() && jewPQ.peek()[0] <= bag) {
                jewValPQ.add(jewPQ.poll()[1]);
            }
            if (!jewValPQ.isEmpty()) {
                result += jewValPQ.poll();
            }
        }

        System.out.println(result);
    }
}
