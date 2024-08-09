import java.util.*;

import java.io.*;

public class Main {
    private static final char MOVE_LEFT = 'L', MOVE_RIGHT = 'D', BACKSPACE = 'B', TYPE ='P';
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        char[] input = br.readLine().toCharArray();
        int N = Integer.parseInt(br.readLine());
        char[][] cmd = new char[N][2];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            cmd[i][0] = st.nextToken().charAt(0);
            if (cmd[i][0] == 'P') cmd[i][1] = st.nextToken().charAt(0);
        }

        ArrayDeque<Character> left = new ArrayDeque<>();
        for (int i = 0; i < input.length; i++) {
            left.addLast(input[i]);
        }
        ArrayDeque<Character> right = new ArrayDeque<>();

        for (int i = 0; i < N; i++) {
            char expression = cmd[i][0];
            switch (expression) {
                case MOVE_LEFT:
                    if (!left.isEmpty()) {
                        char tmp = left.pollLast();
                        right.addFirst(tmp);
                    }
                    break;
                case MOVE_RIGHT:
                    if (!right.isEmpty()) {
                        char tmp = right.pollFirst();
                        left.addLast(tmp);
                    }
                    break;
                case BACKSPACE:
                    if (!left.isEmpty()) {
                        left.pollLast();
                    }
                    break;
                case TYPE:
                    left.addLast(cmd[i][1]);
                    break;
            }
        }

        StringBuilder sb = new StringBuilder();
        while (!left.isEmpty()) {
            sb.append(left.pollFirst());
        }
        while (!right.isEmpty()) {
            sb.append(right.pollFirst());
        }
        System.out.append(sb);
    }
}
