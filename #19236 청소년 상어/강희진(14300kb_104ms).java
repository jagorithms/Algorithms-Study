import java.util.*;
import java.io.*;
public class Main {
    static int totalFish;
    static final int DY[] = {-1, -1, 0, 1, 1, 1, 0, -1}, DX[] = {0, -1, -1, -1, 0, 1, 1, 1};

    static void simulate(int[][] grid, Fish[] school) {
        Shark shark = new Shark(0, 0, school[grid[0][0]].direction);
        int initialFish = grid[0][0];
        school[grid[0][0]] = null;
        grid[0][0] = 0;
    
        moveFish(grid, school, shark);
        moveShark(initialFish, grid, school, shark);
    }

    static void moveShark(int fishEaten, int[][] grid, Fish[] school, Shark shark) {
        totalFish = Math.max(fishEaten, totalFish);
        for (int dist = 1; dist <= 4; dist++) {
            int ny = shark.y + dist*DY[shark.direction];
            int nx = shark.x + dist*DX[shark.direction];
            if (isValidCoordinate(ny, nx) && grid[ny][nx] > 0) {
                Fish eatenFish = school[grid[ny][nx]];

                Shark newShark = new Shark(ny, nx, eatenFish.direction);
                int[][] newGrid = duplicateGrid(grid);
                Fish[] newSchool = duplicateSchool(school);
                newGrid[ny][nx] = 0;
                newSchool[eatenFish.idx] = null;
                
                moveFish(newGrid, newSchool, newShark);
                moveShark(fishEaten + grid[ny][nx], newGrid, newSchool, newShark);
            }
        }
    }

    static Fish[] duplicateSchool(Fish[] school) {
        Fish[] newSchool = new Fish[16+1];
        for (int i = 1; i <= 16; i++) {
            if (school[i]==null)continue;
            newSchool[i] = new Fish(school[i].idx, school[i].direction, school[i].y, school[i].x);
        }
        return newSchool;
    }

    static int[][] duplicateGrid(int[][] grid) {
        int[][] newGrid = new int[4][4];
        for (int i =0 ; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                newGrid[i][j] = grid[i][j];
            }
        }
        return newGrid;
    }
    
    static void moveFish(int[][] grid, Fish[] school, Shark shark) {
        for (int i = 1; i <= 16; i++) {
            if (school[i] == null) continue;
            Fish fish = school[i];
            for (int d = 0; d < 8; d++) {
                int nextDirection = nextDir(fish.direction, d);
                int ny = fish.y + DY[nextDirection];
                int nx = fish.x + DX[nextDirection];
                if (isValidCoordinate(ny, nx) && !sharkIsHere(ny, nx, shark)) {
                    int tmp = grid[ny][nx];
                    grid[ny][nx] = fish.idx;
                    grid[fish.y][fish.x] = tmp;
                    
                    if (tmp != 0) {
                        school[tmp].y = fish.y;
                        school[tmp].x = fish.x;
                    }

                    school[i].direction = nextDirection;
                    school[i].y = ny;
                    school[i].x = nx;

                    break;
                }
            }
        }
    }

    static boolean sharkIsHere(int y, int x, Shark shark) {
        return y == shark.y && x == shark.x;
    }

    static boolean isValidCoordinate(int y, int x) {
        return y >= 0 && x < 4 && x >= 0 && y < 4;
    }

    static class Shark {
        int y, x, direction;
        Shark(int y, int x, int direction) {
            this.y = y;
            this.x =x;
            this.direction =direction;
        }
    }

    static class Fish {
        int y, x, idx, direction;
        Fish(int idx, int direction, int y, int x) {
            this.idx = idx;
            this.direction = direction;
            this.y = y;
            this.x = x;
        }
    }

    static int nextDir(int dirIdx, int move) {
        return (dirIdx + move) % 8;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int[][] grid = new int[4][4];
        Fish[] school = new Fish[16+1];
        for (int i = 0; i < 4; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0;j < 4; j++) {
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                b--;
                school[a] = new Fish(a, b, i, j);
                grid[i][j] = a;
            }
        }
        simulate(grid, school);
        System.out.println(totalFish);
    }
}
