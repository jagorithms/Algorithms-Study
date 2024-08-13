package eleven;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class editer {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static Deque<Character> left = new LinkedList<>();
    private static Deque<Character> right = new LinkedList<>();

    public static void main(String[] args) throws IOException {
        String input = br.readLine();
        for (int i = 0; i < input.length(); i++) {
            left.add(input.charAt(i));
        }
        int M = Integer.parseInt(br.readLine());
        for (int i = 0; i < M; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            String command = st.nextToken();
            switch (command) {
                case "P":
                    if(st.hasMoreTokens()) {
                        String ch = st.nextToken();
                        left.add(ch.charAt(0));
                    }
                    break;
                case "L":
                    if(!left.isEmpty()) {
                        right.add(left.pollLast());
                    }
                    break;

                case "D":
                    if(!right.isEmpty()) {
                        left.add(right.pollLast());
                    }
                    break;

                case "B":
                    if(!left.isEmpty()) {
                        left.pollLast();
                    }
                    break;
            }
        }
        if(!right.isEmpty()){
            while(!right.isEmpty()){
                left.add(right.pollLast());
            }
        }

        StringBuilder sb = new StringBuilder();
        for(Character ch : left){
            sb.append(ch);
        }
        System.out.println(sb);
    }
}
