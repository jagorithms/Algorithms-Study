package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class 김서로 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tokens;
		StringBuilder output = new StringBuilder();
		
        // 입력 받기
		int N = Integer.parseInt(input.readLine());
		int [] numbers = new int[N];
		
		tokens = new StringTokenizer(input.readLine());
		for (int i=0; i<N; i++) {
			numbers[i] = Integer.parseInt(tokens.nextToken());
		}
		
        // 오큰수를 찾지 못한 수들을 모아놓기 위함
		Stack<Integer> stack = new Stack<>();
		// 오큰수를 담을 배열 
        int[] NGEs = new int[N];
		
		// 모든 수의 오큰수를 -1로 초기화 
		for (int i=0; i<N; i++) {
			NGEs[i] = -1;
		}
		
		for (int i=0; i<N; i++) {
			while (!(stack.isEmpty()) && numbers[i] > numbers[stack.peek()]) {
				NGEs[stack.pop()] = numbers[i];
			}
			stack.add(i);
		}
		
        // 출력
		for (int NGE: NGEs) {
			output.append(NGE).append(" ");
		}
		
		System.out.println(output);
	}

}
