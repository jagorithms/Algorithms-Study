import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        String n = br.readLine();
        String m = br.readLine();

        int lenN = n.length();
        int lenM = m.length();

        int[][] dp = new int[lenN + 1][lenM + 1];

        for (int y = 0; y < lenN; y++) {
            for (int x = 0; x < lenM; x++) {
                if (n.charAt(y) == m.charAt(x)) {
                    dp[y + 1][x + 1] = dp[y][x] + 1;
                } else {
                    dp[y + 1][x + 1] = Math.max(dp[y][x + 1], dp[y + 1][x]);
                }
            }
        }

        System.out.println(dp[lenN][lenM]);

        if (dp[lenN][lenM] > 0) {
            StringBuilder result = new StringBuilder();
            int x = lenM;
            int y = lenN;

            while (dp[y][x] > 0) {
                if (dp[y - 1][x] == dp[y][x]) {
                    y--;
                } else if (dp[y][x - 1] == dp[y][x]) {
                    x--;
                } else {
                    result.append(m.charAt(x - 1));
                    x--;
                    y--;
                }
            }

            System.out.println(result.reverse().toString());
        }
    }
}
