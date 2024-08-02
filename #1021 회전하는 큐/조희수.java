import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class three1021 {
    public void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        LinkedList<Integer> nums = new LinkedList<>();
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        for (int i = 1; i <= n ; i++) {
            nums.offer(i);
        }

        int[] indexes = new int[m];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < m; i++) {
            indexes[i] = Integer.parseInt(st.nextToken());
        }


        int count = 0;
        for (int index : indexes) {
            int valueIndex = nums.indexOf(index);
            int halfIndex = (nums.size() % 2 == 0) ? nums.size() / 2 - 1 : nums.size() / 2;
            boolean flag = false;
            while (!flag) {
                if (nums.peek() == index) {
                    nums.pop();
                    flag = true;
                    break;
                }
                count++;

                if (valueIndex <= halfIndex) { // 왼쪽으로 돌리기
                    int replace = nums.pollFirst();
                    nums.offerLast(replace);
                } else { // 오른쪽으로 돌리기
                    int replace = nums.pollLast();
                    nums.offerFirst(replace);
                }
            }
        }
        System.out.println(count);
    }

    public static void main(String[] args) throws IOException {
        new three1021().solution();
    }
}
