import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int findSet(int v){
        if(parents[v] == v) return v;
        return parents[v] = findSet(parents[v]);
    }

    static boolean union(int a, int b) {
        int rootA = findSet(a);
        int rootB = findSet(b);
        if(rootA == rootB) return false;
        if(rootA < rootB) parents[rootB] = rootA;
        else parents[rootA] = rootB;
        return true;
    }

    private static int N,M;
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static int[] parents;
    private static int answer;
    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        parents = new int[N];

        for (int i = 0; i < N; i++) {
            parents[i] = i;
        }

        answer = 0;
        for(int i = 1; i <= M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            if(!union(a,b)) {
                answer = i;
                break;
            }
        }
        System.out.println(answer);
    }
}
