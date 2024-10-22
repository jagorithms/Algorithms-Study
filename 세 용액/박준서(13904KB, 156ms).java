import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	private static int N;
	private static long[] solutions;
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static long answer = Long.MAX_VALUE;
	private static long a, b, c;
	private static long[] answers = new long[3];
	public static void main(String[] args) throws IOException {
		N = Integer.parseInt(br.readLine());
		solutions = new long[N];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i ++) {
			solutions[i] = Long.parseLong(st.nextToken());
		}
		
		Arrays.sort(solutions);
		binarySearch();
		
		answers[0] = a;
		answers[1] = b;
		answers[2] = c;
		
		Arrays.sort(answers);
		
		for(int i = 0; i < 3; i++) {
			System.out.print(answers[i] + " ");
		}
	}
	
	
	private static void binarySearch() {
		for(int i = 0; i <= N - 3; i++) {
			int start = i + 1;
			int end = N - 1;
			while(start < end) {
				long temp = (long) (solutions[i] + solutions[start] + solutions[end]);
				if(answer > Math.abs(temp)) {
					answer = Math.abs(temp);
					a = solutions[i];
					b = solutions[start];
					c = solutions[end];
				}
				
				if(temp > 0) {
					end--;
				} else {
					start++;
				}
			}
		}
	}

}
