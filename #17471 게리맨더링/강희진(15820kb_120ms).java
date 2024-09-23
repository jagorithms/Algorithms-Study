
import java.util.*;
import java.io.*;

public class Main {
    static int N, population[], minDiff = Integer.MAX_VALUE;
    static ArrayList<Integer>[] map;

    static void genComb(int depth, int start, boolean[] visited) {
        if (depth > 0) {
            if (isConnected(visited, true) && isConnected(visited, false)) {
                int aCount = sum(visited, true);
                int bCount = sum(visited, false);
                minDiff = Math.min(minDiff, Math.abs(aCount - bCount));
            }
        }
        if (depth == N)
            return;
        for (int i = start; i <= N; i++) {
            visited[i] = true;
            genComb(depth + 1, i + 1, visited);
            visited[i] = false;
        }
    }

    static int sum(boolean[] visited, boolean ans) {
        int n = 0;
        for (int i = 1; i <= N; i++) {
            if (visited[i] == ans) {
                n += population[i];
            }
        }
        return n;
    }
    
    static boolean isConnected(boolean[] res, boolean answer) {
        boolean[] visited = new boolean[N + 1];
        Queue<Integer> queue = new ArrayDeque<>();
        
        int start = -1;
        for (int i = 1; i <= N; i++) {
            if (res[i] == answer) {
                start = i;
                break;
            }
        }

        if (start == -1)
            return false;
        
        queue.add(start);
        visited[start] = true;
        
        while (!queue.isEmpty()) {
            int node = queue.poll();
            for (int next : map[node]) {
                if (res[next] == answer && !visited[next]) {
                    visited[next] = true;
                    queue.add(next);
                }
            }
        }
        for (int i = 1; i <= N; i++) {
            if (res[i] == answer && !visited[i]) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] ars) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());

        population = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            population[i] = Integer.parseInt(st.nextToken());
        }

        map = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            int count = Integer.parseInt(st.nextToken());
            map[i] = new ArrayList<>();
            for (int j = 1; j <= count; j++) {
                map[i].add(Integer.parseInt(st.nextToken()));
            }
        }

        genComb(0, 1, new boolean[N + 1]);
        System.out.println(minDiff == Integer.MAX_VALUE ? -1 : minDiff);
    }
}
