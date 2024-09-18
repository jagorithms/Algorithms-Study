import java.io.BufferedReader;
import java.io.IOError;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static int N;
	private static int[][] ingredient;
	private static int[] necessary = new int[4];
	private static List<int[]> comb = new ArrayList<>();
	private static int answer = Integer.MAX_VALUE;
	
	private static int protein, carbohydrate, fat, vitamin;
	private static StringBuilder sb;
	
	public static void main(String[] args) throws IOException {
		N = Integer.parseInt(br.readLine());
		ingredient = new int[N][5];
		
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i < 4; i++) {
			necessary[i] = Integer.parseInt(st.nextToken());
		}
		
		protein = necessary[0];
		carbohydrate = necessary[1];
		fat = necessary[2];
		vitamin = necessary[3];
	
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < 5; j++) {
				ingredient[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		makeDiet(0, new boolean[N], 0, 0, 0, 0, 0);
		
		if(answer == Integer.MAX_VALUE) {
			System.out.println(-1);
			
		} else {
			System.out.println(answer);
			System.out.println(sb);
		}
		
	}
	
	private static void makeDiet(int index, boolean[] selected, int pro, int carbo, int fatty, int vit, int money) {
		if(index == N) {
			if(protein <= pro && carbohydrate <= carbo && fat <= fatty && vitamin <= vit) {
				if(answer > money) {
					answer = money;
					sb = new StringBuilder();
					for(int i = 0; i < N; i++) {
						if(selected[i]) {
							sb.append(i + 1).append(" ");
						}
					}
				} else if(answer == money) {
					StringBuilder temp = new StringBuilder();
					for(int i = 0; i < N; i++) {
						if(selected[i]) {
							temp.append(i+1).append(" ");
						}
					}
					
					if(sb.toString().compareTo(temp.toString()) > 0) {
						sb = temp;
					}
				}
			}
		return;
		} else {
			selected[index] = true;
			makeDiet(index + 1, selected, 
					pro + ingredient[index][0],
					carbo + ingredient[index][1], 
					fatty + ingredient[index][2], 
					vit + ingredient[index][3],
					money + ingredient[index][4]);
			
			selected[index] = false;
			makeDiet(index + 1, selected, pro, carbo, fatty, vit, money);
		}
	}


}
