import java.io.*;
import java.lang.reflect.Array;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int N;
    static int[] li;
    static int result = 0;

    public static void main(String[] args) throws Exception {
        N = Integer.parseInt(br.readLine());

        li = new int[N+1];
        dfs(1);

        System.out.println(result);
    }

    static void dfs(int index){
        if(index == N+1){
            result+=1;
            //System.out.println(Arrays.toString(li));
            return;
        }

        for (int i = 1; i < N+1; i++) {
            if(check(index, i)){
                li[index] = i;
                dfs(index+1);
                li[index] = 0;
            }
        }
    }

    static boolean check(int index, int i){
        for (int j = 1; j < N+1; j++) {
            if(li[j]>0){
                if(li[j] == i){
                    return false;
                }
                if(Math.abs(j-index) == Math.abs(i-li[j])){
                    return false;
                }
            }
        }
        return true;
    }
}
