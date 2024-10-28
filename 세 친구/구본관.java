import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        HashMap<Integer, HashSet<Integer>> dic = new HashMap<>();
        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        for (int i = 1; i <= N; i++) {
            dic.put(i, new HashSet<>());
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            dic.get(a).add(b);
            dic.get(b).add(a);
        }

        int result = Integer.MAX_VALUE;

        for (int a = 1; a <= N; a++) {
            List<Integer> friends = new ArrayList<>(dic.get(a));

            for (int i = 0; i < friends.size() - 1; i++) {
                int b = friends.get(i);
                for (int j = i + 1; j < friends.size(); j++) {
                    int c = friends.get(j);

                    if (dic.get(b).contains(c)) {
                        int friendCountA = dic.get(a).size() - 2;
                        int friendCountB = dic.get(b).size() - 2;
                        int friendCountC = dic.get(c).size() - 2;
                        int totalFriends = friendCountA + friendCountB + friendCountC;
                        result = Math.min(result, totalFriends);
                    }
                }
            }
        }

        System.out.println(result == Integer.MAX_VALUE ? -1 : result);
    }
}

