import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int N = 4;
    static int[][] graph = new int[N][N];
    static int[][] fish = new int[N*N+1][3];
    static int[][] dydx = {{-1,0},{-1,-1},{0,-1},{1,-1},{1,0},{1,1},{0,1},{-1,1},{-1,0},{-1,-1},{0,-1},{1,-1},{1,0},{1,1},{0,1}};
    static int result = 0;

    public static void main(String[] args) throws Exception {
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                fish[a] = new int[]{b-1, i, j};
                graph[i][j] = a;
            }
        }

        dfs(0,0, graph[0][0]);

//        for (int i = 1; i < N*N+1; i++) {
//            System.out.println(Arrays.toString(fish[i]));
//        }
//        System.out.println();
//        for (int y = 0; y < N; y++) {
//            System.out.println(Arrays.toString(graph[y]));
//        }
//        System.out.println();
//
        System.out.println(result);
    }

    static void dfs(int Sy, int Sx, int eat) {
        int[][] backupgraph = new int[4][4];
        for (int y = 0; y < N; y++) {
            for (int x = 0; x < N; x++) {
                backupgraph[y][x] = graph[y][x];
            }
        }

        int[][] backupfish = new int[N*N+1][3];
        for (int y = 1; y <= N*N; y++) {
            if(fish[y] != null) {
                for (int x = 0; x < 3; x++) {
                    backupfish[y][x] = fish[y][x];
                }
            }else{
                backupfish[y] = null;
            }
        }


        int St = fish[graph[Sy][Sx]][0];
        eat(Sy,Sx);
        result = Math.max(result, eat);

        for (int i = 1; i <= N*N; i++) {
            if(fish[i] != null){
                int fy = fish[i][1];
                int fx = fish[i][2];
                int ft = fish[i][0];
                for (int t = ft; t < ft+8; t++) {
                    int ny = fy + dydx[t][0];
                    int nx = fx + dydx[t][1];

                    if(ny<0 || ny>=N || nx<0 || nx>=N) continue;

                    if(ny == Sy && nx == Sx) continue;

                    if(graph[ny][nx] == 0){
                        graph[ny][nx] = i;
                        fish[i] = new int[]{t%8,ny,nx};
                        graph[fy][fx] = 0;
                    }else{
                        int temp = graph[ny][nx];

                        graph[ny][nx] = i;
                        fish[i] = new int[]{t%8,ny,nx};

                        graph[fy][fx] = temp;
                        fish[temp] = new int[]{fish[temp][0],fy,fx};
                    }
                    break;
                }
            }
        }
        Sy+= dydx[St][0];
        Sx+= dydx[St][1];

        while(0<=Sy && Sy<N && 0<=Sx && Sx<N){
            if(graph[Sy][Sx] != 0){
                dfs(Sy, Sx, eat + graph[Sy][Sx]);
            }
            Sy+= dydx[St][0];
            Sx+= dydx[St][1];
        }
        graph = backupgraph;
        fish = backupfish;
    }

    static void eat(int y, int x){
        int n = graph[y][x];
        fish[n] = null;
        graph[y][x] = 0;
    }
}
