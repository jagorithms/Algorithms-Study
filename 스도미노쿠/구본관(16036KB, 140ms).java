import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int[][] graph;
    static int[][] graph3 = new int[9][9];

    static int[] ybit;  // 각 행에 대한 비트 마스크
    static int[] xbit; // 각 열에 대한 비트 마스크
    static int[] yx3bit;  // 각 3x3 박스에 대한 비트 마스크

    static int[] puzzlebit;

    static int[][] dyx = {{1,0},{0,1}};
    static boolean isEnd;

    public static void main(String[] args) throws Exception {
        // 3x3 박스 인덱스 설정
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                graph3[i][j] = 3 * (i / 3) + j / 3;
            }
        }

        int num = 0;
        while(true){
            int T = Integer.parseInt(br.readLine());
            if(T == 0){
                break;
            }
            num+=1;
            System.out.println("Puzzle " + num);

            graph = new int[9][9];
            ybit = new int[9];
            xbit = new int[9];
            yx3bit = new int[9];
            puzzlebit = new int[10];

            for (int i = 0; i < T; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                String yx1 = st.nextToken();
                int b = Integer.parseInt(st.nextToken());
                String yx2 = st.nextToken();

                int y1 = yx1.charAt(0) - 'A';
                int x1 = yx1.charAt(1) - '0'-1;

                int y2 = yx2.charAt(0) - 'A';
                int x2 = yx2.charAt(1) - '0'-1;

                puzzlebit[a] |= 1<<b;
                puzzlebit[b] |= 1<<a;

                insert(y1,x1,a);
                insert(y2,x2,b);
            }

            st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= 9; i++) {
                String s = st.nextToken();
                int y = s.charAt(0) - 'A';
                int x = s.charAt(1) - '0'-1;
                insert(y,x,i);
            }

            // DFS로 스도쿠 풀이 시작
            isEnd = false;
            dfs(0, 0);
        }
    }

    static void dfs(int y, int x) {
        //System.out.println(y + ", " + x);
        if(isEnd){
            return;
        }
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
            isEnd = true;
            return;
        }

        // 이미 값이 있는 칸은 다음 칸으로 이동
        if (graph[y][x] != 0) {
            dfs(y, x + 1);
            return;
        }

        // 1부터 9까지의 숫자를 시도
        for (int i = 1; i <= 9; i++) {
            if(!check(y,x,i)){
                continue;
            }

            insert(y,x,i);

            for (int j = 1; j <= 9; j++) {
                if(i==j || (puzzlebit[i] & 1<<j) > 0){
                    continue;
                }

                for (int[] dydx : dyx){
                    int ny = y + dydx[0];
                    int nx = x + dydx[1];

                    if(check(ny,nx,j)){
                        puzzlebit[i] |= 1<<j;
                        puzzlebit[j] |= 1<<i;

                        insert(ny,nx,j);
                        dfs(y, x+1);
                        remove(ny, nx,j);

                        puzzlebit[i] ^= 1<<j;
                        puzzlebit[j] ^= 1<<i;
                    }
                }
            }
            remove(y, x, i);
        }
    }

    static boolean check(int y, int x, int value) {
        if(y<0 || 9<=y || x<0 || 9<=x){
            return false;
        }
        if(graph[y][x] != 0){
            return false;
        }
        // 행, 열, 3x3 박스에서 해당 숫자가 이미 사용되었는지 확인
        if ((ybit[y] & (1 << value)) > 0 || (xbit[x] & (1 << value)) > 0 || (yx3bit[graph3[y][x]] & (1 << value)) > 0) {
            return false;
        }

        return true;
    }

    static void insert(int y, int x, int value){
        graph[y][x] = value;
        ybit[y] |= 1 << graph[y][x];
        xbit[x] |= 1 << graph[y][x];
        yx3bit[graph3[y][x]] |= 1 << graph[y][x];
    }

    static void remove(int y, int x, int value){
        graph[y][x] = 0;
        ybit[y] ^= 1 << value;
        xbit[x] ^= 1 << value;
        yx3bit[graph3[y][x]] ^= 1 << value;
    }
}
