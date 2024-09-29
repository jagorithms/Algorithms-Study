
import java.util.*;
import java.io.*;

public class Main {

    static int N, takers[], B, C;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        takers = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            takers[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        B = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        long sum = 0;
        for (int i = 0; i < N; i++) {
            sum += 1;
            if (takers[i] - B > 0) sum += (long) Math.ceil((double)(takers[i] - B)/(double)C);
        }
        sb.append(sum);

        bw.write(sb.toString());
        bw.flush();
        br.close();
        bw.close();
    }
    
}
