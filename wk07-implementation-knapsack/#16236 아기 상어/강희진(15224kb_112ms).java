
import java.util.*;
import java.io.*;
public class Main {
    static int N, grid[][], time;
    static Shark babyShark;
    static final int DY[] = {-1, 0, 1, 0}, DX[] = {0, -1, 0, 1};

    static void simulate() {
        Fish nextFish = bfs();
        if (nextFish.y==-1) return;

        time += nextFish.distance;
        grid[nextFish.y][nextFish.x] = 0;

        babyShark.fishEaten++;
        babyShark.y = nextFish.y;
        babyShark.x = nextFish.x;
        if (babyShark.fishEaten == babyShark.size) {
            babyShark.size++;
            babyShark.fishEaten=0;
        }
        
        simulate();
    }

    static boolean isValidCoordinate(int y, int x) {
        return y >= 0 && x >= 0 && x < N && y < N;
    }

    static Fish bfs() {
        ArrayDeque<int[]> queue = new ArrayDeque<>();
        int[][] visited = new int[N][N];

        queue.add(new int[] {babyShark.y, babyShark.x});
        visited[babyShark.y][babyShark.x] = 1;

        int minDist = Integer.MAX_VALUE;
        List<int[]> ans = new ArrayList<>();

        while (!queue.isEmpty()) {
            int[] node = queue.pollFirst();
            int y = node[0];
            int x = node[1];

            if (visited[y][x] > minDist) break;

            if (isConsumable(y, x)) {
                minDist = Math.min(minDist, visited[y][x]);
                if (visited[y][x]==minDist) ans.add(new int[]{y, x});
            }

            for (int d = 0; d < 4; d++) {
                int ny = y + DY[d];
                int nx = x + DX[d];
                if (isValidCoordinate(ny, nx) && visited[ny][nx] == 0 && grid[ny][nx] <= babyShark.size) {
                    visited[ny][nx] = visited[y][x] + 1;
                    queue.add(new int[] {ny, nx});
                }
            }
        }

        if (ans.isEmpty()) return new Fish(-1, -1, -1);
        else {
            Collections.sort(ans, new Comparator<int[]>() {
                @Override
                public int compare(int[] a, int[] b) {
                    if (a[0] != b[0]) return Integer.compare(a[0], b[0]);
                    return Integer.compare(a[1], b[1]);
                }
            });
            return new Fish(ans.get(0)[0], ans.get(0)[1], minDist-1);
        }
    }

    static class Fish {
        int y, x, distance;
        Fish(int y, int x, int distance) {
            this.y = y;
            this.x = x;
            this.distance = distance;
        }
    }

    static boolean isConsumable(int y, int x) {
        return grid[y][x] > 0 && grid[y][x] < babyShark.size;
    }
    static class Shark {
        int size, fishEaten, y, x;
        Shark(int y, int x) {
            this.y = y;
            this.x = x;
            this.size = 2;
            this.fishEaten = 0;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        grid = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                grid[i][j] = Integer.parseInt(st.nextToken());
                if (grid[i][j] == 9) {
                    babyShark = new Shark(i, j);
                    grid[i][j] = 0;
                }
            }
        }

        simulate();
        System.out.println(time);
    }
}
