package boj;

import java.io.*;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

/*
 @author ranuinclulus
 @since 2024.08.22
 @link https://www.acmicpc.net/problem/2212
 @timecomplex
 @performance 18388KB, 196MS
 @category
 @note
 */
public class goldFive2212 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static int n;
    static int k;
    static int[] sensors;
    static Integer[] distance;
    static int answer;

    public void solution() throws IOException {
        n = Integer.parseInt(br.readLine());
        k = Integer.parseInt(br.readLine());
        if (k > n) {
            sb.append(0);
        } else {
            sensors = new int[n];
            distance = new Integer[n - 1];
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < n; i++) {
                sensors[i] = Integer.parseInt(st.nextToken());
            }
            Arrays.sort(sensors);
            for (int i = 0; i < n - 1; i++) {
                distance[i] = sensors[i + 1] - sensors[i];
            }
            Arrays.sort(distance, Collections.reverseOrder());

            // 센서가 두 개면 거리 하나를 뛰어넘을 수 있음
            for (int i = k - 1; i < n - 1; i++) {
                answer += distance[i];
            }

            sb.append(answer);
        }
        bw.write(sb.toString());
        bw.flush();
    }




    public static void main(String[] args) throws IOException {
        new goldFive2212().solution();
    }
}
