import java.util.*;
import java.io.*;

public class Main {

    static final int ALPHABETS = 26;
    static int ans = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        boolean[][] words = new boolean[N][ALPHABETS];

        for (int i = 0; i < N; i++) {
            String word = br.readLine();
            for (int j = 0; j < word.length(); j++) {
                words[i][word.charAt(j) - 'a'] = true;
            }
        }
        generate(0, 0, K, new boolean[ALPHABETS], words);
        System.out.println(ans);
    }

    private static void generate(int next, int depth, int K, boolean[] learned, boolean[][] words) {
        if (depth == K) {
            int cnt = 0;
            for (int i = 0; i < words.length; i++) {
                boolean flag = true;
                for (int j = 0; j < ALPHABETS; j++) {
                    if (words[i][j] && !learned[j]) {
                        flag = false;
                        break;
                    }
                }
                if (flag) {
                    cnt++;
                }
            }
            ans = Math.max(ans, cnt);
            return;
        }

        for (int i = next; i < ALPHABETS; i++) {
            learned[i] = true;
            generate(i + 1, depth + 1, K, learned, words);
            learned[i] = false;
        }

    }
}
