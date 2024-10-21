import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int[][] graph = new int[9][9];
    static int[][] graph3 = new int[9][9];

    static int[] ybit = new int[9];  // 각 행에 대한 비트 마스크
    static int[] xbit = new int[9];  // 각 열에 대한 비트 마스크
    static int[] yx3bit = new int[9];  // 각 3x3 박스에 대한 비트 마스크

    public static void main(String[] args) throws Exception {

        // 3x3 박스 인덱스 설정
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                graph3[i][j] = 3 * (i / 3) + j / 3;
            }
        }

        // 입력을 받아 스도쿠 그래프 초기화 및 비트 마스크 설정
        for (int y = 0; y < 9; y++) {
            String s = br.readLine();
            for (int x = 0; x < 9; x++) {
                graph[y][x] = s.charAt(x) - '0';
                if (graph[y][x] > 0) {
                    ybit[y] |= 1 << graph[y][x];
                    xbit[x] |= 1 << graph[y][x];
                    yx3bit[graph3[y][x]] |= 1 << graph[y][x];
                }
            }
        }

        // DFS로 스도쿠 풀이 시작
        dfs(0, 0);
    }

    static void dfs(int y, int x) {
        // 다음 행으로 넘어가기
        if (x == 9) {
            dfs(y + 1, 0);
            return;
        }
        // 마지막 행까지 완료되면 성공
        if (y == 9) {
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    System.out.print(graph[i][j]);
                }
                System.out.println();
            }
            System.exit(0);  // 스도쿠가 풀렸으므로 즉시 프로그램 종료
        }

        // 이미 값이 있는 칸은 다음 칸으로 이동
        if (graph[y][x] != 0) {
            dfs(y, x + 1);
            return;
        }

        // 1부터 9까지의 숫자를 시도
        for (int i = 1; i <= 9; i++) {
            // 행, 열, 3x3 박스에서 해당 숫자가 이미 사용되었는지 확인
            if ((ybit[y] & (1 << i)) > 0 || (xbit[x] & (1 << i)) > 0 || (yx3bit[graph3[y][x]] & (1 << i)) > 0) {
                continue;
            }

            // 숫자 i를 사용
            graph[y][x] = i;
            ybit[y] |= 1 << i;
            xbit[x] |= 1 << i;
            yx3bit[graph3[y][x]] |= 1 << i;

            // 다음 칸으로 이동
            dfs(y, x + 1);

            // 백트래킹: 숫자 i를 복원
            ybit[y] ^= 1 << i;
            xbit[x] ^= 1 << i;
            yx3bit[graph3[y][x]] ^= 1 << i;
            graph[y][x] = 0;
        }
    }
}
