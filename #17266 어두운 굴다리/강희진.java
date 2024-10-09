import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder output = new StringBuilder();
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());

        int[] locs = new int[M];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            locs[i] = Integer.parseInt(st.nextToken());
        }

        int start = 1, end = N;
        int ans = Integer.MAX_VALUE;
        while (start <= end) {
            int mid = start + (end - start)/2;
            // mid에 대해서 사용가능한지 탐색
            boolean success = check(mid, N, locs);
            // System.out.println(mid+": "+success);

            if (success) {
                ans = mid;
                end = mid - 1;
            } else {
                start = mid + 1;
            }

        }
        System.out.println(ans);
    }

    private static boolean check(int mid, int N, int[] locs) {
        int[][] range = new int[locs.length][2];
        for (int i = 0; i < locs.length; i++) {
            int loc = locs[i];
            range[i][0] = loc - mid;
            range[i][1] = loc + mid;
            if (i == 0) {
                if (range[i][0] > 0) return false;
            }  
            if (i == locs.length-1) {
                // System.out.println("HELLO "+range[i][1]);
                if (range[i][1] < N) return false;
            } 
            if (i != 0) {
                if (range[i-1][1] < range[i][0]) return false;
            } 
        }
        /*System.out.print(mid+": ");
        for (int[] r : range) {
            System.out.print(Arrays.toString(r));
        }
        System.out.println();
        */return true;
    }

    
}
