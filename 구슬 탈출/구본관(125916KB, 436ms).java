import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int N, M;
    static char[][] graph;
    static int[] first = new int[4];
    static int[][] dxy = { {1, 0}, {-1, 0}, {0, 1}, {0, -1} };

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        graph = new char[N][M];
        for (int i = 0; i < N; i++) {
            graph[i] = br.readLine().toCharArray();
        }

        for (int y = 1; y < N - 1; y++) {
            for (int x = 1; x < M - 1; x++) {
                if (graph[y][x] == 'R') {
                    graph[y][x] = '.';
                    first[0] = y;
                    first[1] = x;
                } else if (graph[y][x] == 'B') {
                    graph[y][x] = '.';
                    first[2] = y;
                    first[3] = x;
                }
            }
        }

        Deque<int[]> dq = new ArrayDeque<>();
        dq.add(new int[] { first[0], first[1], first[2], first[3] });

        boolean isEnd = false;
        int result = 0;

        for (int num = 1; num <= 10; num++) {
            int size = dq.size();
            for (int i = 0; i < size; i++){
                int[] current = dq.poll();
                int ry = current[0], rx = current[1], by = current[2], bx = current[3];

                for (int[] direction : dxy) {
                    int[] rotated = rotate(ry, rx, by, bx, direction[0], direction[1]);
                    int nry = rotated[0], nrx = rotated[1], nby = rotated[2], nbx = rotated[3];

                    if (graph[nby + direction[0]][nbx + direction[1]] == 'O') {
                        continue;
                    }
                    if (graph[nry + direction[0]][nrx + direction[1]] == 'O') {
                        if (nby + direction[0] == nry && nbx + direction[1] == nrx) {
                            continue;
                        }
                        isEnd = true;
                        result = 1;
                        break;
                    }

                    dq.add(new int[] { nry, nrx, nby, nbx });
                }
                if (isEnd) break;
            }

            if (isEnd) break;
        }
        System.out.println(result);
    }

    private static int[] rotate(int ry, int rx, int by, int bx, int dy, int dx) {
        if ((dy > 0 && ry > by) || (dy < 0 && ry < by) || (dx > 0 && rx > bx) || (dx < 0 && rx < bx)) {
            int[] red = go(ry, rx, dy, dx);
            ry = red[0];
            rx = red[1];
            int[] blue = go(by, bx, dy, dx);
            by = blue[0];
            bx = blue[1];
            if (graph[ry][rx] != 'O' && ry == by && rx == bx) {
                by -= dy;
                bx -= dx;
            }
        } else {
            int[] blue = go(by, bx, dy, dx);
            by = blue[0];
            bx = blue[1];
            int[] red = go(ry, rx, dy, dx);
            ry = red[0];
            rx = red[1];
            if (graph[by][bx] != 'O' && ry == by && rx == bx) {
                ry -= dy;
                rx -= dx;
            }
        }
        return new int[] { ry, rx, by, bx };
    }

    private static int[] go(int y, int x, int dy, int dx) {
        while (graph[y + dy][x + dx] == '.') {
            y += dy;
            x += dx;
        }
        return new int[] { y, x };
    }
}
