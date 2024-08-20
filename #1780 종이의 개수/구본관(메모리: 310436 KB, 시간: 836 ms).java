import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
	static int N;
	static int[][] graph;
	static int[] result;

	static StringBuilder sb = new StringBuilder();
	
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        
        graph = new int[N][N];
        result = new int[3];
        
        for(int y=0; y<N; y++) {
        	st = new StringTokenizer(br.readLine());
        	for(int x=0; x<N; x++) {
        		graph[y][x] = Integer.parseInt(st.nextToken())+1;
            }
        }
        
        sol(0,0,N);
        
        sb.append(result[0]+ "\n" + result[1]+ "\n" + result[2]+ "\n");
        
        System.out.println(sb);
    }    
    public static void sol(int ny, int nx, int n) {
    	if(check(ny,nx,n)) {
    		result[graph[ny][nx]] +=1;
    		return;
    	}
    	
    	if(n<=3) {
    		for(int y=ny; y<ny+3; y++) 
             	for(int x=nx; x<nx+3; x++)
             		result[graph[y][x]] +=1;
    		return;
    	}
    	
    	for(int y=ny; y<ny+n; y+=n/3) {
         	for(int x=nx; x<nx+n; x+=n/3) {
         		sol(y,x,n/3);
         	}
    	}
    }
    
    public static boolean check(int ny, int nx, int n) {
    	int num = graph[ny][nx];
    	 for(int y=ny; y<ny+n; y++) {
         	for(int x=nx; x<nx+n; x++) {
         		if(graph[y][x] != num)
         			return false;
             }
         }
    	
    	return true;
    }
}
