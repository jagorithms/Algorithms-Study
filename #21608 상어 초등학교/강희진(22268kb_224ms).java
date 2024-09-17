
import java.util.*;
import java.io.*;

public class Main {
    static int N, grid[][];
    static Map<Integer, int[]> map = new HashMap<>();
    static final int DY[] = {-1, 1, 0, 0}, DX[] = {0, 0, -1, 1};

    static long survey() {
        long ans = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                int student = grid[i][j];
                int[] favs = map.get(student);
                int favCount = 0;
                for (int d = 0; d < 4; d++) {
                    int ny = i + DY[d];
                    int nx = j + DX[d];
                    if (isValidCoordinate(ny, nx)) {
                        for (int idx = 0; idx < 4; idx++) {
                            if (favs[idx] == grid[ny][nx]) {
                                favCount++;
                                break;
                            }
                        }
                    }
                }
                ans += satisfaction(favCount);
            }
        }
        return ans;
    }

    static int satisfaction(int count) {
        switch (count) {
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

    static boolean isValidCoordinate(int y, int x) {
        return x >= 0 && y >= 0 && x < N && y < N;
    }

    static void locate(int student, int[] favorites) {
        List<Seat> seats = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (grid[i][j] != 0) continue;

                int favs = 0, empty = 0;
                for (int d = 0; d < 4; d++) {
                    int ny = i + DY[d];
                    int nx = j + DX[d];
                    if (isValidCoordinate(ny, nx)) {
                        if (grid[ny][nx]==0) empty++;
                        else {
                            for (int idx = 0; idx < 4; idx++) {
                                if (favorites[idx] == grid[ny][nx]) {
                                    favs++;
                                    break;
                                }
                            }
                        }
                    }
                }
                seats.add(new Seat(i, j, favs, empty));
            }
        }

        Collections.sort(seats, (a, b) -> {
            if (a.adjFavs == b.adjFavs) {
                if (a.adjEmpty == b.adjEmpty) {
                    if (a.y == b.y) return Integer.compare(a.x, b.x);
                    return Integer.compare(a.y, b.y);
                }
                return -Integer.compare(a.adjEmpty, b.adjEmpty);
            }
            return -Integer.compare(a.adjFavs, b.adjFavs);
        });

        Seat chosenSeat = seats.get(0);
        grid[chosenSeat.y][chosenSeat.x] = student;
    }

    static class Seat {
        int adjFavs, adjEmpty, y, x;
        Seat (int y, int x, int adjFavs, int adjEmpty) {
            this.y = y;
            this.x = x;
            this.adjFavs = adjFavs;
            this.adjEmpty = adjEmpty;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        grid = new int[N][N];

        for (int i = 0; i < N*N; i++) {
            st = new StringTokenizer(br.readLine());
            int student = Integer.parseInt(st.nextToken());
            int[] favs = new int[4];
            for (int j = 0; j < 4; j++) {
                favs[j] = Integer.parseInt(st.nextToken());
            }    
            map.put(student, favs);
            locate(student, favs);
        }
        System.out.println(survey());
    }
}
