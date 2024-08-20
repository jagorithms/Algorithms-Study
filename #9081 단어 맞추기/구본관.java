//14184KB, 92ms
import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine());
        for (int i = 0; i < N; i++) {
            char[] input = br.readLine().toCharArray();

            for (int j = input.length-1; j >0; j--) {
                if(input[j] > input[j-1]){
                    int k = 0;
                    for (k = input.length-1; k >= j; k--) {
                        if(input[k] > input[j-1]){
                            break;
                        }
                    }
                    char c = input[j-1];
                    input[j-1] = input[k];
                    input[k] = c;

                    char[] li = Arrays.copyOfRange(input, j, input.length);
                    Arrays.sort(li);
                    for (int l = 0; l < li.length; l++) {
                        input[l+j] = li[l];
                    }
                    break;
                }
            }
            for(char c : input) {
                sb.append(c);
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }
}
