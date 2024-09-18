import java.io.*;
import java.util.*;

/*
 @author ranuinclulus
 @since 2024.08.28
 @link https://www.acmicpc.net/problem/1766
 @timecomplex
 @performance 51988 KB, 392 MS
 @category
 @note
 */
public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static int n;
    static int m;
    static int[] degree;
    static List<List<Integer>> edges;

    public void solution() throws IOException {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        edges = new LinkedList<>();
        degree = new int[n];

        for (int i = 0; i < n; i++) {
            edges.add(new LinkedList<>());
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken()) - 1;
            int end = Integer.parseInt(st.nextToken()) - 1;
            degree[end]++;
            edges.get(start).add(end);
        }

        PriorityQueue<Integer> queue = new PriorityQueue<>();
        for (int i = 0; i < n; i++) {
            if (degree[i] == 0) {
                queue.add(i);
                degree[i] = -1;
            }
        }

        while (!queue.isEmpty()) {
            int now = queue.poll();
            sb.append(now + 1).append(" ");

            for(int next : edges.get(now)) {
                degree[next]--;
            }
            for (int i = 0; i < n; i++) {
                if (degree[i] == 0) {
                    queue.add(i);
                    degree[i] = -1;
                }
            }
        }

        bw.write(sb.toString());
        bw.flush();
    }

    public static void main(String[] args) throws IOException {
        new Main().solution();
    }
}
