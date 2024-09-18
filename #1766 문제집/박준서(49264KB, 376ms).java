import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static int N,M;
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static PriorityQueue<Integer> queue = new PriorityQueue();
    private static List<List<Integer>> problem;
    private static int[] link;
    private static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        problem = new ArrayList<>();
        for(int i = 0; i < N; i++){
            problem.add(new ArrayList<>());
        }

        link = new int[N];

        for(int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken())-1;
            int b = Integer.parseInt(st.nextToken())-1;
            problem.get(a).add(b);
            link[b] += 1;
        }

        for(int i = 0; i < N; i++){
            if(link[i] == 0) queue.add(i);
        }

        while(!queue.isEmpty()) {
            int cur = queue.poll();
            sb.append(cur + 1).append(" ");
            for (int next : problem.get(cur)) {
                link[next] -= 1;
                if (link[next] == 0) queue.add(next);
            }
        }
        
        System.out.println(sb);
    }

}
