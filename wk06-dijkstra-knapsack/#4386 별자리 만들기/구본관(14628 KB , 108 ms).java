import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw  = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;


    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine());

        double[][] stars = new double[N][2];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            stars[i][0] = Double.parseDouble(st.nextToken());
            stars[i][1] = Double.parseDouble(st.nextToken());
        }
        PriorityQueue<double[]> pq = new PriorityQueue<>((o1, o2) -> Double.compare(o1[2], o2[2]));
        for (int i = 0; i < N-1; i++) {
            for(int j = i+1; j < N; j++){
                double distance = Math.sqrt((stars[i][0]-stars[j][0])*(stars[i][0]-stars[j][0]) + (stars[i][1]-stars[j][1])*(stars[i][1]-stars[j][1]));
                pq.add(new double[]{i, j, distance});
            }
        }
        int[] parent = new int[N];
        for(int i = 0; i < N; i++){
            parent[i] = i;
        }

        double result = 0;
        while(!pq.isEmpty()){
            double[] cur = pq.poll();
            if(find((int) cur[0], parent) != find((int) cur[1], parent)){
                union((int) cur[0], (int) cur[1], parent);
                result+=cur[2];
            }
        }

        sb.append(result);
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
    static int find(int x, int[] parent){
        if(parent[x] == x){
            return x;
        }
        return find(parent[x], parent);
    }
    static void union(int x, int y, int[] parent){
        x = find(x, parent);
        y = find(y, parent);
        if(x > y){
            parent[x] = y;
        }else{
            parent[y] = x;
        }
    }
}
