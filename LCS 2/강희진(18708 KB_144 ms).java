import java.util.*;
import java.io.*;

public class Main {

    static final char OPENING = '(', CLOSING = ')';
    static Set<String> uniqueExpressions = new HashSet<>();
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        String first = br.readLine();
        String second = br.readLine();


        int[][] dp = new int[first.length() + 1][second.length() + 1];

        for (int i = 0; i < first.length(); i++) {
            char f = first.charAt(i);
            for (int j = 0; j < second.length(); j++) {
                char s = second.charAt(j);
                if (f == s) {
                    dp[i + 1][j + 1] = dp[i][j] + 1;
                } else {
                    dp[i + 1][j + 1] = Math.max(dp[i][j + 1], dp[i + 1][j]);
                }
            }
        }

        System.out.println(dp[first.length()][second.length()]);

        if (dp[first.length()][second.length()] == 0) {
            return;
        }

        int i = first.length(), j = second.length();

        StringBuilder sb = new StringBuilder();
        while (dp[i][j] > 0) {
            if (dp[i - 1][j] == dp[i][j]) {
                i--;
            } else if (dp[i][j - 1] == dp[i][j]) {
                j--;
            } else {
                sb.append(second.charAt(j - 1));
                i--;
                j--;
            }
        }
        System.out.println(sb.reverse().toString());
        
    }

    
}
