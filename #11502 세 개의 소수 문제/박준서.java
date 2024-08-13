package august.eight;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class three_prime_solution_2 {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static List<Integer> result=new ArrayList<>();
    private static boolean found = false;
    public static void main(String[] args) throws IOException {

        int T = Integer.parseInt(br.readLine());
        for (int t = 0; t < T; t++) {
            int N = Integer.parseInt(br.readLine());
            List<Integer> primes = isPrime(N); // 확인 완
            found = false;
            result.clear();
            List<Integer> temp = new ArrayList<>();
            back_tracking(primes, N, 0, 3, temp, 0);

            StringBuilder sb = new StringBuilder();
            for(int r : result){
                sb.append(r).append(" ");
            }
            System.out.println(sb);
        }
    }

    private static List<Integer> isPrime(int N){
        List<Integer> primes = new ArrayList<>();
        boolean[] isPrime = new boolean[N+1];
        for(int i = 0; i <= N; i++){
            isPrime[i] = true;
        }
        isPrime[0] = false; isPrime[1] = false;
        for(int i = 2; i <= Math.sqrt(N); i++){
            if(isPrime[i]) {
                for(int j = i * i; j <= N; j+=i) {
                    isPrime[j] = false;
                }
            }
        }
        for(int i = 0; i < isPrime.length; i++){
            if(isPrime[i]){
                primes.add(i);
            }
        }
        return primes;
    }

    private static void back_tracking(List<Integer> primes, int N, int sum, int r, List<Integer> temp, int start){
        if(temp.size() == r) {
            if (sum == N) {
                found = true;
                result = new ArrayList<>(temp);
            }
            return;
        }

        if(temp.size() > r || sum > N) { // 넘으면 빼주는거 있어야 함
            return;
        }
            for(int i = start ; i < primes.size(); i++){// start
                if(found) return;

                temp.add(primes.get(i));

                back_tracking(primes, N, sum+ primes.get(i), r , temp, i);

                temp.remove(temp.size()-1);
            }
        }

}
