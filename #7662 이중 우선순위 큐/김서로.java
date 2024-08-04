package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.HashMap;
import java.util.PriorityQueue;

public class 김서로 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(input.readLine());
		for (int t=0; t<T; t++) {
			// 최대 힙과 최소 힙의 동기화 목적 
			HashMap<Integer, Integer> numberCount = new HashMap<>();
			// 최대 힙 
			PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
			// 최소 힙 
			PriorityQueue<Integer> minHeap = new PriorityQueue<>();
			
			int K = Integer.parseInt(input.readLine());
			for (int k=0; k<K; k++) {
				String[] inputs = input.readLine().split(" ");
				String cmd = inputs[0];
				int n = Integer.parseInt(inputs[1]);
				
				// 삽입 
				if (cmd.equals("I")) {
					numberCount.put(n, numberCount.getOrDefault(n, 0) + 1);
					maxHeap.add(n);
					minHeap.add(n);
					
				// 삭제 
				} else {
					while (!(maxHeap.isEmpty() || minHeap.isEmpty())) {
						int temp;
						// 최댓값 삭제 
						if (n == 1) {
							temp = maxHeap.poll();
						// 최솟값 삭제 
						} else {
							temp = minHeap.poll();
						}
						//numberCount 동기화 
						if (numberCount.getOrDefault(temp, 0) > 0) {
							numberCount.put(temp, numberCount.get(temp) - 1);
							// 수가 없으면 key 삭제 
							if (numberCount.get(temp) == 0) {
								numberCount.remove(temp);
							}
							break;
						}
					}
				}
			}
			
			// 결과 출력 
			if (!(numberCount.isEmpty())) {
				int mx = 0;
				int mn = 0;
				while (!(maxHeap.isEmpty())) {
					mx = maxHeap.poll();
					if (numberCount.getOrDefault(mx, 0) != 0) {
						break;
					}
				}
				while (!(minHeap.isEmpty())) {
					mn = minHeap.poll();
					if (numberCount.getOrDefault(mn, 0) != 0) {
						break;
					}
				}
				System.out.printf("%d %d\n", mx, mn);
			} else {
				System.out.println("EMPTY");
			}
		
		}

	}

}
