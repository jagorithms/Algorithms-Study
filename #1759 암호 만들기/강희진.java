import java.util.*;
import java.io.*;

public class Main {

    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int L = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        char[] characters = new char[C];
        int idx = 0;
        while (st.hasMoreTokens()) {
            characters[idx++] = st.nextToken().charAt(0);
        }
        Arrays.sort(characters);
        makeCombinations(characters, new char[L], 0, 0,L, new boolean[C]);
        bw.write(sb.toString());
        bw.flush();
        br.close();
    }

    private static void makeCombinations(char[] characters, char[] result, int depth, int start, int L, boolean[] visited) {
        if (depth==L) {
            if (satisfiesCondition(result)) sb.append(arrToString(result)).append("\n");
            return;
        }
        for (int i = start; i < characters.length; i++) {
            if (!visited[i]) {
                visited[i] = true;
                result[depth] = characters[i];
                makeCombinations(characters, result, depth + 1, i+1, L, visited);
                visited[i] = false;
            }
        }
    }

    private static String arrToString(char[] arr) {
        StringBuilder sb =new StringBuilder();
        for (char c : arr) {
            sb.append(c);
        }
        return sb.toString();
    }

    private static boolean satisfiesCondition(char[] result) {
        int vowels = 0, consonants = 0;
        for (char c : result) {
            switch (c) {
                case 'a':
                case 'e':
                case 'i':
                case 'o': 
                case 'u':
                    vowels++;
                    break;
                default:
                    consonants++;
            }
        }
        return vowels >= 1 && consonants >= 2;
    }

}
