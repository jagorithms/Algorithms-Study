import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(stringTokenizer.nextToken());
        int k = Integer.parseInt(stringTokenizer.nextToken());

        Deque<Integer> dq = new ArrayDeque<>();

        for (int i = 0; i < n; i++) {
            dq.offerLast(i+1);
        }

        StringBuilder sb = new StringBuilder();

        sb.append("<");

        while(!dq.isEmpty()){
            for (int i = 0; i < k-1; i++) {
                dq.offerLast(dq.pollFirst());
            }
            sb.append(dq.pollFirst() + ", ");
        }
        System.out.println(sb.toString().substring(0, sb.length() - 2) + ">");
    }
}
