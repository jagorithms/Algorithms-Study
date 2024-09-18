package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

// 메모리: 14380 KB, 시간: 116 ms

public class 트럭 {
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static StringTokenizer tokens;

	private static int truckNum; // 트럭 수
	private static int bridgeLength; // 다리 길이
	private static int maxWeight; // 최대 하중
	private static int[] trucks; // 모든 트럭들의 무게

	// 현재 드라이브 중인 트럭들의 무게
	private static ArrayDeque<Integer> drive = new ArrayDeque<>();
	// 경과 시간
	private static int time = 0;

	private static void solution() {
		int weight = 0; // 현재 다리 무게

		for (int truck : trucks) {
			while (weight - drive.peekFirst() + truck > maxWeight) {
				weight -= drive.pollFirst();
				// 다리 길이 유지하기 위해 0 삽입
				drive.addLast(0);
				time++;
			}

			weight -= drive.pollFirst();
			weight += truck;
			drive.addLast(truck);
			time++;
		}

		time += bridgeLength;
	}

	private static void init() throws IOException {
		tokens = new StringTokenizer(br.readLine());

		truckNum = Integer.parseInt(tokens.nextToken());
		bridgeLength = Integer.parseInt(tokens.nextToken());
		maxWeight = Integer.parseInt(tokens.nextToken());

		trucks = new int[truckNum];
		tokens = new StringTokenizer(br.readLine());
		for (int i = 0; i < truckNum; i++) {
			trucks[i] = Integer.parseInt(tokens.nextToken());
		}

		// 다리 길이를 bridgeLength으로 맞추기 위해 0 삽입
		for (int i = 0; i < bridgeLength; i++) {
			drive.add(0);
		}
	}

	public static void main(String[] args) throws IOException {
		init();
		solution();
		System.out.println(time);
	}

}
