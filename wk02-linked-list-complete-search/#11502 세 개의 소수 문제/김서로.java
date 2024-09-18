import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

	private static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    // 소수인지 여부를 boolean으로 저장
	private static boolean[] prime = new boolean[1000];
    
    // 소수인지 판단
	private static boolean isPrime(int num) {
		for (int i = 2; i < (int) Math.sqrt(num) + 1; i++) {
			if (num % i == 0) {
				return false;
			}
		}
		return true;
	}
    
    // 홀수를 세 개의 소수로 출력
	private static void printThreePrime(int num) {
		for (int i = 2; i < num; i++) {
			for (int j = 2; j < num; j++) {
				if (num - i - j > 0 && prime[i] && prime[j] && prime[num - i - j]) {
					StringBuilder output = new StringBuilder();
					output.append(i).append(" ").append(j).append(" ").append(num - i - j);
					System.out.println(output);
					return;
				}
			}
		}
		System.out.println(0);
	}

	public static void main(String[] args) throws Exception {
		// 소수 미리 구해두기
		for (int i=2; i < 1000; i++) {
			prime[i] = isPrime(i);
		}
		
		int T = Integer.parseInt(input.readLine());
		
		for (int i=0; i < T; i++) {
			int k = Integer.parseInt(input.readLine());
			printThreePrime(k);
		}
	}

}
