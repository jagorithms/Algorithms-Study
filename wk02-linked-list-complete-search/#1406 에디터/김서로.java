import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static StringTokenizer tokens;
	private static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws NumberFormatException, IOException {
		// 스택 2개를 이용한 풀이
		Stack<Character> left = new Stack<>();
		Stack<Character> right = new Stack<>();

		// 입력된 문자열을 스택에 넣기
		String inputs = br.readLine();
		for (char c : inputs.toCharArray()) {
			left.add(c);
		}
		
		int M = Integer.parseInt(br.readLine());

		// 명령어 처리
		for (int i = 0; i < M; i++) {
			tokens = new StringTokenizer(br.readLine());
			String cmd = tokens.nextToken();

			switch (cmd) {
			case "L":
				if (!left.isEmpty()) {
					right.add(left.pop());
				}
				break;
			case "D":
				if (!right.isEmpty()) {
					left.add(right.pop());
				}
				break;
			case "B":
				if (!left.isEmpty()) {
					left.pop();
				}
				break;
			case "P":
				left.add(tokens.nextToken().charAt(0));
				break;
			}
		}
		
		// 출력
		for (char c : left) {
			sb.append(c);
		}

		while (!right.isEmpty()) {
			sb.append(right.pop());
		}

		System.out.println(sb);
	}

}
