import java.io.*;
import java.util.*;

/*
 @author ranuinclulus
 @since 2024.10.17
 @link https://www.acmicpc.net/problem/2473
 @timecomplex
 @performance
 @category
 @note
 */
public class Main {
    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder builder = new StringBuilder();
    static StringTokenizer tokenizer;
    static int n;
    static long[] solutions;
    static long[] answer;

    public void solution() throws IOException {
        n = Integer.parseInt(reader.readLine());
        solutions = new long[n];
        answer = new long[3];

        tokenizer = new StringTokenizer(reader.readLine());
        for (int i = 0; i < n; i++) {
            solutions[i] = Long.parseLong(tokenizer.nextToken());
        }

        Arrays.sort(solutions);

        long minAbs = Long.MAX_VALUE;
        for (int i = 0; i < n - 2; i++) {
            int left = i + 1;
            int right = n - 1;
            while (left < right) {
                long sum = solutions[left] + solutions[right] + solutions[i];
                long absSum = Math.abs(sum);

                if (absSum < minAbs) {
                    answer[0] = solutions[i];
                    answer[1] = solutions[left];
                    answer[2] = solutions[right];
                    minAbs = absSum;
                }

                if (sum < 0) left++;
                else if (sum > 0) right--;
                else break;
            }
        }

        //Arrays.sort(answer);
        for(long value : answer) {
            builder.append(value + " ");
        }
        writer.write(builder.toString());
        writer.flush();
    }

    public static void main(String[] args) throws IOException {
        new Main().solution();
    }
}

/*


5
-2 6 -97 -6 98
[-97, -6, -2, 6, 98]

7
-2 -3 -24 -6 98 100 61
[-24, -6, -3, -2, 61, 98, 100]

5
-99 0 1 1 99
 */
