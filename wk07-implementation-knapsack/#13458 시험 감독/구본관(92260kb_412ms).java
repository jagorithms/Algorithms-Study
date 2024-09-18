import java.io.*;
import java.util.*;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;

	public static void main(String[] args) throws Exception {
		int N = Integer.parseInt(br.readLine());
		int[] li = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			li[i] = Integer.parseInt(st.nextToken());
		}

		st = new StringTokenizer(br.readLine());
		int B = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());

		long result = N; // long으로 변경하여 큰 수 처리
		for (int i = 0; i < N; i++) {
			int remaining = li[i] - B;
			if (remaining > 0) {
				result += (remaining / C);
				if (remaining % C != 0) {
					result++;
				}
			}
		}

		sb.append(result);
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}
}
