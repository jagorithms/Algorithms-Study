import java.util.*;
import java.io.*;

public class 강희진 {
    private static final char OPENING_BRACKET = '<';
    private static final char CLOSING_BRACKET = '>';
    private static final char WHITESPACE = ' ';

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        char[] input = br.readLine().toCharArray();
        Stack<Character> stack = new Stack<>();
        int idx = 0;
        while (idx < input.length) {
            if (input[idx] == OPENING_BRACKET) {
                while (input[idx] != CLOSING_BRACKET) {
                    sb.append(input[idx++]);
                }
                sb.append(input[idx]);
                idx++;

            } else if (input[idx] == WHITESPACE) {
                while (input[idx] == WHITESPACE) {
                    sb.append(input[idx++]);
                }
            } else {
                while (idx < input.length && input[idx] != OPENING_BRACKET && input[idx] != WHITESPACE) {
                    stack.push(input[idx++]);
                }
                while (!stack.isEmpty()) {
                    sb.append(stack.pop());
                }
            }
        }
        System.out.append(sb);
    }
}