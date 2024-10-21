import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    public static void main(String[] args) throws Exception {
        int N = Integer.parseInt(br.readLine());
        long[] arr = new long[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Long.parseLong(st.nextToken());
        }

        Arrays.sort(arr); // 배열을 오름차순으로 정렬

        long min = Long.MAX_VALUE; // 최소 합의 절대값을 저장할 변수
        long[] result = new long[3]; // 결과를 저장할 배열

        for (int i = 0; i < N-2; i++) {
            int l = i+1; // 왼쪽 포인터
            int r = N-1; // 오른쪽 포인터

            while (l < r) {
                long sum = arr[i] + arr[l] + arr[r]; // 세 수의 합

                if (Math.abs(sum) < min) {
                    min = Math.abs(sum);
                    result[0] = arr[i];
                    result[1] = arr[l];
                    result[2] = arr[r];
                }

                if (sum > 0) {
                    r--; // 합이 양수면 오른쪽 포인터를 왼쪽으로 이동
                } else {
                    l++; // 합이 음수면 왼쪽 포인터를 오른쪽으로 이동
                }
            }
        }

        System.out.println(result[0] + " " + result[1] + " " + result[2]);
    }
}
