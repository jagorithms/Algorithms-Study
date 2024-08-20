//메모리: 37484 KB, 시간: 292 ms
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashMap;

class Main
{
	static char[][] graph = new char[5][5];
	static int visit = 0;
	static int result = 0;
	
	static int[][] dydx = new int[][] {{1,0},{-1,0},{0,1},{0,-1}};
	static HashMap<Integer, Integer> hm = new HashMap();
	
	public static void main(String args[]) throws Exception
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		for (int i = 0; i < 5; i++) {
			graph[i] = br.readLine().toCharArray();
		}
		
		dfs(0,0);
		System.out.println(result);
	}
	
	public static void dfs(int Ycount, int count) {
		//System.out.println(y + "," + x + " : " + count + ", " + Ycount);
		
		if(Ycount>=4) {
			return;
		}
		if(count>=7) {
			if(hm.containsKey(visit)) {
				return;
			}
			hm.put(visit, 0);
			result+=1;
			return;
		}
		
		for(int y = 0; y<5; y++) {
			for(int x = 0; x<5; x++) {
				if((visit & 1<<(5*y+x))==0 && check(y, x, count)) {
					visit |= 1<<(5*y+x);
					if(graph[y][x]=='Y') {
						dfs(Ycount+1,count+1);
					}
					else {
						dfs(Ycount,count+1);
					}
					visit ^= 1<<(5*y+x);
				}
			}
		}
	}
	public static boolean check(int y,int x, int count) {
		if(count==0)
			return true;
		for(int[] dyx : dydx) {
			int ny = y + dyx[0], nx = x + dyx[1];
			if(0<=ny && ny<5 && 0<=nx && nx<5) {
				if((visit & 1<<(5*ny+nx))!=0) {
					return true;
				}
			}
		}
		return false;
	}
}
