import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringBuilder sb = new StringBuilder();
    private static int[] money;
    private static int N, goal;
    private static int[] method;
    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(br.readLine());
        for(int t = 0; t < T; t++) {
            N = Integer.parseInt(br.readLine());
            money = new int[N];

            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int i = 0; i < N; i++) {
                money[i] = Integer.parseInt(st.nextToken());
            }

            goal = Integer.parseInt(br.readLine());
            method = new int[goal + 1];

            knapsack();
            sb.append(method[goal]).append("\n");
        }
        System.out.println(sb);
    }

    private static void knapsack() {
        method[0] = 1;
        for(int i = 0; i < N; i++) {
            for(int j = money[i]; j <= goal; j++) {
                method[j] += method[j - money[i]];
            }
        }
    }
}
