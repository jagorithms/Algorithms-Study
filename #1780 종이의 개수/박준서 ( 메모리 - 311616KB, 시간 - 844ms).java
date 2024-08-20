import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static int N;
    private static int[][] papers;
    private static int minus_one = 0;
    private static int plus_one = 0;
    private static int zero = 0;

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine());
        papers = new int[N][N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                papers[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        splitPaper(0,0,N);
        StringBuilder sb = new StringBuilder();
        sb.append(minus_one).append("\n").append(zero).append("\n").append(plus_one).append("\n");
        System.out.println(sb);
    }

    private static int hasDifferent(int x, int y, int size) {
        int color = papers[x][y];
        for(int i = x; i < x + size; i++){
            for(int j = y; j < y + size; j++){
                if(color != papers[i][j]){
                    return -2;
                }
            }
        }
        return color;
    }

    private static void splitPaper(int start_x, int start_y,int size){
        int num = hasDifferent(start_x, start_y, size);
        if(num != -2){
            if(num == -1){
                minus_one++;
            }
            if(num == 1){
                plus_one++;
            }
            if(num == 0){
                zero++;
            }
        } else {
            int new_size = size / 3;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    splitPaper(start_x + i * new_size, start_y + j * new_size, new_size);
                }
            }
        }
    }
}
