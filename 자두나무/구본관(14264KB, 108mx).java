import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int T,W;

    public static void main(String[] args) throws Exception {
        st = new StringTokenizer(br.readLine());
        T = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());

        int[] dp = new int[W+1];

        List<Integer> list = new ArrayList<>();

        int s = 1;
        int count = 0;
        for (int i = 0; i < T; i++) {
            int d = Integer.parseInt(br.readLine());
            if(s == d){
                count+=1;
            }else{
                list.add(count);
                s = d;
                count = 1;
            }
        }
        list.add(count);
        //System.out.println(list);

        for (int i = 0; i < list.size(); i++) {
            if(i%2==0){//1
                dp[0]+=list.get(i);
                for (int w = 2; w <= W; w+=2) {
                    if(dp[w]>0)
                        dp[w]+=list.get(i);
                    if(dp[w-1]>0){
                        dp[w] = Math.max(dp[w], dp[w-1]+list.get(i));
                    }
                }
            }else{
                dp[1]+=list.get(i);
                if(dp[0]>0){
                    dp[1] = Math.max(dp[1], dp[0]+list.get(i));
                }
                for (int w = 3; w <= W; w+=2) {
                    if(dp[w]>0)
                        dp[w]+=list.get(i);
                    if(dp[w-1]>0){
                        dp[w] = Math.max(dp[w], dp[w-1]+list.get(i));
                    }
                }
            }
            //System.out.println(Arrays.toString(dp));
        }
        int result = 0;
        for (int d : dp){
            result = Math.max(result, d);
        }
        System.out.println(result);
    }
}
