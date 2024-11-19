import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    private static String str1;
    private static String str2;
    private static int[][] dp;
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        str1 = br.readLine();
        str2 = br.readLine();

        dp = new int[str1.length() + 1][str2.length() + 1];

        for (int i = 1; i <= str1.length(); i++) {
            for (int j = 1; j <= str2.length(); j++) {
                if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }

        int len1 = str1.length();
        int len2 = str2.length();
        StringBuilder sb = new StringBuilder();
        while(len1 > 0 && len2 > 0) {
            if(str1.charAt(len1 - 1) == str2.charAt(len2 - 1)) {
                sb.append(str1.charAt(len1 - 1));
                len1--;
                len2--;
            } else if(dp[len1-1][len2] > dp[len1][len2 - 1]) {
                len1--;
            } else {
                len2--;
            }
        }
        System.out.println(dp[str1.length()][str2.length()]);
        System.out.println(sb.reverse().toString());
    }
}
