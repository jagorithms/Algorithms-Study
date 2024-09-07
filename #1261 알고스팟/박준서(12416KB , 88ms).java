package september.seven;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ1261 {
    static class Edge implements Comparable<Edge> {
        int x, y, weight;
        public Edge(int x, int y,int weight) {
            this.x = x;
            this.y = y;
            this.weight = weight;
        }
        @Override
        public int compareTo(Edge o) {
            return this.weight - o.weight;
        }
    }
    private static int N,M;
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static int[][] spots;
    private static int[][] dist;
    private static boolean[][] visited;
    private static int[] dx = {-1,1,0,0};
    private static int[] dy = {0,0,-1,1};
    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        spots = new int[N][M];
        dist = new int[N][M];
        visited = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            String input = br.readLine();
            for (int j = 0; j < M; j++) {
               spots[i][j] = input.charAt(j) - '0';
            }
        }
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                dist[i][j] = Integer.MAX_VALUE;
            }
        }

        dijkstra();

        System.out.println(dist[N - 1][M - 1]);
    }

    private static void dijkstra(){
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        dist[0][0] = 0;
        pq.add(new Edge(0, 0, spots[0][0]));
        while (!pq.isEmpty()) {
            Edge edge = pq.poll();
            visited[edge.x][edge.y] = true;
            for(int i = 0; i < 4; i++){
                int nx = edge.x + dx[i];
                int ny = edge.y + dy[i];
                if(nx >= 0 && nx < N && ny >= 0 && ny < M && !visited[nx][ny]){
                    if(dist[nx][ny] > dist[edge.x][edge.y] + spots[nx][ny]){
                        dist[nx][ny] = dist[edge.x][edge.y]+ spots[nx][ny];
                        pq.add(new Edge(nx, ny, dist[nx][ny]));
                    }
                }
            }
        }
    }
}
