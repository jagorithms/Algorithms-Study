import java.util.*;
import java.io.*;

public class Main {
    
    static int[][] sticks;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        Map<Integer, Integer> map = new HashMap<>();

        int N = Integer.parseInt(st.nextToken());
        sticks = new int[N][2];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            sticks[i][0] = Integer.parseInt(st.nextToken());
            sticks[i][1] = Integer.parseInt(st.nextToken());
            map.put(sticks[i][0], sticks[i][1]);
        }
        
        Arrays.sort(sticks, (a, b) -> Integer.compare(a[0], b[0]));
        
        int maxStickLength = Integer.MIN_VALUE;
        int maxStickIdx = -1;
        for (int i = 0; i < N; i++) {
            if (sticks[i][1] > maxStickLength) {
                maxStickLength = sticks[i][1];
                maxStickIdx = sticks[i][0];
            }
        }
        
        int height = sticks[0][1];
        int sum = 0;
        for (int i = sticks[0][0]; i <= maxStickIdx; i++) {
            if (map.containsKey(i) && map.get(i) > height) {
                height = map.get(i);
            }
            // System.out.println(height);
            sum += height;
        }
        height = sticks[N-1][1];
        for (int i = sticks[N-1][0]; i > maxStickIdx; i--) {
            if (map.containsKey(i) && map.get(i) > height) {
                height = map.get(i);
            }
            // System.out.println(height);
            sum += height;
        }

        sb.append(sum);
        System.out.append(sb);
    }
}
