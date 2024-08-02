import java.io.*;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class four17298 {
    public void solution() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder builder = new StringBuilder();
        int n = Integer.parseInt(reader.readLine());
        Deque<Integer> stack = new ArrayDeque<>();
        int[] nums = new int[n];

        StringTokenizer infoToken = new StringTokenizer(reader.readLine());
        for (int i = 0; i < n; i++) {
           nums[i] = Integer.parseInt(infoToken.nextToken());
        }

        for (int i = 0; i < n; i++) {
            if (stack.isEmpty()) {
                stack.push(i);
                continue;
            }
            if (nums[stack.peek()] >= nums[i]) {
                stack.push(i);
            } else {
                while (!stack.isEmpty() && nums[stack.peek()] < nums[i]) {
                    nums[stack.pop()] = nums[i];
                }
                stack.push(i);
            }
        }
        while (!stack.isEmpty()) {
            nums[stack.pop()] = -1;
        }
        for (int i = 0; i < n; i++) {
            builder.append(nums[i]).append(" ");
        }
        writer.write(builder.toString());
        writer.flush();
    }

    public static void main(String[] args) throws IOException {
        new four17298().solution();
    }
}
