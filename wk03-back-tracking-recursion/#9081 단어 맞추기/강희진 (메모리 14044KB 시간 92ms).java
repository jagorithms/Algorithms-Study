
import java.io.*;
import java.util.*;

public class Main {   
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(st.nextToken());

        for (int t = 0; t < T; t++) {
            char[] word = br.readLine().toCharArray();
            char[] ans = nextPermutation(word);
            sb.append(String.valueOf(ans)).append("\n");
        }
    
        br.close();
        bw.write(sb.toString());
        bw.flush();
    }

    private static char[] nextPermutation(char[] word) {
        int pivotIdx = word.length-2;
        while (pivotIdx >= 0 && word[pivotIdx] >= word[pivotIdx+1] ) {
            pivotIdx--;
        }
        if (pivotIdx < 0) return word;
        
        int successorIdx = word.length-1;
        while (word[successorIdx] <= word[pivotIdx]) {
            successorIdx--;
        }
        swap(word, pivotIdx, successorIdx);
        
        reverse(word, pivotIdx+1);
        return word;
    }

    private static void swap(char[] word, int i, int j) {
        char tmp = word[i];
        word[i] = word[j];
        word[j] = tmp;
    } 

    private static void reverse(char[] word, int start) {
        int end = word.length-1;
        while (start < end) {
            swap(word, start, end);
            start++;
            end--;
        }
    }

}
