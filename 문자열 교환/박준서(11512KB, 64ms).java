import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static String str;
	private static int answer = Integer.MAX_VALUE;
	public static void main(String[] args) throws IOException {
		str = br.readLine();
		
		int aCount = 0;
		for(int i = 0; i < str.length(); i++) {
			if(str.charAt(i) == 'a') {
				aCount++; // 창문 길이 지정 해준다
			}
		}
		
		
		for(int i = 0; i < str.length(); i++) {
			int bCount = 0;
			for(int j = i; j < i + aCount; j++) {
				if(str.charAt(j % str.length()) == 'b') {
					bCount++;
				}
			}
			answer = Math.min(answer, bCount);
		}
		
		System.out.println(answer);
	}

}
