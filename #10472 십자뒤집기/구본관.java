import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
	static int[][] dydx;
	static List<int[]> point;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        
        dydx = new int[][] {{0,0},{-1,0},{1,0},{0,-1},{0,1}};
        
        point = new ArrayList<>();
        int index=0;
        for(int y=0; y<3; y++) {
        	for(int x=0; x<3; x++) {
        		point.add(new int[] {y,x});
        	}
        }
        
        HashMap<Integer, Integer> dic = new HashMap<>();
        
        Deque<Integer> dq = new ArrayDeque<>();
        Deque<Integer> dq2 = new ArrayDeque<>();
        dq2.offer(0);
        int num = 0;
        
        int nbit = paint(0,1,0);
        
        while(!dq2.isEmpty()) {
        	dq = dq2;
        	dq2 = new ArrayDeque<>();
        	while(!dq.isEmpty()) {
        		int bit = dq.pollFirst();
        		
        		if(dic.containsKey(bit)) {
        			continue;
        		}
        		dic.put(bit, num);
        		
        		for(int i=0; i<9; i++) {
        			int y = point.get(i)[0];
        			int x = point.get(i)[1];
        			
        			nbit = paint(bit,y,x);
        			
        			dq2.add(nbit);
        		}
        	}
        	num+=1;
        }
        
        int n = Integer.parseInt(br.readLine());
        for(int i=0; i<n; i++) {
        	char[] str = new char[9];
        	index = 0;
        	for(int t=0; t<3; t++) {
        		String s = br.readLine();
        		for(int x=0; x<3; x++) {
        			if(s.charAt(x)=='*') {
        				str[index++] = '1';
        			}else {
        				str[index++] = '0';
        			}
        		}
        	}
        	sb.append(dic.get(Integer.parseInt(new String(str), 2)) + "\n");
        }

        System.out.println(sb);
    }
    static int paint(int bit, int y, int x) {
    	for(int[] yx : dydx) {
    		int ny = y + yx[0];
    		int nx = x + yx[1];
    		
    		if(0<=nx && nx<3 && 0<=ny && ny<3) {
    			for(int i=0; i<9; i++) {
    				if(Arrays.equals(point.get(i), new int[] {ny, nx})) {
    					bit = bit ^ 1<<(8-i);
    					break;
    				}
    			}
    		}
    	}
    	return bit;
    }
}
