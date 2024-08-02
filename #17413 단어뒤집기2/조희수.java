import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class three17413 {
    public void solution() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder builder = new StringBuilder();
        String input = reader.readLine();
        Stack<Character> stack = new Stack<>();

        boolean isTag = false;

        for (int i = 0; i < input.length(); i++) {
            char current = input.charAt(i);
            if (current == '<') {
                while (!stack.isEmpty()) {
                    builder.append(stack.pop());
                }
                builder.append(current);
                isTag = true;
                continue;
            } else if (current == '>') {
                builder.append(current);
                isTag = false;
                continue;
            } else if (current == ' ') {
                while (!stack.isEmpty()) {
                    builder.append(stack.pop());
                }
                builder.append(current);
                continue;
            }

            if (isTag) builder.append(current);
            else {
                stack.push(current);
            }
        }
        while (!stack.isEmpty()) {
            builder.append(stack.pop());
        }
        writer.write(builder.toString());
        writer.flush();
    }

    public static void main(String[] args) throws IOException {
        new three17413().solution();
    }

}
