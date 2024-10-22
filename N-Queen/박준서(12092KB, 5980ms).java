import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    private static int N;
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static int[] chess;
    private static int answer = 0;
    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
        chess = new int[N];

        makeQueen(0);

        System.out.println(answer);
    }

    private static void makeQueen(int depth) {
        if(depth == N) {
            answer++;
            return;
        }
        for(int i = 0; i < N; i++) {
            chess[depth] = i;
            if(isOk(depth)){
                makeQueen(depth+1);
            }
        }
    }

    private static boolean isOk(int depth) { // 같은 행, 열, 대각선에 있으면 안된다.
        for(int i = 0; i < depth; i++) {
            if(chess[depth] == chess[i]) {
                return false;
            }

            if(depth - i == Math.abs(chess[depth] - chess[i])) {
                return false;
            }
        }
        return true;
    }
}
