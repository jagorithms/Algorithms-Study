import java.io.*;
import java.util.*;

public class Main {   

    static int n, w, L, time;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        n = Integer.parseInt(st.nextToken());
        w = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());

        int idx = 0;
        int[] moves = new int[n];

        TreeMap<Integer, Integer> trucks = new TreeMap<>();
        while (st.hasMoreTokens()) {
            int truck = Integer.parseInt(st.nextToken());
            trucks.put(idx++, truck);
        }

        solve(trucks, moves);
        time++;
        br.close();
        sb.append(time);
        bw.write(sb.toString());
        bw.flush();
    }

    private static void solve(TreeMap<Integer, Integer> trucks,int[] moves) {
        TreeMap<Integer, Integer> moving = new TreeMap<>();
        int sumWeight = 0;
        while (true) {
            for (int i = 0; i < (!trucks.isEmpty() ? trucks.firstKey() : n); i++) {
                if (moves[i] >= w) {
                    sumWeight -= moving.get(i);
                    if (moves[n-1] >= w) return;
                    moves[i] = Integer.MIN_VALUE;
                    moving.remove(i);       
                }
                
            }
            if (!trucks.isEmpty() && moving.size() < w && sumWeight + trucks.firstEntry().getValue() <= L)  {
                moving.put(trucks.firstKey(), trucks.firstEntry().getValue());
                sumWeight += trucks.pollFirstEntry().getValue();
            }
            for (int i = 0; i < (!trucks.isEmpty() ? trucks.firstKey() : n); i++) {
                moves[i]++;
            }

            time++;
        }
    }



}
