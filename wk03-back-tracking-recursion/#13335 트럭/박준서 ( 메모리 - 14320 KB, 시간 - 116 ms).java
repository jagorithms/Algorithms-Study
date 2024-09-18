import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static int N, W, L;
    private static Deque<Integer> cars = new ArrayDeque<>();
    private static Deque<Integer> bridge = new ArrayDeque<>();
    private static int time = 0;

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            cars.add(Integer.parseInt(st.nextToken()));
        }
        calculateTime();
        System.out.println(time);
    }

    private static void calculateTime() {
        int currentWeight = 0;
        for (int i = 0; i < W; i++) {
            bridge.add(0);
        }
        while (!bridge.isEmpty()) {
            time++;
            currentWeight -= bridge.pollFirst();
            if (!cars.isEmpty()) {
                if (currentWeight + cars.peekFirst() <= L) {
                    int car = cars.pollFirst();
                    bridge.addLast(car);
                    currentWeight += car;
                } else {
                    bridge.addLast(0);
                }
            }
        }
    }
}
