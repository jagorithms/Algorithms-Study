import java.util.*;
import java.io.*;

public class Main {	
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(br.readLine());
		
		int[] inputli = new int[N];
		Deque<Integer> dq = new ArrayDeque<>();
		int[] result = new int[N];
		
		Arrays.fill(result, -1);
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		for(int i=0; i<N; i++) {
			inputli[i] = Integer.parseInt(st.nextToken());
		}
		
		for(int i=0; i<N; i++) {
			while(!dq.isEmpty() && inputli[dq.peekLast()]<inputli[i]) {
				result[dq.pollLast()] = inputli[i];
			}
			dq.offerLast(i);
		}
		
		for(int i : result) {
			sb.append(i+" ");
		}
		System.out.println(sb);
	}
}
