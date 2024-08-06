package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class 김서로 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder output = new StringBuilder();
		
		int N = Integer.parseInt(input.readLine());
		int[][] schedule = new int[N][2];
		
		// 컴퓨터 시작과 종료 시각 입력 받기 
		for (int i=0; i<N; i++) {
			String[] str = input.readLine().split(" ");
			int start = Integer.parseInt(str[0]);
			int end = Integer.parseInt(str[1]);
			schedule[i] = new int[] {start, end};
		}
		
		// 시작 시간을 기준으로 정렬 
		Arrays.sort(schedule, Comparator.comparing(times -> times[0]));
		
		// 컴퓨터 이용한 사람 수 
		int[] computerCount = new int[N];
		// 컴퓨터 종료 시각을 기준으로 최소 힙 생성 
		PriorityQueue<int[]> minHeap = new PriorityQueue<>(Comparator.comparing(times -> times[1]));
		// 사용 가능한 컴퓨터 
		PriorityQueue<Integer> availableComputer = new PriorityQueue<>(); 
		
		int count = 0;
		for (int[] times: schedule) {
			int start = times[0];
			int end = times[1];
			
			// 사용 가능한 컴퓨터 갱신 
			while (!minHeap.isEmpty() && minHeap.peek()[1] <= start) {
				int index = minHeap.poll()[2];
				// 번호가 가장 작은 자리에 앉도록 최소 힙에 삽입 
				availableComputer.add(index);
			}
			
			// 사용 가능한 컴퓨터가 있다면 
			if (!availableComputer.isEmpty()) {
				int index = availableComputer.poll();
				computerCount[index]++;
				minHeap.add(new int[] {start, end, index});
			
			// 사용 가능한 컴퓨터가 없다면 
			} else {
				computerCount[count]++;
				minHeap.add(new int[] {start, end, count});
				count++;
			}
		}
		
		// 출력 
		output.append(count).append("\n");
		for (int i=0; i<count; i++) {
			output.append(computerCount[i]).append(" ");
		}
		
		System.out.println(output);
	}

}
