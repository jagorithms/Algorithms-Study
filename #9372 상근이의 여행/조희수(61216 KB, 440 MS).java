import java.io.*;
import java.util.*;

/*
 @author ranuinclulus
 @since 2024.08.28
 @link https://www.acmicpc.net/problem/9372
 @timecomplex
 @performance 61216 KB, 440 MS
 @category
 @note
 */
public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static int testNum;
    static int n;
    static int m;
    static List<List<Integer>> flights;


    public void solution() throws IOException {
        testNum = Integer.parseInt(br.readLine());
        for (int test = 0; test < testNum; test++) {
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());

            flights = new LinkedList<>();

            for (int i = 0; i < n; i++) {
                flights.add(new LinkedList<>());
            }

            for (int i = 0; i < m; i++) {
                st = new StringTokenizer(br.readLine());
                int first = Integer.parseInt(st.nextToken()) - 1;
                int second = Integer.parseInt(st.nextToken()) - 1;

                flights.get(first).add(second);
                flights.get(second).add(first);
            }

            sb.append(n - 1).append('\n');
        }
        bw.write(sb.toString());
        bw.flush();
    }

  

    public static void main(String[] args) throws IOException {
        new Main().solution();
    }
}
