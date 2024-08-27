import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static int N, K;
	private static int[] sensors;
	private static int[] difference;
	private static int answer;
	public static void main(String[] args) throws IOException {
		N = Integer.parseInt(br.readLine());
		K = Integer.parseInt(br.readLine());
		sensors = new int[N];
		difference = new int[N-1];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			sensors[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(sensors);
		for(int i = 0; i < N - 1; i++) {
			difference[i] = sensors[i+1] - sensors[i];
		}
		
		Arrays.sort(difference);
		
		for(int i = 0; i < N-K; i++) {
			answer += difference[i];
		}
		
		System.out.println(answer);

	}

}
