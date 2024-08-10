
import java.io.BufferedReader;
import java.io.DataOutput;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class five10472 {
    static HashMap<Integer, int[]> filps = new HashMap<>();
    static final char BLACK = '*';
    static int map[];
    static int[] isFliped;


    public void solution() throws IOException {
        init();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int testNum = Integer.parseInt(reader.readLine());
        for (int test = 0; test < testNum; test++) {
            // 입력부
            map = new int[10];
            isFliped = new int[10];
            for (int i = 0; i < 3; i++) {
                String input = reader.readLine();
                for (int j = 0; j < 3; j++) {
                    map[i * 3 + j] = (input.charAt(j) == BLACK) ? 1 : 0 ;
                }
            }
            System.out.println(bfs());
        }
    }

    private int bfs() {
        Deque<int[][]> toFlip = new ArrayDeque<>();
        toFlip.add(new int[][] {map, isFliped});

        while (!toFlip.isEmpty()) {
            int[][] current = toFlip.poll();

            if (isComplete(current[0])) {
                return current[0][9];
            }

            for (int i = 0; i < 9; i++) {
                if (current[1][i] == 1) continue; // 이미 뒤집었다면 패스

                int[] newMap = current[0].clone();
                int[] newIsFliped = current[1].clone();

                for (int flip : filps.get(i)) { // 뒤집기s
                    newMap[flip] = newMap[flip] == 0 ? 1 : 0;
                }

                newIsFliped[i] = 1;
                newMap[9]++;

                toFlip.add(new int[][] {newMap, newIsFliped});
            }
        }
        return 0;
    }

    private boolean isComplete(int[] map) {
        for (int i = 0; i < 9; i++) {
            if (map[i] == 1) return false;
        }
        return true;
    }

    private void init() {
        filps.put(0, new int[] {0, 1, 3});
        filps.put(1, new int[] {1, 0, 2, 4});
        filps.put(2, new int[] {2, 1, 5});
        filps.put(3, new int[] {3, 0, 4, 6});
        filps.put(4, new int[] {4, 1, 3, 5, 7});
        filps.put(5, new int[] {5, 2, 4, 8});
        filps.put(6, new int[] {6, 3, 7});
        filps.put(7, new int[] {7, 4, 6, 8});
        filps.put(8, new int[] {8, 5, 7});
    }


    public static void main(String[] args) throws IOException {
        new five10472().solution();
    }
}
