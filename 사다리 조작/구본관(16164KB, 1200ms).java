import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int N, M, H;
    static int[][] graph;
    static int result = 4;

    public static void main(String[] args) throws Exception {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());

        graph = new int[H + 1][N + 1]; // 1-based index 사용

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            graph[a][b] = 1; // 가로선 추가
            graph[a][b + 1] = -1; // 반대편 가로선 표시
        }

        dfs(0, 1, 0); // 탐색 시작
        System.out.println(result == 4 ? -1 : result);
    }

    static void dfs(int count, int startY, int startX) {
        if (count >= result) return; // 기존 최적값보다 크면 종료

        if (check()) {
            result = count; // 유효하면 결과 갱신
            return;
        }

        for (int i = startY; i <= H; i++) {
            for (int j = (i == startY ? startX : 1); j < N; j++) {
                // 가로선 추가 조건 확인
                if (graph[i][j] == 0 && graph[i][j + 1] == 0) {
                    graph[i][j] = 1;
                    graph[i][j + 1] = -1;

                    dfs(count + 1, i, j + 2); // 다음 탐색
                    graph[i][j] = 0; // 백트래킹
                    graph[i][j + 1] = 0;
                }
            }
        }
    }

    static boolean check() {
        for (int start = 1; start <= N; start++) {
            int position = start;

            for (int i = 1; i <= H; i++) {
                if (graph[i][position] == 1) {
                    position++; // 오른쪽으로 이동
                } else if (position > 1 && graph[i][position - 1] == 1) {
                    position--; // 왼쪽으로 이동
                }
            }

            if (position != start) return false; // 자기 위치로 도착하지 않으면 실패
        }
        return true;
    }
}
