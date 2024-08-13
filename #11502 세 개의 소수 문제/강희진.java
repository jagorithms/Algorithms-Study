import java.util.*;

import java.io.*;

public class Main {
    static final char WHITESPACE = ' ', NEW_LINE = '\n';
    static int[][] nums = new int[3003][3];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(st.nextToken());


        
        for (int first = 2; first < 1001 ; first++) {
            if (isPrime(first)) {
                for (int sec = first; sec < 1001; sec++) {
                    if (isPrime(sec)) {
                        for (int third = sec; third < 1001; third++) {
                            int sum = first+sec+third;
                            if (isPrime(third)) {
                                nums[sum][0] = first;
                                nums[sum][1] = sec;
                                nums[sum][2] = third;
                            }
                        }
                    }
                }
            }
        }   

        for (int i = 0; i < T; i++) {
            int num = Integer.parseInt(br.readLine());
            if (nums[num][0] == 0) sb.append(0);
            else {
                sb.append(nums[num][0]).append(WHITESPACE).append(nums[num][1]).append(WHITESPACE).append(nums[num][2]);
            }
            sb.append(NEW_LINE);
        }
        System.out.append(sb);

    }

    private static boolean isPrime(int n) {
        if (n == 1) return false;
        for (int i = 2; i <= Math.sqrt(n); i++) {
            if (n % i == 0) return false;
        }
        return true;
    }

}
