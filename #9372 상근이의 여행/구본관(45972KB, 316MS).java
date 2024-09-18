import java.io.*;
import java.util.*;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;

	static int N;
	static int[][] graph;
	static HashMap<Integer, Integer> dp;

	public static void main(String[] args) throws Exception {
		N = Integer.parseInt(br.readLine());

		for(int i=0; i<N; i++){
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			for(int t=0; t<b; t++){
				st = new StringTokenizer(br.readLine());
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
			}
			System.out.println(a-1);
		}
	}
}
