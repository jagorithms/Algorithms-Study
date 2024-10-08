import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int N, M, k;
    static smell[][] graph;
    static int[][] dydx = {{-1,0},{1,0},{0,-1},{0,1}};

    public static void main(String[] args) throws Exception {
       st = new StringTokenizer(br.readLine());
       N = Integer.parseInt(st.nextToken());
       M = Integer.parseInt(st.nextToken());
       k = Integer.parseInt(st.nextToken());

       graph = new smell[N][N];

       List<shark> list = new ArrayList<>();
        for (int i = 0; i < M; i++) {
            list.add(new shark());
        }

        for (int y = 0; y < N; y++) {
            st = new StringTokenizer(br.readLine());
            for (int x = 0; x < N; x++) {
                int t = Integer.parseInt(st.nextToken());
                if(t>0){
                    list.get(t-1).set(t-1, y, x);
                }
            }
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            list.get(i).dir = Integer.parseInt(st.nextToken())-1;
        }

        for (int i = 0; i < M; i++) {
            int[][] priority = new int[4][4];
            for (int j = 0; j < 4; j++) {
                st = new StringTokenizer(br.readLine());
                for (int l = 0; l < 4; l++) {
                    priority[j][l] = Integer.parseInt(st.nextToken())-1;
                }
            }
            list.get(i).priority = priority;
        }
        int time;
        for (time = 0; time <= 1000; time++) {
            //System.out.println(list);

            for (int y = 0; y < N; y++) {
                for (int x = 0; x < N; x++) {
                    if(graph[y][x] != null){
                        graph[y][x].time-=1;
                        if(graph[y][x].time==0){
                            graph[y][x] = null;
                        }
                    }
                }
            }

            if(list.size()<=1){
                break;
            }

            for (shark s : list){
                graph[s.y][s.x] = new smell(s.num);
            }

            for (shark s : list){
                find(s);
            }

            list.sort(new Comparator<shark>() {
                @Override
                public int compare(shark a, shark b) {
                    if(a.y == b.y){
                        return Integer.compare(a.x, b.x);
                    }
                    return Integer.compare(a.y, b.y);
                }
            });

            for (int i = 0; i < list.size()-1; i++) {
                if(list.get(i).y == list.get(i+1).y && list.get(i).x == list.get(i+1).x){
                    if(list.get(i).num<list.get(i+1).num){
                        list.remove(i+1);
                    }else{
                        list.remove(i);
                    }
                    i-=1;
                }
            }
        }

        if(time >= 1001){
            System.out.println(-1);
        }else{
            System.out.println(time);
        }
    }

    static void find(shark s){
        int[] nyx = null;

        for (int pri : s.priority[s.dir]){
            int ny = s.y + dydx[pri][0];
            int nx = s.x + dydx[pri][1];

            if(ny<0 || N<=ny || nx<0 || N<=nx){
                continue;
            }

            if (graph[ny][nx] == null){
                s.y = ny;
                s.x = nx;
                s.dir = pri;
                return;
            }else if(graph[ny][nx].num == s.num && nyx == null){
                nyx = new int[]{ny,nx,pri};
            }
        }
        s.y = nyx[0];
        s.x = nyx[1];
        s.dir = nyx[2];
    }

    static class shark{
        int num;
        int dir;
        int[][] priority;
        int y, x;

        void set(int num, int y, int x){
            this.num = num;
            this.y = y;
            this.x = x;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("num : ").append(num).append(", dir : ").append(dir).append(", y : ").append(y).append(", x : ").append(x).append("\n");
            return sb.toString();
        }
    }

    static class smell{
        int num = 0;
        int time = 0;
        smell(int num){
            this.num = num;
            time = k;
        }
    }
}
