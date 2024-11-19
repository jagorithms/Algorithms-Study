import java.util.*;
import java.io.*;

public class Main {

    static final char OPENING = '(', CLOSING = ')';
    static Set<String> uniqueExpressions = new HashSet<>();
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        String word = br.readLine();
        
        List<int[]> pairs = getParenthesesPairs(word);

        generateExpressions(word, pairs, new boolean[pairs.size()], 0);

        List<String> result = new ArrayList<>(uniqueExpressions);
        Collections.sort(result);

        for (String expr : result) {
            System.out.println(expr);
        }
    }

    private static List<int[]> getParenthesesPairs(String word) {
        Stack<Integer> stack = new Stack<>();
        List<int[]> pairs = new ArrayList<>();

        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (c == OPENING) {
                stack.push(i);
            } else if (c == CLOSING && !stack.isEmpty()) {
                pairs.add(new int[]{stack.pop(), i});
            }
        }
        return pairs;
    }

    private static void generateExpressions(String word, List<int[]> pairs, boolean[] toRemove, int index) {
        if (index == pairs.size()) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < word.length(); i++) {
                boolean shouldRemove = false;
                for (int j = 0; j < pairs.size(); j++) {
                    if (toRemove[j] && (i == pairs.get(j)[0] || i == pairs.get(j)[1])) {
                        shouldRemove = true;
                        break;
                    }
                }
                if (!shouldRemove) {
                    sb.append(word.charAt(i));
                }
            }
            if (sb.length() < word.length()) { 
                uniqueExpressions.add(sb.toString());
            }
            return;
        }

        generateExpressions(word, pairs, toRemove, index + 1);

        toRemove[index] = true;
        generateExpressions(word, pairs, toRemove, index + 1);
        toRemove[index] = false;
    }
}
