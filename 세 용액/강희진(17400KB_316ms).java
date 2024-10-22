import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());
        long[] nums = new long[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            nums[i] = Long.parseLong(st.nextToken());
        }
        Arrays.sort(nums);

        long minDiff = Long.MAX_VALUE;
        long[] ans = new long[3];
        for (int i = 0; i < N; i++) {
            long[] tmp = solve(nums, nums[i], i);
            if (Math.abs(tmp[0] + tmp[1] + tmp[2]) < minDiff) {
                minDiff = Math.abs(tmp[0] + tmp[1] + tmp[2]);
                ans = tmp;
            }
        }
        Arrays.sort(ans);
        System.out.println(ans[0]+" "+ans[1]+" "+ans[2]);
    }

    private static long[] solve(long[] nums, long chosen, int idx) {
        int left = 0, right = nums.length - 1;
        long[] ans = new long[3];
        ans[0] = chosen;

        long tmpDiff = Long.MAX_VALUE;

        while (left < right) {
            if (left == idx) {
                left++;
                continue;
            } else if (right == idx) {
                right--;
                continue;
            }

            long s = Math.abs(chosen + nums[left] + nums[right]);
            if (s < tmpDiff) {
                ans[1] = nums[left];
                ans[2] = nums[right];
                tmpDiff = s;
                if (tmpDiff == 0) {
                    return ans;
                }
            } else if (chosen + nums[left] + nums[right] > 0) {
                right--;
            } else {
                left++;
            }
        }
        return ans;
    }
    

}
