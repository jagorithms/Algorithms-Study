import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw  = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static int N, result, sum;
    static int[] po;
    static List<Integer>[] graph;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());

        po = new int[N];
        graph = new List[N];
        visited = new boolean[N];
        result = Integer.MAX_VALUE;

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            po[i] = Integer.parseInt(st.nextToken());
            sum+=po[i];
        }

        for (int i = 0; i < N; i++) {
            graph[i] = new ArrayList<>();
            st = new StringTokenizer(br.readLine());
            int m = Integer.parseInt(st.nextToken());
            for(int j = 0; j < m; j++) {
                graph[i].add(Integer.parseInt(st.nextToken())-1);
            }
        }

        visited[0] = true;
        dfs(1, po[0]);

        if(result == Integer.MAX_VALUE) {
            result = -1;
        }
        sb.append(result);

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    static void dfs(int size, int cost) {
        if(Math.abs(sum - cost*2) < result) {
            if(bfs(0)==size){
                int i=0;
                for (i = 0; i < N; i++) {
                    if(!visited[i]) {
                        break;
                    }
                }

                if(bfs(i) == N-size){
                    result = Math.abs(sum - cost*2);
                }
            }
        }

        if(size >= N-1) return;

        for (int i = 0; i < N; i++) {
            if(!visited[i]) {
                visited[i] = true;
                dfs(size+1, cost+po[i]);
                visited[i] = false;
            }
        }
    }
    static int bfs(int start){
        int count = 0;
        boolean[] qvisit =  new boolean[N];
        qvisit[start] = true;

        Deque<Integer> q = new ArrayDeque<>();
        q.add(start);

        while(!q.isEmpty()){
            count+=1;

            int cur = q.poll();
            for(int t : graph[cur]){
                if(visited[t] == visited[cur] && !qvisit[t]) {
                    qvisit[t] = true;
                    q.offerLast(t);
                }
            }
        }

        return count;
    }
}
