import java.util.*;
import java.io.*;
import java.lang.annotation.Target;

public class Main {

    private static final char TARGET = 'a';
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();

        int minAns = Integer.MAX_VALUE;
        int occurrences = countOccurrences(input);

        for (int i = 0; i < input.length(); i++) {
            int bCnt = 0;
            for (int j = i; j < occurrences + i; j++) {
                if (input.charAt(j % input.length()) != TARGET) {
                    bCnt++;
                }
            }
            minAns = Math.min(minAns, bCnt);
        }

        System.out.println(minAns);
    }

    private static int countOccurrences(String word) {
        int cnt = 0;
        for (char c : word.toCharArray()) {
            if (c == TARGET) {
                cnt++;
            }
        }
        return cnt;
    }
}
