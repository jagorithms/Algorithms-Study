import java.util.*;
import java.io.*;

public class Main {

    static Node root;

    static class Node {
        char c;
        Node parent;
        boolean isLeaf = false;
        Map<Character, Node> children;
        int count = 0;

        Node(char c) {
            this.c = c;
            parent = null;
            children = new HashMap<>();
        }

        Node(char c, Node parent) {
            this.c = c;
            this.parent = parent;
            children = new HashMap<>();
        }

        boolean hasChildOf(char c) {
            return this.children.containsKey(c);
        }

        Node getChildOf(char c) {
            return this.children.get(c);
        }
    }
    
    static String createTrie(String name) {
        StringBuilder output = new StringBuilder();
        String ans = "";
        Node current = root;
        
        boolean success = false;
        for (char c : name.toCharArray()) {
            output.append(c);
            if (!current.hasChildOf(c)) {
                if (!success) {
                    ans = output.toString();
                }
                success = true;
                current.children.put(c, new Node(c, current));
            } 
            current = current.getChildOf(c);
        }
        current.count++;

        if (!success) {
            if (current.count > 1)output.append(current.count);
            ans = output.toString();
        }
        return ans;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine());
        root = new Node('0');
        for (int i = 0; i < N; i++) {
            sb.append(createTrie(br.readLine())).append("\n");
        }
        System.out.append(sb);
    }
}
