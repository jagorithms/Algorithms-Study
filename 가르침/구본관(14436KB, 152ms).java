import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int N, M;
    static int antatica = (1<<('a'-'a') | 1<<('n'-'a') | 1<<('t'-'a') | 1<<('i'-'a') | 1<<('c'-'a'));
    static int[] bitarr;
    static int result = 0;

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        if(M<5){
            System.out.println(0);
            return;
        }
        bitarr = new int[N];

        for (int i = 0; i < N; i++) {
            int bit = antatica;
            String s = br.readLine();

            for (int j = 4; j < s.length()-4; j++) {
                char c = s.charAt(j);
                bit |= (1 << c - 'a');
            }
            bitarr[i] = bit;
        }
        dfs(0,antatica, 0);
        System.out.println(result);
    }
    static void dfs(int idx, int bit, int count) {
        // 현재 가르친 문자로 읽을 수 있는 단어 수 계산
        if (Integer.bitCount(bit) == M) {
            int readableCount = 0;
            for (int wordBit : bitarr) {
                if ((wordBit & bit) == wordBit) {
                    readableCount++;
                }
            }
            result = Math.max(result, readableCount);
            return;
        }
        
        if (idx >= 26) return;
        
        dfs(idx + 1, bit, count);
        
        if ((bit & (1 << idx)) == 0) {
            dfs(idx + 1, bit | (1 << idx), count + 1);
        }
    }
}
