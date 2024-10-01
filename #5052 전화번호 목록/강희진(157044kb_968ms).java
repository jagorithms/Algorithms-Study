import java.util.*;
import java.io.*;

public class Main {

    static class Node {
        int value;
        Node parent;
        List<Node> children;

        Node(int value) {
            this.value = value;
            this.parent = null;
            this.children = new ArrayList<>();
        }

        Node(int value, Node parent) {
            this.value = value;
            this.parent = parent;
            this.children = new ArrayList<>();
        }

        boolean hasChildOf(int val) {
            for (Node child : children) {
                if (child.value == val)
                    return true;
            }
            return false;
        }

        Node getChildOf(int val) {
            for (Node child : children) {
                if (child.value == val) {
                    return child;
                }
            }
            return null;
        }

        @Override
        public String toString() {
            StringBuilder output = new StringBuilder();
            output.append("Node[").append(this.value).append("], children: {");
            for (Node child : children) {
                output.append(child.value).append(", ");
            }
            output.append("}");
            return output.toString();
        }

        
    }

    static Node root;
    
    static boolean createTrie(String number) {
        Node current = root;


        for (int i = 0; i < number.length(); i++) {
            int n = number.charAt(i) - '0';


            if (current.hasChildOf(-10)) {
                return false;
            } else if (current.getChildOf(n) == null) {
                Node newNode = new Node(n, current);
                current.children.add(newNode);
            } 
            current = current.getChildOf(n);
        }
        current.children.add(new Node(-10));
        return true;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            int n = Integer.parseInt(br.readLine());
            String[] numbers = new String[n];

            root = new Node(-1);
            for (int i = 0; i < n; i++) {
                numbers[i] = br.readLine();
            }
            Arrays.sort(numbers);
            boolean success = true;
            for (int i = 0; i < n; i++) {
                if (!createTrie(numbers[i])) {
                    success = false;
                }
            }
            sb.append(success ? "YES" : "NO").append("\n");
        }
        System.out.append(sb);
    }
}
