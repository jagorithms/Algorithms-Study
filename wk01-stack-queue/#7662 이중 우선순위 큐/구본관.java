import java.util.*;
import java.io.*;

public class Main {	
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		long k = Long.parseLong(br.readLine());
		
		for(int i=0 ;i<k; i++) {
			long M = Long.parseLong(br.readLine());
			
			PriorityQueue<Long> minhq = new PriorityQueue();
			PriorityQueue<Long> maxhq = new PriorityQueue();
			
			HashMap<Long, Long> dic = new HashMap<>();
			
			
			for(int t=0; t<M; t++) {
				st = new StringTokenizer(br.readLine());
				String inputs = st.nextToken();
				long inputi = Long.parseLong(st.nextToken());
				
				if (inputs.equals("I")) {
					minhq.offer(inputi);
					maxhq.offer(-inputi);
					
					if(dic.containsKey(inputi)) {
						dic.replace(inputi, dic.get(inputi)+1);
					}else {
						dic.put(inputi, 1l);
					}
				}else {
					if(inputi == 1) {
						long maxnum = 0;
						while(!maxhq.isEmpty()) {
							maxnum = -maxhq.poll();
							if(dic.containsKey(maxnum) && dic.get(maxnum)>0) {
								dic.replace(maxnum, dic.get(maxnum)-1);
								break;
							}
						}
					}else {
						long minnum = 0;
						while(!minhq.isEmpty()) {
							minnum = minhq.poll();
							if(dic.containsKey(minnum) && dic.get(minnum)>0) {
								dic.replace(minnum, dic.get(minnum)-1);
								break;
							}
						}
					}
				}
			}
			
			long maxnum = 0;
			while(!maxhq.isEmpty()) {
				maxnum = -maxhq.poll();
				if(dic.containsKey(maxnum) && dic.get(maxnum)>0) {
					break;
				}
			}
			
			long minnum = 0;
			while(!minhq.isEmpty()) {
				minnum = minhq.poll();
				if(dic.containsKey(minnum) && dic.get(minnum)>0) {
					break;
				}
			}
			if(dic.containsKey(maxnum) && dic.get(maxnum)>0)
				sb.append(maxnum + " " + minnum + "\n");
			else
				sb.append("EMPTY\n");
			
			//System.out.println(dic);
		}		
		System.out.println(sb);
	}
}
