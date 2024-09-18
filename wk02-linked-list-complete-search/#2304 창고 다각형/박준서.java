package august.ten;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class container {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static int[] height = new int[1001];
    private static int max = 0;
    private static int max_idx = 0;
    private static int last_idx = 0;
    private static int area = 0;

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine());
        for (int i = 0; i < N; i++) {
            String[] input = br.readLine().split(" ");
            height[Integer.parseInt(input[0])] = Integer.parseInt(input[1]);
            if (max < Integer.parseInt(input[1])){
                max = Integer.parseInt(input[1]);
                max_idx = Integer.parseInt(input[0]);
            }
            if(last_idx < Integer.parseInt(input[0])){
                last_idx = Integer.parseInt(input[0]);
            }
        }
        findArea();
        System.out.println(area);
    }

    private static void findArea() {
        int max_height = 0;
        for(int i = 0; i < max_idx; i++) { // max_idx = 8
            if(height[i] > max_height) {
                max_height = height[i];
            }
            area += max_height;
        }
        max_height = 0;
        for(int i = last_idx; i >= max_idx; i--){
            if(height[i] > max_height) {
                max_height = height[i];
            }
            area += max_height;
        }
    }
}
