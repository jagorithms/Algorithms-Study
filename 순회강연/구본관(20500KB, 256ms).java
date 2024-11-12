import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int N;
    static List<int[]> list = new ArrayList<>();
    static PriorityQueue<Integer> pq = new PriorityQueue<>();

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            list.add(new int[]{a,b});
        }

        Collections.sort(list, (a,b) -> (a[1]-b[1]));

        for (int i = 0; i < N; i++) {
            int size = pq.size();
            if(list.get(i)[1]<size){
                continue;
            }
            if(size<list.get(i)[1]){
                pq.add(list.get(i)[0]);
                continue;
            }
            if(!pq.isEmpty()  && pq.peek()<list.get(i)[0]){
                pq.poll();
                pq.add(list.get(i)[0]);
            }
        }
        int result = 0;
        while (!pq.isEmpty()){
            result += pq.poll();
            //System.out.println(result);
        }
        System.out.println(result);
    }
}
