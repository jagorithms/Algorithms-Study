import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class 김서로 {

	public static void main(String[] args) throws IOException {
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tokens;
		
		tokens = new StringTokenizer(input.readLine());
		int N = Integer.parseInt(tokens.nextToken());
		int M = Integer.parseInt(tokens.nextToken());
		
		// 뽑아내려고 하는 수의 위치 
		int[] targets = new int[M];
		tokens = new StringTokenizer(input.readLine());
		for (int i=0; i<M; i++) {
			targets[i] = Integer.parseInt(tokens.nextToken());
		}
		
		LinkedList<Integer> dq = new LinkedList<>();
		for (int i=0; i<N; i++) {
			dq.add(i+1);
		}
		
		// 연산 횟수 
		int count = 0;
		
		for (int target: targets) {
			// 현재 큐의 길이 
			int dqLength = dq.size();
			int targetIndex = dq.indexOf(target);
			
			// 왼쪽으로 이동시키는 방법이 효율적일 경우 
			if (targetIndex * 2 <= dqLength) {
				// 뽑아내려고 하는 수가 큐의 맨 앞에 올 때까지 
				while (dq.peek() != target) {
					count++;
					dq.addLast(dq.pollFirst());
				}
				dq.pollFirst();
			// 오른쪽으로 이동시키는 방법이 효율적일 경우 
			} else {
				// 뽑아내려고 하는 수가 큐의 맨 앞에 올 때까지 
				while (dq.peek() != target) {
					count++;
					dq.addFirst(dq.pollLast());
				}
				dq.pollFirst();
			}
		}
		
		System.out.println(count);	
	}
}
