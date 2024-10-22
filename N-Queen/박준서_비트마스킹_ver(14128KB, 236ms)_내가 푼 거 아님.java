import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    static BufferedReader br;
    static int N, cnt, ans;
    public static void main(String[] args) throws Exception {
        br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        cnt = 0;
        ans = 0;
        dfs(0, 0, 0, 0);
        System.out.println(ans);
    }
    private static void dfs(int row, int c, int d1, int d2) {
        if(row == N) {
            cnt++;
            return;
        }

        if(row > 0) { //첫번째 row가 아닐 경우 
            int able = ((1 << N) -1) & ~(c|d1|d2); //c|d1|d2하면 가능한 경우가 나옴.
            
            while(able > 0) {
                int p = able & -able; //가능한 경우의 수 중에 가장 작은 수
                able -= p;

                nextDfs(row, c, d1, d2, p);
            } 
        } else {
            for(int i = 0; i < N/2; i++) nextDfs(row, c, d1, d2, 1<<i);
            ans += cnt;

            if(N % 2 == 1) nextDfs(row, c, d1, d2, 1 << (N / 2));
            ans += cnt;
        }
    }

    static void nextDfs(int row, int c, int d1, int d2, int p) {
        dfs(row+1, c|p, (d1 | p) >> 1, (d2 | p )<< 1);
    }
}
