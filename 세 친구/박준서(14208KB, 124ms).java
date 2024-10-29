import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static int N,M;
    private static List<Integer>[] friends;
    private static int answer = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        friends = new ArrayList[N+1];
        for(int i=1; i<=N;i++) {
            friends[i] = new ArrayList<>();
        }

        for(int i=0; i< M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            friends[a].add(b);
            friends[b].add(a);
        }

        countFriends();

        if(answer == Integer.MAX_VALUE) {
            System.out.println(-1);
        } else {
            System.out.println(answer);
        }
    }

    private static void countFriends() {
        for(int a = 1; a <= N; a++) {
            for(int b : friends[a]) {
                if(b <= a) continue;
                for(int c : friends[b]) {
                    if(c <= b || !friends[c].contains(a)) continue;
                    int temp = friends[a].size() + friends[b].size() + friends[c].size() - 6;
                    answer = Math.min(answer, temp);
                }
            }
        }
    }

}
