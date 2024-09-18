package august.two;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Rotation_Queue {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static Deque<Integer> deque = new ArrayDeque<>();
    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[] arr = new int[M];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        for (int i = 1; i <= N; i++) {
            deque.add(i);
        }

        int answer = 0;
        for (int i = 0; i < M; i++) {
            int now = findPosition(arr[i]);
            while (true) {
                if (now == 0) {
                    deque.pollFirst();
                    break;
                } else if (now <= deque.size() / 2) {
                    second();
                    answer++;
                } else {
                    third();
                    answer++;
                }
                now = findPosition(arr[i]);
            }
        }
            System.out.println(answer);

    }

    private static int findPosition(int target){
        int idx = 0;
        for(int value : deque){
            if(value == target){
                break;
            }else{
                idx++;
            }
        }
        return idx;
    }

    private static void second(){
        int a = deque.pollFirst();
        deque.offerLast(a);
    }

    private static void third(){
        int a = deque.pollLast();
        deque.offerFirst(a);
    }
}
