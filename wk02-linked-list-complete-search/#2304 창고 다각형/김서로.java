import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	
	private static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	private static StringTokenizer tokens;
	
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		int N = Integer.parseInt(input.readLine());
		
		List<int[]> pillars = new ArrayList<>();
		
		int maxH = 0;
		int maxL = 0;
		
        // 기둥 위치, 높이 입력 받기
		for (int i=0; i < N; i++) {
			tokens = new StringTokenizer(input.readLine());
			int L = Integer.parseInt(tokens.nextToken());
			int H = Integer.parseInt(tokens.nextToken());
			
			if (maxH < H) {
				maxH = H;
				maxL = L;
			}
			
			pillars.add(new int[] {L, H});
		}
		
		// 기둥들을 왼쪽부터 정렬
		Collections.sort(pillars, Comparator.comparingInt(pillar -> pillar[0]));
		
		int prevL = 0;
        int prevH = 0;
        int sum = maxH;

        // 최고 기둥을 기준으로 오른쪽 넓이 구하기
        while (true) {
            int[] pillar = pillars.remove(pillars.size() - 1);
            int L = pillar[0];
            int H = pillar[1];
            
            if (H >= prevH) {
                sum += prevH * (prevL - L);
                prevH = H;
                prevL = L;
            }
            
            if (L == maxL) {
                pillars.add(new int[]{L, H});
                break;
            }
        }

        prevL = 0;
        prevH = 0;

        // 최고 기둥을 기준으로 왼쪽 넓이 구하기
        while (!pillars.isEmpty()) {
            int[] pillar = pillars.remove(0);
            int L = pillar[0];
            int H = pillar[1];
            
            if (H >= prevH) {
                sum += prevH * (L - prevL);
                prevH = H;
                prevL = L;
            }
        }

        System.out.println(sum);
	}

}
