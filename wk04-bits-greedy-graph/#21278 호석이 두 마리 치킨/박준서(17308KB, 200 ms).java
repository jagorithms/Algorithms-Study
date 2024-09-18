import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static int N, M;
	private static int[][] dist;
	private static int chicken_1, chicken_2;
	private static int minTotal = Integer.MAX_VALUE;
	public static void main(String[] args) throws IOException {
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		dist = new int[N + 1][N + 1];
		for(int i = 1; i <= N; i++) {
			Arrays.fill(dist[i], Integer.MAX_VALUE);
			dist[i][i] = 0;
		}
		
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
		
			dist[a][b] = 1;
			dist[b][a] = 1;
			
		}
		
        for (int k = 1; k <= N; k++) {
            for (int i = 1; i <= N; i++) {
                for (int j = 1; j <= N; j++) {
                    if (dist[i][k] != Integer.MAX_VALUE && dist[k][j] != Integer.MAX_VALUE) {
                        if (dist[i][j] > dist[i][k] + dist[k][j]) {
                            dist[i][j] = dist[i][k] + dist[k][j];
                        }
                    }
                }
            }
        }
        
        findChicken();
        System.out.println(chicken_1 + " " + chicken_2 + " " + minTotal);
	
	}
	
	private static void findChicken() {
		for(int i = 1; i <= N; i++) {
			for(int j = i+1; j <= N; j++) {
				int totalDis = 0;
				
				for(int k = 1; k <= N; k++) {
					if(k == i || k== j) continue;
					int shortest = Math.min(dist[k][i], dist[k][j]);
					totalDis += shortest * 2;
				}
				
				if(totalDis < minTotal) {
					minTotal = totalDis;
					chicken_1 = i;
					chicken_2  = j;
				}
			}
		}
	}

}
