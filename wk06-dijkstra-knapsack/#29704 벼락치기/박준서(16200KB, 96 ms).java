import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static class HomeWork{
		int day, money;
		public HomeWork(int day, int money) {
			this.day = day;
			this.money = money;
		}
	}
	
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static int N, T;
	private static HomeWork[] homeworks;
	private static int[][] bag;
	private static int totalMoney = 0;
	public static void main(String[] args) throws IOException {
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		
		homeworks = new HomeWork[N];
		bag = new int[N+1][T+1];
		

		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int day = Integer.parseInt(st.nextToken());
			int money = Integer.parseInt(st.nextToken());
			totalMoney += money;
			homeworks[i] = new HomeWork(day,money);
			
		}
		
		for(int i = 1; i <= N; i++) {
			for(int j = 1; j <= T; j++) {
				if(j < homeworks[i-1].day) {
					bag[i][j] = bag[i-1][j];
				} else {
					bag[i][j] = Math.max(bag[i-1][j], bag[i-1][j - homeworks[i-1].day] + homeworks[i-1].money);
				}
			}
		}
		System.out.println(bag[N][T] == Integer.MAX_VALUE ? -1 : totalMoney - bag[N][T]);
	}

	
	
}
