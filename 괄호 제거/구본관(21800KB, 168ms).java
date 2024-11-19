import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static Set<String> result = new HashSet<>();

    static String input;

    public static void main(String[] args) throws Exception {
        input = br.readLine();

        dfs(new StringBuilder(), 0, new ArrayDeque<>());

        List<String> list = new ArrayList<>(result);
        Collections.sort(list);

        for (int i = 1; i < list.size(); i++) {
            sb.append(list.get(i)).append("\n");
        }

        System.out.println(sb);
    }

    public static void dfs(StringBuilder stringBuilder, int index, Deque<Character> dq) {
        if(index == input.length()) {
            result.add(stringBuilder.toString());
            return;
        }
        char c = input.charAt(index);

        if(c != '(' && c != ')') {
            stringBuilder.append(input.charAt(index));
            dfs(stringBuilder, index + 1, dq);
            return;
        }

        if(c==')'){
            char p = dq.pollLast();
            if(p=='('){
                stringBuilder.append(')');
            }
            dfs(stringBuilder, index + 1, dq);
            return;
        }

        Deque<Character> ndq = new ArrayDeque<>(dq);
        StringBuilder newStringBuilder = new StringBuilder(stringBuilder);

        stringBuilder.append(c);
        dq.offerLast(c);
        ndq.offerLast('0');

        dfs(stringBuilder, index + 1, dq);
        dfs(newStringBuilder, index + 1, ndq);
    }
}
