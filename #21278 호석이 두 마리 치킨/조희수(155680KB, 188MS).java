package boj;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 @author ranuinclulus
 @since 2024.08.26
 @link https://www.acmicpc.net/problem/21278
 @timecomplex
 @performance 155680KB, 188MS
 @category
 @note
 */
public class goldFive21278 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static StringBuilder sb;
    static int n;
    static int m;
    static int[][] edges;
    static int minDistance = Integer.MAX_VALUE;;

    public void solution() throws IOException {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        edges = new int[n][n];

        for (int i = 0; i < n; i++) {
            Arrays.fill(edges[i], Integer.MAX_VALUE / 2);
            edges[i][i] = 0;
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken()) - 1;
            int end = Integer.parseInt(st.nextToken()) - 1;
            edges[start][end] = 1;
            edges[end][start] = 1;
        }

        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    edges[i][j] = Math.min(edges[i][j], edges[i][k] + edges[k][j]);
                }
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                calculate(i, j);
            }
        }

        bw.write(sb.toString());
        bw.flush();
    }

    private void calculate(int one, int two) {
        int sum = 0;
        for (int i = 0; i < n; i++) {
            sum += Math.min(edges[i][one], edges[i][two]) * 2;
        }
        if (sum < minDistance) {
            sb = new StringBuilder();
            sb.append(one + 1).append(" ").append(two + 1).append(" ").append(sum);
            minDistance = sum;
        }
    }


    public static void main(String[] args) throws IOException {
        new goldFive21278().solution();
    }
}
