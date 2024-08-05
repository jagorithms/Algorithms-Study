import java.util.*;
import java.io.*;

public class Main {	
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		Deque<Integer> dq = new ArrayDeque<>();
		for(int i=1; i<=N; i++)
			dq.offer(i);
		
		st = new StringTokenizer(br.readLine());
		
		int result = 0;
		
		for(int i=0; i<M; i++) {
			int num = Integer.parseInt(st.nextToken());
			
			int t = 0;
			while(true) {
				int n = dq.pollFirst();
				if(n==num)
					break;
				dq.offerLast(n);
				t+=1;
			}
			//System.out.print(dq);
			//System.out.println(t + " " + (dq.size()+1-t));
			result += Math.min(dq.size()+1-t, t);
		}
		
		sb.append(result);
		
		System.out.println(sb);
	}
}
