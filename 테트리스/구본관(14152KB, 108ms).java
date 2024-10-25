import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {

        // 각 번호별 테트리스 바닥종류
        List<List<int[]>> tetris = new ArrayList<>(Arrays.asList(
                null,
                Arrays.asList(new int[]{0}, new int[]{0, 0, 0, 0}), // 1
                Arrays.asList(new int[]{0, 0}),                       // 2
                Arrays.asList(new int[]{0, 0, 1}, new int[]{0, -1}),  // 3
                Arrays.asList(new int[]{0, -1, -1}, new int[]{0, 1}), // 4
                Arrays.asList(new int[]{0, 0, 0}, new int[]{0, -1}, new int[]{0, 1}, new int[]{0, -1, 0}), // 5
                Arrays.asList(new int[]{0, 0, 0}, new int[]{0, 0}, new int[]{0, -2}, new int[]{0, 1, 1}), // 6
                Arrays.asList(new int[]{0, 0, 0}, new int[]{0, 0}, new int[]{0, 2}, new int[]{0, 0, -1}) // 7
        ));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int C = Integer.parseInt(st.nextToken());
        int P = Integer.parseInt(st.nextToken());

        int[] arr = new int[C];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < C; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int result = 0;

        for (int i = 0; i < C; i++) {
            List<Integer> height = new ArrayList<>();
            int cur = arr[i];
            for (int t = i; t < i + 4; t++) {
                if (t >= C) break;
                height.add(arr[t] - cur);

                int[] heightArray = new int[t-i+1];
                for (int j = 0; j < t-i+1; j++) {
                    heightArray[j] = height.get(j);
                }

                for (int[] pattern : tetris.get(P)) {
                    if (Arrays.equals(heightArray, pattern)) {
                        result++;
                        break;
                    }
                }
            }
        }

        System.out.println(result);
    }
}
