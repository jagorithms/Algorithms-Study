import java.io.*;
import java.util.StringTokenizer;

/*
 @author ranuinclulus
 @since 2024.08.28
 @link https://www.acmicpc.net/problem/1717
 @timecomplex
 @performance 51988KB, 392MS
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
    static int[] parent;
    static int operation;
    static int first;
    static int second;

    public void solution() throws IOException {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        parent = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            parent[i] = i;
        }
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            operation = Integer.parseInt(st.nextToken());
            first = Integer.parseInt(st.nextToken());
            second = Integer.parseInt(st.nextToken());

            if(operation == 0) {
                union(first, second);
            } else {
                sb.append(find(first) == find(second) ? "YES" : "NO").append('\n');
            }
        }
        bw.write(sb.toString());
        bw.flush();
    }

    private int find(int target) {
        if (parent[target] == target) {
            return target;
        } else return parent[target] = find(parent[target]);
    }

    private void union(int first, int second) {
        int firstParent = find(first);
        int secondParent = find(second);

        if (firstParent > secondParent) {
            parent[firstParent] = secondParent;
        } else parent[secondParent] = firstParent;
    }

    public static void main(String[] args) throws IOException {
        new Main().solution();
    }
}
