import java.util.*;
import java.io.*;

public class Main {
    static int N;
    static List<Integer>[] grid;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        grid = new ArrayList[N+1];

        for (int i = 1; i <= N; i++) {
            grid[i] = new ArrayList<>();
        }
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int dest = Integer.parseInt(st.nextToken());
            grid[start].add(dest);
            grid[dest].add(start);
        }

        boolean[] isCycle = new boolean[N + 1];
        for(int i = 1; i <= N; i++) {
            if(checkCycle(i, i, i, isCycle)) break;
            isCycle = new boolean[N + 1];
        }
 
        int[] result = new int[N + 1];
        for(int i = 1; i <= N; i++) {
            if(!isCycle[i]) result[i] = bfs(i, isCycle);
        }
        
        for (int i = 1; i <= N; i++) {
            sb.append(result[i]).append(" ");
        }
        System.out.println(sb);
    }

    private static boolean checkCycle(int prev, int current, int start, boolean[] isCycle) {
        isCycle[current] = true;
 
        for(int i = 0; i < grid[current].size(); i++) {
            int next = grid[current].get(i);
 
            if(!isCycle[next]) {
                if(checkCycle(current, next, start, isCycle)) return true;
            } else if(next != prev && next == start) return true;
        }
        isCycle[current] = false;
        
        return false;
    }

    private static int bfs(int start, boolean[] isCycle) {
        ArrayDeque<Node> queue = new ArrayDeque<>();
        boolean[] visited = new boolean[N + 1];
        queue.add(new Node(start, 0));
        visited[start] = true;
 
        while(!queue.isEmpty()) {
            Node current = queue.poll();
            if(isCycle[current.idx]) return current.count;
 
            for(int i = 0; i < grid[current.idx].size(); i++) {
                int next = grid[current.idx].get(i);
                if(!visited[next]) {
                    visited[next] = true;
                    queue.add(new Node(next, current.count + 1));
                }
            }
        }
        return -1;
    }

    static class Node {
        int idx, count;
        Node(int idx, int count) {
            this.idx = idx;
            this.count = count;
        }
    }
}
