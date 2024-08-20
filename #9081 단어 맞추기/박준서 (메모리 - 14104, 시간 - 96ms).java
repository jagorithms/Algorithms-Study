import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static int T;
	private static char[] input;
	private static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws IOException {
		T = Integer.parseInt(br.readLine());
		for(int t = 0; t < T; t++) {
			input = br.readLine().toCharArray();
			next_permutation(input);
			for(int i = 0; i < input.length; i++) {
				sb.append(input[i]);
			}
			sb.append("\n");
		}
		System.out.println(sb);
	
	}
	
	private static boolean next_permutation(char[] c) {
		int N = c.length;
		
		int i = N-1;
		while(i > 0 && c[i-1] >= c[i]) --i;
		
		if(i == 0) return false;
		
		int j = N-1;
		while(c[i-1] >= c[j]) --j;
		
		swap(c, i-1, j);
		
		int k = N-1;
		while (i < k) {
			swap(c, i++, k--);
		}
		return true;
	}
	
	private static void swap(char[] c, int i, int j) {
		char temp = c[i];
		c[i] = c[j];
		c[j] = temp;
	}

}
