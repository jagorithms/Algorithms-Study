import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        //StringTokenizer stringTokenizer = new StringTokenizer(br.readLine());

        ArrayDeque<Character> frontdq = new ArrayDeque();
        ArrayDeque<Character> backdq = new ArrayDeque();

        String s = br.readLine();
        for (int i = 0; i < s.length(); i++) {
            frontdq.offerLast(s.charAt(i));
        }

        int n = Integer.parseInt(br.readLine());
        for (int i = 0; i < n; i++) {
            s = br.readLine();
            if(s.equals("L")) {
                if (!frontdq.isEmpty()){
                    backdq.offerFirst(frontdq.pollLast());
                    }
            }
            else if(s.equals("D")){
                if(!backdq.isEmpty()) {
                    frontdq.offerLast(backdq.pollFirst());
                }
            }
            else if(s.equals("B")) {
                if (!frontdq.isEmpty()) {
                    frontdq.pollLast();
                }
            }
            else {
                s.split(" ");
                    frontdq.offerLast(s.split(" ")[1].charAt(0));
                }
        }

        for(char c : frontdq){
            sb.append(c);
        }

        for(char c : backdq){
            sb.append(c);
        }
        System.out.println(sb);
    }
}
