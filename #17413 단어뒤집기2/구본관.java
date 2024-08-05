import java.util.Scanner;
import java.util.Stack;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scan = new Scanner(System.in);
		Stack<Character> stack = new Stack<>();
		
		String string = scan.nextLine();
		
		StringBuilder sb = new StringBuilder();
		
		int i = 0;
		
		while(i<string.length()) {
			char c = string.charAt(i);
			if(c=='<') {
				while(i<string.length()) {
					c = string.charAt(i);
					sb.append(c);
					if(c=='>')
						break;
					i++;
				}
			}
			else if(c==' ') {
				sb.append(c);
			}
			else {
				while(i<string.length()) {
					c = string.charAt(i);
					if(c=='<' || c==' ') {
						while(!stack.isEmpty()) {
							sb.append(stack.pop());
						}
						i--;
						break;
					}
					stack.add(c);
					i++;
				}
			}
			i++;
		}
		
		while(!stack.isEmpty()) {
			sb.append(stack.pop());
		}
		
		
		System.out.println(sb);
		
	}

}
