import java.util.*;
import java.io.*;

public class Main {
    static int N, M, K;
    static ArrayList<FireBall>[][] grid;
    static final int[] DY = { -1, -1, 0, 1, 1, 1, 0, -1 };
    static final int[] DX = { 0, 1, 1, 1, 0, -1, -1, -1 };
    static final int[] EVEN_DIR_IDX = { 0, 2, 4, 6 }, ODD_DIR_IDX = { 1, 3, 5, 7 };

    static class FireBall {
        int mass, direction, speed;

        FireBall(int mass, int speed, int direction) {
            this.mass = mass;
            this.speed = speed;
            this.direction = direction;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        grid = new ArrayList[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                grid[i][j] = new ArrayList<>();
            }
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken()) - 1;
            int m = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());

            grid[r][c].add(new FireBall(m, s, d));
        }

        System.out.println(solution());
    }

    static int solution() {
        for (int i = 0; i < K; i++) {
            play();
            cleanup();
        }
        return sum();
    }

    static void play() {
        ArrayList<FireBall>[][] newGrid = new ArrayList[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                newGrid[i][j] = new ArrayList<>();
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                for (FireBall fb : grid[i][j]) {
                    int ny = (i + N + DY[fb.direction] * (fb.speed % N)) % N;
                    int nx = (j + N + DX[fb.direction] * (fb.speed % N)) % N;

                    newGrid[ny][nx].add(new FireBall(fb.mass, fb.speed, fb.direction));
                }
            }
        }

        grid = newGrid;
    }

    static void cleanup() {
        ArrayList<FireBall>[][] newGrid = new ArrayList[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                newGrid[i][j] = new ArrayList<>();
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (grid[i][j].size() == 1) {
                    newGrid[i][j].add(grid[i][j].get(0));
                } else if (grid[i][j].size() >= 2) {
                    int totalMass = 0;
                    int totalSpeed = 0;
                    boolean sameParity = sameDirections(grid[i][j]);
                    for (FireBall fb : grid[i][j]) {
                        totalMass += fb.mass;
                        totalSpeed += fb.speed;
                    }
                    int newMass = totalMass / 5;
                    int newSpeed = totalSpeed / grid[i][j].size();

                    if (newMass > 0) { 
                        int[] directions = sameParity ? EVEN_DIR_IDX : ODD_DIR_IDX;
                        for (int d : directions) {
                            newGrid[i][j].add(new FireBall(newMass, newSpeed, d));
                        }
                    }
                }
            }
        }

        grid = newGrid;
    }

    static boolean sameDirections(List<FireBall> res) {
        int firstDirectionParity = res.get(0).direction % 2;
        for (int i = 1; i < res.size(); i++) {
            if (res.get(i).direction % 2 != firstDirectionParity) {
                return false;
            }
        }
        return true;
    }

    static int sum() {
        int totalMass = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                for (FireBall fb : grid[i][j]) {
                    totalMass += fb.mass;
                }
            }
        }
        return totalMass;
    }
}
