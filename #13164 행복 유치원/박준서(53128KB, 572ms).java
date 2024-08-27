import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static int N, K;
	private static int[] height;
	private static int[] difference;
	private static int answer;
	public static void main(String[] args) throws IOException {
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		height = new int[N];
		difference = new int[N-1];
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			height[i] = Integer.parseInt(st.nextToken());
		}
		
		for(int i = 0; i < N-1; i++) {
			difference[i] = Math.abs(height[i] - height[i+1]);
		}
		
		Arrays.sort(difference);
		
		for(int i = 0; i < N-K; i++) {
			answer += difference[i];
		}
		
		System.out.println(answer);
		
	}
	
}
