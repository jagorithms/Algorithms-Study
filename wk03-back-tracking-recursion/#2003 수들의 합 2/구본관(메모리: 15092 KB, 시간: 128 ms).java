//메모리: 15092 KB, 시간: 128 ms
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.StringTokenizer;

class Main
{
	
	
	public static void main(String args[]) throws Exception
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int[] li = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			li[i] = Integer.parseInt(st.nextToken());
		}
		
		
		int sum = li[0];
		int start = 0;
		int end = 1;
		
		int result = 0;
		while(true) {
			if(sum<M) {
				if(end<N) {
					sum+=li[end];
					end+=1;
				}else {
					break;
				}
			}else {
				if(sum==M)
					result+=1;
				sum-=li[start];
				start+=1;
			}
		}
		System.out.println(result);
	}
}
