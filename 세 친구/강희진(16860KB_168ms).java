import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken()); 
        int M = Integer.parseInt(st.nextToken()); 

        List<Integer>[] friends = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            friends[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int f1 = Integer.parseInt(st.nextToken());
            int f2 = Integer.parseInt(st.nextToken());
            friends[f1].add(f2);
            friends[f2].add(f1);
        }

        int minSum = Integer.MAX_VALUE;

        for (int a = 1; a <= N; a++) {
            for (int b : friends[a]) {
                if (b > a) { 
                    for (int c : friends[b]) {
                        if (c > b && friends[a].contains(c)) { 
                            int degreeSum = friends[a].size() + friends[b].size() + friends[c].size() - 6;
                            minSum = Math.min(minSum, degreeSum);
                        }
                    }
                }
            }
        }

        System.out.println(minSum == Integer.MAX_VALUE ? -1 : minSum);
    }
}
