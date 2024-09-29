import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 상어초등학교 {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer tokens;

    private static int N;
    private static int[] students; // 자리를 배치할 학생의 순서
    private static int[][] friends; // 각 학생이 좋아하는 학생들의 번호
    private static int[][] matrix; // 교실 자리 배치 정보

    private static int[][] deltas = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    private static int solve() {
        for (int student : students) {
            arrange(student);
        }

        int sum = 0;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                sum += calcSatisfaction(i, j);
            }
        }

        return sum;
    }

    private static int calcSatisfaction(int r, int c) {
        int sum = 0;

        for (int [] delta : deltas) {
            int nr = r + delta[0];
            int nc = c + delta[1];

            if (nr < 0 || nc < 0 || nr >= N || nc >= N) {
                continue;
            }

            int student = matrix[r][c];
            for (int friend : friends[student]) {
                if (friend == matrix[nr][nc]) {
                    sum++;
                    break;
                }
            }
        }

        switch (sum) {
            case 4:
                return 1000;
            case 3:
                return 100;
            case 2:
                return 10;
            case 1:
                return 1;
            default:
                return 0;
        }
    }

    private static void arrange(int student) {
        int choosedR = -1;
        int choosedC = -1;

        int maxLike = -1;
        int maxVacant = -1;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                // 이미 누가 앉아있으면 pass
                if (matrix[i][j] != 0) {
                    continue;
                }

                int like = 0;
                int vacant = 0;

                // 좋아하는 학생의 수와 비어있는 칸 개수 새기
                for (int[] delta : deltas) {
                    int nr = i + delta[0];
                    int nc = j + delta[1];

                    if (nr < 0 || nc < 0 || nr >= N || nc >= N) {
                        continue;
                    }

                    // 좋아하는 학생의 수 세기
                    for (int friend : friends[student]) {
                        if (matrix[nr][nc] == friend) {
                            like++;
                            break;
                        }
                    }

                    // 빈 칸 세기
                    if (matrix[nr][nc] == 0) {
                        vacant++;
                    }
                }

                if (maxLike < like) {
                    maxLike = like;
                    maxVacant = vacant;
                    choosedR = i;
                    choosedC = j;
                } else if (maxLike == like) {
                    if (maxVacant < vacant) {
                        maxVacant = vacant;
                        choosedR = i;
                        choosedC = j;
                    }
                }
            }
        }

        // 자리 결정
        matrix[choosedR][choosedC] = student;
    }

    private static void init() throws IOException {
        N = Integer.parseInt(br.readLine());
        friends = new int[N * N + 1][4];
        students = new int[N * N];
        matrix = new int[N][N];

        for (int i = 0; i < N * N; i++) {
            tokens = new StringTokenizer(br.readLine());
            int me = Integer.parseInt(tokens.nextToken());
            students[i] = me;

            for (int j = 0; j < 4; j++) {
                friends[me][j] = Integer.parseInt(tokens.nextToken());
            }
        }
    }

    public static void main(String[] args) throws IOException {
        init();
        System.out.println(solve());
    }
}
