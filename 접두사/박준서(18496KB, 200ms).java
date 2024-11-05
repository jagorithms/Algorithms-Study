package november;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;

public class BOJ1141 {
    private static int N;
    private static String[] words;
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static int answer = 0;
    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());

        words = new String[N];
        for (int i = 0; i < N; i++) {
            words[i] = br.readLine();
        }

        Arrays.sort(words, Comparator.comparingInt(String::length));
        findWords();

        System.out.println(answer);

    }

    private static void findWords() {
        for(int i = 0; i < N; i++) {
            boolean found = false;
            for(int j = i + 1; j < N; j++) {
                if(words[j].startsWith(words[i])) {
                  found = true;
                  break;
              }
            }
            if(!found) {
                answer++;
            }
        }
    }

}
