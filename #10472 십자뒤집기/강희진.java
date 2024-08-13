import java.util.*;
import java.io.*;

public class Main {
    static final char NEW_LINE = '\n', WHITE = '.', BLACK = '*';
    static final String START = ".".repeat(9);
    static int[] dx = {-1, 0, 0, 0, 1};
    static int[] dy = {0, 1, 0, -1, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(st.nextToken());
        
        for (int i = 0; i < T; i++) {
            StringBuilder goal = new StringBuilder();
            for (int r = 0; r < 3; r++) {
                goal.append(br.readLine());
            }
            sb.append(solve(goal.toString())).append(NEW_LINE);
        }
        System.out.append(sb);
    }

    private static int solve(String goal) {
        ArrayDeque<String> queue = new ArrayDeque<>();
        Map<String, Integer> patterns = new HashMap<>();
        queue.add(START);
        patterns.put(START, 0);
        
        while (!queue.isEmpty()) {
            String node = queue.pollFirst();
            char[][] tmp = new char[3][3];
            for (int j = 0; j < 9; j++) {
                tmp[j/3][j%3] = node.charAt(j);
            }

            if (node.equals(goal)) return patterns.get(goal);

            for (int i = 0; i < 9; i++) {
                int row = i / 3, col = i % 3;
                char[][] search = new char[3][3];
                for (int r = 0; r < 3; r++) {
                    for (int c = 0; c < 3; c++) {
                        search[r][c] = tmp[r][c];
                    }
                }
                for (int moveIdx = 0; moveIdx < 5; moveIdx++) {
                    int ny = dy[moveIdx] + row, nx = dx[moveIdx] + col;
                    if (ny >= 0 && nx >= 0 && ny < 3 && nx < 3) {
                        switch (search[ny][nx]) {
                            case WHITE:
                                search[ny][nx] = BLACK;
                                break;
                            default:
                                search[ny][nx] = WHITE;
                        }
                    }
                }
                String tmpAns = arrayToString(search);
                if (!patterns.containsKey(tmpAns)) {
                    patterns.put(tmpAns, patterns.get(node)+1);
                    queue.addLast(tmpAns);
                }
            }
        }
        return -1;
    }

    private static String arrayToString(char[][] arr) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                sb.append(arr[i][j]);
            }
        }
        return sb.toString();
    }

}
