import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static Deque<Integer> truckdq = new ArrayDeque<>();
    static Deque<Integer> bridgedq = new ArrayDeque<>();


    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int W = Integer.parseInt(st.nextToken());
        int L = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            truckdq.offerLast(Integer.parseInt(st.nextToken()));
        }

        for (int i = 0; i < W; i++) {
            bridgedq.offerLast(0);
        }
        int sum = 0;
        int time = 0;

        while(!truckdq.isEmpty()){
            time+=1;
            sum -= bridgedq.pollLast();
            if(sum + truckdq.peekFirst() <= L){
                sum += truckdq.peekFirst();
                bridgedq.offerFirst(truckdq.pollFirst());
            }else{
                bridgedq.offerFirst(0);
            }
        }
        System.out.println(time + W);


    }
}
