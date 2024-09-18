import java.util.*;
import java.io.*;

public class 강희진 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N, M;

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        int[] nums = new int[M];
        st = new StringTokenizer(br.readLine());
        int idx = 0;
        while (st.hasMoreTokens()) {
            nums[idx++] = Integer.parseInt(st.nextToken());
        }

        LinkedList<Integer> queue = new LinkedList<>();
        for (int i = 0; i < N; i++) {
            queue.add(i + 1);
        }
        int ans = 0;
        for (int i = 0; i < M; i++) {
            int target = nums[i];
            int targetIdx = queue.indexOf(target);
            int move = Math.min(targetIdx, queue.size() - targetIdx);
            if (move == targetIdx) {
                for (int j = 0; j < move; j++) {
                    int curr = queue.removeFirst();
                    queue.add(curr);
                }
            } else {
                for (int j = 0; j < move; j++) {
                    int curr = queue.removeLast();
                    queue.addFirst(curr);
                }
            }
            queue.remove(0);
            ans += move;
        }
        System.out.println(ans);
        br.close();
    }
}