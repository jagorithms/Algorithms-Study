
import java.util.*;
import java.io.*;

public class Main {
    static int N, M, K, X, visited[];
    static List<Integer> ans = new ArrayList<>();
    static Map<Integer, List<Integer>> map = new HashMap<>();


    static void bfs(int start) {
        visited = new int[N+1];
        ArrayDeque<Integer> queue = new ArrayDeque<>();

        visited[start] = 1;
        queue.add(start);
        while (!queue.isEmpty()) {
            int node = queue.pollFirst();

            // System.out.println("Start from: " + node);

            if (visited[node] == K + 1) ans.add(node);

            for (int neighbor : map.get(node)) {
                if (visited[neighbor] == 0) {
                    // System.out.println("  Exploring: "+neighbor);
                    visited[neighbor] = visited[node] + 1;
                    queue.add(neighbor);
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder output = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());  
        K = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());

        for (int i = 1; i <= N; i++) {
            map.put(i, new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            map.get(a).add(b);
        }

        bfs(X);
        if (ans.isEmpty()) {
            output.append(-1);
        } else {
            Collections.sort(ans);
            for (int a : ans) {
                output.append(a).append("\n");
            }
        }

        bw.write(output.toString());
        bw.flush();
        br.close();
        bw.close();
    }
}
