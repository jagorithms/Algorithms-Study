import java.util.*;
import java.io.*;

public class Main {

    // 출처: 구본관
    private static final List<List<int[]>> tetris = new ArrayList<>(Arrays.asList(
            null,
        Arrays.asList(new int[]{0}, new int[]{0, 0, 0, 0}), // 1
        Arrays.asList(new int[]{0, 0}),                       // 2
        Arrays.asList(new int[]{0, 0, 1}, new int[]{0, -1}),  // 3
        Arrays.asList(new int[]{0, -1, -1}, new int[]{0, 1}), // 4
        Arrays.asList(new int[]{0, 0, 0}, new int[]{0, -1}, new int[]{0, 1}, new int[]{0, -1, 0}), // 5
        Arrays.asList(new int[]{0, 0, 0}, new int[]{0, 0}, new int[]{0, -2}, new int[]{0, 1, 1}), // 6
        Arrays.asList(new int[]{0, 0, 0}, new int[]{0, 0}, new int[]{0, 2}, new int[]{0, 0, -1}) // 7
    ));

    static int C, P, heights[];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        C = Integer.parseInt(st.nextToken());
        P = Integer.parseInt(st.nextToken());
        heights = new int[C];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < C; i++) {
            heights[i] = Integer.parseInt(st.nextToken());
        }
        int result = 0;
        for (int i = 0; i < C; i++) {
            for (int[] blockShape : tetris.get(P)) {
                if (canPlaceBlock(i, blockShape)) {
                    result++;
                }
            }
        }
        System.out.println(result);
    }

    private static boolean canPlaceBlock(int start, int[] blockShape) {
        if (start + blockShape.length > C) return false;
        int baseHeight = heights[start];
        for (int j = 0; j < blockShape.length; j++) {
            if (heights[start + j] != baseHeight + blockShape[j]) {
                return false;
            }
        }
        return true;
    }
}
