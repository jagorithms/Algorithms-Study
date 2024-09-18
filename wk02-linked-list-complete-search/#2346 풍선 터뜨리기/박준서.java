package august.ten;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class burst_balloon {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static Deque<int[]> balloons = new ArrayDeque<>();
    private static List<Integer> answer = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine());
        String[] input = br.readLine().split(" ");
        int idx = 1;
        for (int i = 0; i < N; i++) {
            balloons.add(new int[]{Integer.parseInt(input[i]), idx++});
        }
        burst();
        for(int a : answer){
            System.out.print(a + " ");
        }
    }

    private static void burst() {
        while(!balloons.isEmpty()){
            int[] curr = balloons.pollFirst();
            answer.add(curr[1]);
            int steps = curr[0];

            if(balloons.isEmpty()){
                break;
            }

            if (curr[0] > 0){
                for (int i = 0; i < steps-1; i++) {
                    balloons.addLast(balloons.pollFirst());
                }
            }
            if (curr[0] < 0){
                steps = -steps;
                for (int i = 0; i < steps; i++) {
                    balloons.addFirst(balloons.pollLast());
                }
            }
        }
    }
}
