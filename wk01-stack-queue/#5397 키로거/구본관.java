import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scan = new Scanner(System.in);
		
		int N = scan.nextInt();
		scan.nextLine();
		
		StringBuilder sb = new StringBuilder();
		
		Deque<Character> front = new ArrayDeque<>();
		Deque<Character> back = new ArrayDeque<>();
		
		for(int i=0; i<N; i++) {
			String s = scan.nextLine();
			
			for(int t=0; t<s.length(); t++) {
				char c = s.charAt(t);
				if(c=='<') {
					if(!front.isEmpty())
						back.push(front.pop());
				}else if(c=='>') {
					if(!back.isEmpty())
						front.push(back.pop());
				}else if(c=='-') {
					if(!front.isEmpty())
						front.pop();
				}else {
					front.push(c);
				}
			}
		
			while(!front.isEmpty()) {
				sb.append(front.pollLast());
			}
			while(!back.isEmpty()) {
				sb.append(back.poll());
			}
			sb.append("\n");
		}
		
		System.out.println(sb);
	}
}
