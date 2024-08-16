import java.util.*;
import java.io.*;

public class Main {
    static int count = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        ArrayList<Node> students = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            char[] input = br.readLine().toCharArray();
            for (int j = 0; j < 5; j++) {
                students.add(new Node(i, j, input[j]));
            }
        }

        makeCombinations(students, 0, 0, new boolean[students.size()], new Node[7]);
        sb.append(count);
        bw.write(sb.toString());
        bw.flush();
        br.close();
    }

    private static void makeCombinations(ArrayList<Node> students, int depth, int start, boolean[] visited, Node[] result) {
        if (depth == 7) {
            if (satisfiesCondition(result)) {
                count++;
            }
            return;
        }
        for (int i = start; i < students.size(); i++) {
            if (!visited[i]) {
                result[depth] = students.get(i);
                visited[i] = true;
                makeCombinations(students, depth+1, i+1, visited, result);
                visited[i] = false;
            }
        
        }
    }

    private static boolean satisfiesCondition(Node[] result) {
        int sCount = 0;
        for (int i = 0; i < 7; i++) {
            if (result[i].val == 'S') sCount++;
        }
        if (sCount < 4) return false;
        return isConnected(result);
    }

    private static boolean isConnected(Node[] result) {
        int[] dy = {-1, 1, 0, 0};
        int[] dx = {0, 0, -1, 1};

        Set<Node> nodes = new HashSet<>(List.of(result));
        ArrayDeque<Node> stack = new ArrayDeque<>();
        Set<Node> visited = new HashSet<>();
        stack.addLast(result[0]);
        visited.add(result[0]);
        
        while (!stack.isEmpty()) {
            Node curr = stack.pollFirst();
            int y = curr.y;
            int x = curr.x;

            for (int i = 0; i < 4; i++) {
                int ny = y + dy[i];
                int nx = x + dx[i];

                Node n = new Node(ny, nx, '0');
                if (nodes.contains(n) && !visited.contains(n)) {
                    stack.addLast(n);
                    visited.add(n);
                }
            }
        }
        return visited.size() == nodes.size();
    }

    static class Node {
        int y, x;
        char val;
        Node(int y, int x, char val) {
            this.y = y;
            this.x = x;
            this.val = val;
        }

        @Override
        public String toString() {
            return "(" + y + ","+x+")=> "+val+"\t";
        }

        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + y;
            result = prime * result + x;
            return result;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj)
                return true;
            if (obj == null)
                return false;
            if (getClass() != obj.getClass())
                return false;
            Node other = (Node) obj;
            if (y != other.y)
                return false;
            if (x != other.x)
                return false;
            return true;
        }
        
    }

}
