import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.StringTokenizer;

public class right_big_number {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static Stack<Integer> A = new Stack<>();
    private static int[] idxArr;
    private static int N;
    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            A.push(Integer.parseInt(st.nextToken()));
        }

        findRightBigNumber();
        StringBuilder sb = new StringBuilder();
        for(int a : idxArr){
            if(a == 0) continue;
            sb.append(a).append(" ");
        }
        System.out.println(sb);
    }

    private static void findRightBigNumber(){
        Stack<Integer> right = new Stack<>();
        idxArr = new int[N]; // 0 1 2 3 4

        for(int i = N-1; i >= 0; i--) {
            int temp = A.pop();
            while (!right.isEmpty() && temp >= right.peek()) {
                right.pop();
            }
            if (!right.isEmpty()) {
                idxArr[i] = right.peek();
            } else {
                idxArr[i] = -1;
            }
            right.push(temp);
        }
    }

}
