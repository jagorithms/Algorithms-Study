import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringBuilder sb = new StringBuilder();

    private static int[][] board;
    private static boolean flag = false;

    private static void bruteForce(int r, int c) {
        if (flag) {
            return;
        }

        if (r == 8 && c == 9) {
            flag = true;
            return;
        }

        if (c == 9) {
            bruteForce(r + 1, 0);
            return;
        }

        if (board[r][c] != 0) {
            bruteForce(r, c + 1);
            return;
        }

        for (int i = 1; i <= 9; i++) {
            board[r][c] = i;
            if (isValidRow(r) && isValidCol(c) && isValidRectangle(r, c)) {
                bruteForce(r, c + 1);
                if (flag) {
                    return;
                }
            }
            board[r][c] = 0; // 백트래킹
        }
    }

    private static boolean isValidRectangle(int r, int c) {
        int startR = r - r % 3;
        int startC = c - c % 3;

        boolean[] visited = new boolean[9];

        for (int i = startR; i < startR + 3; i++) {
            for (int j = startC; j < startC + 3; j++) {
                int num = board[i][j] - 1;
                if (num == -1) {
                    continue;
                }
                if (visited[num]) {
                    return false;
                }
                visited[num] = true;
            }
        }

        return true;
    }

    private static boolean isValidRow(int index) {
        boolean[] visited = new boolean[9];

        for (int i = 0; i < 9; i++) {
            int num = board[index][i] - 1;
            if (num == -1) {
                continue;
            }
            if (visited[num]) {
                return false;
            }
            visited[num] = true;
        }

        return true;
    }

    private static boolean isValidCol(int index) {
        boolean[] visited = new boolean[9];

        for (int i = 0; i < 9; i++) {
            int num = board[i][index] - 1;
            if (num == -1) {
                continue;
            }
            if (visited[num]) {
                return false;
            }
            visited[num] = true;
        }

        return true;
    }

    private static void init() throws IOException {
        board = new int[9][9];
        for (int i = 0; i < 9; i++) {
            String[] inputs = br.readLine().split("");
            for (int j = 0; j < 9; j++) {
                board[i][j] = Integer.parseInt(inputs[j]);
            }
        }
    }

    private static void output() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                sb.append(board[i][j]);
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }

    public static void main(String[] args) throws IOException {
        init();
        bruteForce(0, 0);
        output();
    }
}
