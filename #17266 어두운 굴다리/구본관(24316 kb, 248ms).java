import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());

        st = new StringTokenizer(br.readLine());

        int t = Integer.parseInt(st.nextToken());
        int maxl = t*2;
        int s = t;
        for (int i = 1; i < M; i++) {
            t = Integer.parseInt(st.nextToken());
            maxl = Math.max(maxl, t-s);
            s = t;
        }
        maxl = Math.max(maxl, (N-s)*2);
        if(maxl%2==0){
            System.out.println(maxl/2);
        }else{
            System.out.println(maxl/2+1);
        }
    }
}
