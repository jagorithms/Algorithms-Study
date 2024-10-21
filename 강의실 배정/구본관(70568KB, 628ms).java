import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    public static void main(String[] args) throws Exception {
        int N = Integer.parseInt(br.readLine());

        int result = 0;
        int count = 0;

        PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] a, int[] b) {
                if(a[0] == b[0]){
                    return a[1] - b[1];
                    }
                return a[0]-b[0];
            }
        });

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            pq.add(new int[]{Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())});
        }

        while (!pq.isEmpty()){
            int[] arr = pq.poll();

            int a = arr[0];
            int b = arr[1];

            if(b>0){
                if(count==0){
                    result+=1;
                }else{
                    count-=1;
                }
                pq.add(new int[] {b, 0});
            }else{
                count+=1;
            }
        }
        System.out.println(result);
    }
}
