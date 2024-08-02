import java.io.*;
import java.util.LinkedList;
import java.util.ListIterator;

public class two5397 {
    public void solution() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder builder = new StringBuilder();
        int testNum = Integer.parseInt(reader.readLine());
        for (int test = 0; test < testNum; test++) {
            String input = reader.readLine();
            LinkedList<Character> password = new LinkedList<>();
            ListIterator<Character> list = password.listIterator();
            for (int i = 0; i < input.length(); i++) {
                char currentChar = input.charAt(i);
                switch (currentChar) {
                    case '<':
                        if (list.hasPrevious()) {
                            list.previous();
                        }
                        break;
                    case '>':
                        if (list.hasNext()) {
                            list.next();
                        }
                        break;
                    case '-':
                        if (list.hasPrevious())  {
                            list.previous();
                            list.remove();
                        }
                        break;
                    default:
                        list.add(currentChar);
                }
            }
            for(char ch : password) {
                builder.append(ch);
            }
            builder.append('\n');
        }
        writer.write(builder.toString());
        writer.close();
    }

    public static void main(String[] args) throws IOException {
        new two5397().solution();
    }
}
