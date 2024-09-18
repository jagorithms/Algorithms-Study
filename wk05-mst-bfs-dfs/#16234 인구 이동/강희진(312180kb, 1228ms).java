import java.util.*;
import java.io.*;

public class Main {
    static int N, L, R, grid[][], parents[], size[];
    static final int DY[] = {-1, 1, 0, 0}, DX[] = {0, 0, -1, 1};

    static boolean solve() {
        boolean success = false;
        make();
        for (int i = 0; i < N*N; i++) {
            int y = i / N;
            int x = i % N;

            for (int dir = 0; dir < 4; dir++) {
                int ny = y + DY[dir];
                int nx = x + DX[dir];

                int nIdx = ny * N + nx;

                if (isValidCoordinate(ny, nx) && fitsAllianceCondition(y, x, ny, nx)) {
                    union(nIdx, i);
                }
            }
        }

        for (int i = 0; i < N*N; i++) {
            findSet(i);
        }

        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < N*N; i++) {
            if (!map.containsKey(parents[i])) map.put(parents[i], new ArrayList<>());
            map.get(parents[i]).add(i);
        }

        for (Map.Entry<Integer, List<Integer>> entry : map.entrySet()) {
            if (entry.getValue().size()==1) continue;
            success = true;
            int sum = 0;
            for (int p : entry.getValue()) {
                int y = p / N;
                int x = p % N;
                sum += grid[y][x];
            }
            int val = sum / entry.getValue().size();
            for (int p : entry.getValue()) {
                int y = p / N;
                int x = p % N;
                grid[y][x] = val;
            }
        }

        return success;
    }

    static boolean fitsAllianceCondition(int y, int x, int ny, int nx) {
        return Math.abs(grid[ny][nx] - grid[y][x]) >= L && Math.abs(grid[ny][nx] - grid[y][x]) <= R;
    }

    static boolean isValidCoordinate(int y, int x) {
        return y >= 0 && x >= 0 && x < N && y < N;
    }

    static void make() {
        parents = new int[N*N];
        size = new int[N*N];
        for (int i = 0; i < N*N; i++) {
            parents[i] = i;
        }
        Arrays.fill(size, -1);
    }

    static boolean union(int a, int b) {
        int aRoot = findSet(a);
        int bRoot = findSet(b);
        if (aRoot == bRoot) return false;
        if (size[bRoot] > size[aRoot]) {
            size[bRoot] += size[aRoot];
            parents[aRoot] = bRoot;
        } else {
            size[aRoot] += size[bRoot];
            parents[bRoot] = aRoot;
        }
        return true;
    }

    static int findSet(int a) {
        if (parents[a] == a) return a;
        return parents[a] = findSet(parents[a]);
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder output = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        grid = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                grid[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int time = 0;
        while (solve()) {
            time++;
        }
        output.append(time);

        bw.write(output.toString());
        bw.flush();
        br.close();
        bw.close();
    }
}
