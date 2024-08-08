import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        //StringTokenizer stringTokenizer = new StringTokenizer(br.readLine());

        boolean[] decimalli = new boolean[1001];
        Arrays.fill(decimalli, true);
        decimalli[0] = decimalli[1] = false;

        ArrayList<Integer> prime = new ArrayList<>();

        for (int i=2; i<1001; i++){
            if (decimalli[i]){
                prime.add(i);
                for (int j = i*2; j < 1001; j+=i) {
                    decimalli[j] = false;
                }
            }
        }

        HashMap<Integer, Integer[]> dic = new HashMap<>();

        for (int a = 0; a < prime.size(); a++) {
            for (int b = a; b < prime.size(); b++) {
                for (int c = b; c < prime.size(); c++) {
                    dic.put(prime.get(a)+prime.get(b)+prime.get(c), new Integer[]{prime.get(a),prime.get(b),prime.get(c)});
                }
            }
        }

        StringBuilder sb = new StringBuilder();

        int num = Integer.parseInt(br.readLine());
        for (int i = 0; i < num; i++) {
            int n = Integer.parseInt(br.readLine());
            if(dic.containsKey(n)){
                for (Integer integer : dic.get(n)) {
                    sb.append(integer + " ");
                }
            }
            sb.append('\n');
        }
        System.out.println(sb);
    }
}
