import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class 김서로 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(input.readLine());
		
		for (int i=0; i<T; i++) {
			String password = input.readLine();
			Stack<Character> leftStack = new Stack<>();
            Stack<Character> rightStack = new Stack<>();
            
            for (char c: password.toCharArray()) {
            	if (Character.isLetterOrDigit(c)) {
            		leftStack.add(c);
            	} else if (!(leftStack.isEmpty()) && c == '<') {
            		rightStack.add(leftStack.pop());
            	} else if (!(rightStack.isEmpty()) && c == '>') {
            		leftStack.add(rightStack.pop());
            	} else if (!(leftStack.isEmpty()) && c == '-') {
            		leftStack.pop();
            	}
            }
            
            while (!(rightStack.isEmpty())) {
            	leftStack.add(rightStack.pop());
            }
            
            StringBuilder output = new StringBuilder();
            
            for (char c: leftStack) {
            	output.append(c);
            }
            
            System.out.println(output);
		}
	}
}
