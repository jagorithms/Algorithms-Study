import java.io.*;
import java.util.*;
public class Main{
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[][] schedules = new int[n][2];
        for (int i = 0; i < n ; i++) {
            String[] input = br.readLine().split(" ");
            schedules[i][0] = Integer.parseInt(input[0]);
            schedules[i][1] = Integer.parseInt(input[1]); 
        }
        Arrays.sort(schedules, (a, b) -> Integer.compare(a[0], b[0]));

        PriorityQueue<Integer> pq = new PriorityQueue<>();
        pq.add(schedules[0][1]);
        for (int i = 1; i < n; i++) {
            int start = schedules[i][0];
            int end = schedules[i][1];
            if (start >= pq.peek()) {
                pq.poll();
                pq.add(end);
            } else {
                pq.add(end);
            }
        }
        br.close();
        System.out.println(pq.size());
    }
}
