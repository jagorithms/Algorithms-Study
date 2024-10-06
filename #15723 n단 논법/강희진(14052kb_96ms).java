
import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder output = new StringBuilder();
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        Map<Character, List<Character>> map = new HashMap<>();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            char a = st.nextToken().charAt(0);
            if (!map.containsKey(a)) map.put(a, new ArrayList<>());
            st.nextToken();
            char b = st.nextToken().charAt(0);
            map.get(a).add(b);
            
        }
        int M = Integer.parseInt(br.readLine());
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            char a = st.nextToken().charAt(0);
            st.nextToken();
            char b = st.nextToken().charAt(0);
            boolean success = bfs(a, b, map, new boolean[26]);
            output.append(success ? "T" : "F").append("\n");
        }
        System.out.println(output);
    }

    private static boolean bfs(char start, char dest, Map<Character, List<Character>> map, boolean[] visited) {
        ArrayDeque<Character> queue = new ArrayDeque<>();
        queue.add(start);
        visited[start-'a'] = true;

        while (!queue.isEmpty()) {
            char node = queue.pollFirst();
            if (node == dest) return true;
            if (map.containsKey(node)) {
                for (char c : map.get(node)) {
                    visited[c - 'a'] = true;
                    queue.add(c);
                }
            }
        }
        return false;
    }
    
}
