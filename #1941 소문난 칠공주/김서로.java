package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;

public class 소문난칠공주 {
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	private static final int NUM = 5; // 행렬 길이
	private static char[][] arrangement = new char[NUM][NUM];
	private static int[][] princess = new int[7][2]; // 선택된 공주의 좌표
	private static int[][] coords = new int[NUM * NUM][2]; // 모든 좌표
	private static final int[][] deltas = new int[][] { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

	private static int maxOurNum; // 우리 팀(S) 최대 인원
	private static int maxYourNum; // 상대 팀(Y) 최대 인원
	private static int answer = 0; // 총 경우의 수

	// 모든 공주가 인접해 있는지 판단
	private static boolean isNear() {
		boolean[][] visited = new boolean[NUM][NUM];
		LinkedList<int[]> queue = new LinkedList<>();

		// 첫 번째 좌표를 시작점으로 설정
		queue.addLast(princess[0]);

		while (!queue.isEmpty()) {
			int[] current = queue.pollFirst();
			int r = current[0];
			int c = current[1];

			// 방문했으면
			if (visited[r][c]) {
				continue;
			}

			// 방문 처리
			visited[r][c] = true;

			// 사방 탐색
			for (int[] delta : deltas) {
				int nr = r + delta[0];
				int nc = c + delta[1];

				for (int i = 1; i < 7; i++) {
					if (nr == princess[i][0] && nc == princess[i][1]) {
						queue.addLast(princess[i]);
					}
				}
			}
		}

		for (int i = 0; i < 7; i++) {
			if (!visited[princess[i][0]][princess[i][1]])
				return false;
		}
		return true;
	}

	// 공주의 중복 없는 조합 생성
	private static void makeCombination(int depth, int start, int ourNum, int yourNum) {
		if (depth >= 7) {
			if (isNear())
				answer++;
			return;
		}

		for (int i = start; i < NUM * NUM; i++) {
			int r = coords[i][0];
			int c = coords[i][1];

			if (arrangement[r][c] == 'S') {
				if (ourNum >= maxOurNum) {
					continue;
				}
				princess[depth] = new int[] { r, c };
				makeCombination(depth + 1, i + 1, ourNum + 1, yourNum);
			} else {
				if (yourNum >= maxYourNum) {
					continue;
				}
				princess[depth] = new int[] { r, c };
				makeCombination(depth + 1, i + 1, ourNum, yourNum + 1);
			}
		}
	}

	private static void init() throws IOException {
		int index = 0;

		for (int i = 0; i < NUM; i++) {
			arrangement[i] = br.readLine().toCharArray();

			for (int j = 0; j < NUM; j++) {
				coords[index++] = new int[] { i, j };
			}
		}
	}

	public static void main(String[] args) throws IOException {
		init();
		for (int i = 4; i <= 7; i++) {
			maxOurNum = i;
			maxYourNum = 7 - maxOurNum;
			// 조합 생성
			makeCombination(0, 0, 0, 0);
		}
		System.out.println(answer);
	}

}
