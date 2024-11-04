import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int N, M;
    static int result = 0;
    static HashMap<Integer, List<int[]>> dic = new HashMap<>();
    static int[] depth;
    static boolean[] isbasic;
    static int[][] ingredient;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        for (int i = 1; i < N+1; i++) {
            dic.put(i, new ArrayList<>());
        }
        depth = new int[N+1];
        isbasic = new boolean[N+1];
        ingredient = new int[N+1][N+1];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            dic.get(b).add(new int[]{a,c}); // a를 만드려면 b가 c개 필요
            depth[a]+=1;
        }
        //System.out.println(Arrays.toString(depth));

        Deque<Integer> dq = new ArrayDeque<>();

        for (int i = 1; i < N+1; i++) {
            if(depth[i]==0){
                dq.offerLast(i);
                isbasic[i]= true;
                ingredient[i][i] = 1;
            }
        }

        while (!dq.isEmpty()){
            int b = dq.pollFirst();
            for(int[] arr : dic.get(b)){
                int a = arr[0];
                int c = arr[1];
                depth[a]-=1;

                for (int i = 1; i < N+1; i++) {
                    ingredient[a][i]+=ingredient[b][i]*c;
                }

                if(depth[a]==0){
                    dq.offerLast(a);
                }
            }
        }
        for (int i = 1; i < N+1; i++) {
            if(isbasic[i]){
                System.out.println(i + " " + ingredient[N][i]);
            }
        };
    }
}
