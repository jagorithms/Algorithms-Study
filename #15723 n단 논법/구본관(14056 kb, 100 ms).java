import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static boolean[][] graph = new boolean[26][26];
    static boolean[] visited;

    public static void main(String[] args) throws Exception {
        int N = Integer.parseInt(br.readLine());
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            String a = st.nextToken();
            st.nextToken();
            String b = st.nextToken();

            graph[a.charAt(0)-'a'][b.charAt(0)-'a'] = true;
        }

        for (int i = 0; i < 26; i++) {
            visited = new boolean[26];
            dfs(i,i);
        }

//        for (int i = 0; i < 26; i++) {
//            for (int j = 0; j < 26; j++) {
//                sb.append(graph[i][j] ? '*' : '.');
//            }
//            sb.append("\n");
//        }

        N = Integer.parseInt(br.readLine());
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            String a = st.nextToken();
            st.nextToken();
            String b = st.nextToken();

            if(graph[a.charAt(0)-'a'][b.charAt(0)-'a']){
                sb.append("T").append("\n");
            }else{
                sb.append("F").append("\n");
            }
        }
        System.out.println(sb.toString());
    }

    static void dfs(int start, int index){
        for (int i = 0; i < 26; i++) {
            if(graph[index][i] && !visited[i]){
                graph[start][i] = true;
                visited[i] = true;
                dfs(start,i);
            }
        }
    }
}
