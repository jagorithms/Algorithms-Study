import java.util.*;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scan = new Scanner(System.in);
		
		int N = scan.nextInt();
		scan.nextLine();
		
		StringBuilder sb = new StringBuilder();
		
		PriorityQueue<int []> personpq = new PriorityQueue<>(
				new Comparator<int []>() {
					public int compare(int[] o1, int[] o2) {
						return o1[0]-o2[0];
					}
				}
			);
		
		PriorityQueue<Integer> computerpq = new PriorityQueue<>();
		int[] computerli = new int[1000000];
		int computersize = 0;
		
		for(int i=0; i<N; i++) {
			personpq.offer(new int[] {scan.nextInt(),scan.nextInt()});
		}
		
		while(!personpq.isEmpty()) {
			int[] temp = personpq.poll();
			int a = temp[0], b = temp[1];
			
			if(b>=0) {
				int c = 0;
				if(!computerpq.isEmpty()) {
					c = computerpq.poll();
				}else {
					c = ++computersize;
				}
				computerli[c]++;
				personpq.offer(new int[] {b,-c});
			}else {
				computerpq.offer(-b);
			}
		}
		sb.append(computersize+"\n");
		for(int i=1; i<computersize+1; i++) {
			sb.append(computerli[i] + " ");
		}
		System.out.println(sb);
	}
}
