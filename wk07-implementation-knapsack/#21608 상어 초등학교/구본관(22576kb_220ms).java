import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int N;
    static int[][] graph;
    static int[][] dydx = new int[][]{{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    static boolean[] likearr;

    static int[][] dic;

    public static void main(String[] args) throws Exception {
        N = Integer.parseInt(br.readLine());

        graph = new int[N][N];
        likearr = new boolean[N*N+1];
        dic = new int[N*N+1][4];

        for (int i = 0; i < N*N; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());

            likearr[b] = true;
            likearr[c] = true;
            likearr[d] = true;
            likearr[e] = true;

            dic[a] = new int[]{b,c,d,e};
            List<int[]> list = new ArrayList<>();

            for (int y = 0; y < N; y++) {
                for (int x = 0; x < N; x++) {
                    if(graph[y][x] == 0)
                        list.add(check(y,x));
                }
            }

            likearr[b] = false;
            likearr[c] = false;
            likearr[d] = false;
            likearr[e] = false;

            //System.out.println(Arrays.toString(list.get(4)));

            Collections.sort(list, new Comparator<int[]>() {
                @Override
                public int compare(int[] o1, int[] o2) {
                    if(o1[0] != o2[0])
                        return o2[0] - o1[0];
                    if(o1[1] != o2[1])
                        return o2[1] - o1[1];
                    if(o1[2] != o2[2])
                        return o1[2] - o2[2];
                    return o1[3] - o2[3];
                }
            });
            graph[list.get(0)[2]][list.get(0)[3]] = a;

            //System.out.println(Arrays.toString(list.get(0)));

//            for (int y = 0; y < N; y++) {
//                System.out.println(Arrays.toString(graph[y]));
//            }
//            System.out.println();
        }

        int result = 0;
        for (int y = 0; y < N; y++) {
            for (int x = 0; x < N; x++) {
                int count = 0;
                for(int[] dyx : dydx){
                    int ny = y + dyx[0];
                    int nx = x + dyx[1];

                    if(ny<0 || N<=ny || nx<0 || N<=nx) continue;

                    for (int i = 0; i < 4; i++) {
                        if(graph[ny][nx] == dic[graph[y][x]][i]) {
                            count+=1;
                            break;
                        }
                    }
                }
                if(count>0){
                    result += Math.pow(10, count-1);
                }
            }
        }
        System.out.println(result);
    }
    static int[] check(int y, int x) {
        int[] arr = {0,0,y,x};//좋아하는 학생수, 빈칸, 행, 열

        for(int[] dyx : dydx){
            int ny = y + dyx[0];
            int nx = x + dyx[1];

            if(ny<0 || N<=ny || nx<0 || N<=nx) continue;

            if(graph[ny][nx]==0){
                arr[1]+=1;
            }else{
                if(likearr[graph[ny][nx]]){
                    arr[0]+=1;
                }
            }
        }
        return arr;
    }
}
