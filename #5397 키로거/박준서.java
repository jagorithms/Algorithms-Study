package august.three;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class keyLogger {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        // - : 백스페이스
        // 커서 바로 앞에 글자가 존재하면 그 글자를 지운다
        // > < : 오른쪽 왼쪽으로 한 칸 씩
        int T = Integer.parseInt(br.readLine());
        for(int i = 0; i < T; i++){
            StringBuilder sb = new StringBuilder();
            String pwd = br.readLine();
            Stack<Character> ans = guessPwd(pwd);
            for(Character ele : ans){
                sb.append(ele);
            }
            System.out.println(sb);

        }

    }

    private static Stack<Character> guessPwd(String pwd){
        Stack<Character> left = new Stack<>();
        Stack<Character> right = new Stack<>();
        for(int i = 0; i < pwd.length(); i++) {
            if (pwd.charAt(i) == '>' && !right.isEmpty()) {
                left.push(right.pop());
                continue;
            } else if (pwd.charAt(i) == '<' && !left.isEmpty()) {
                right.push(left.pop());
                continue;
            } else if (pwd.charAt(i) == '-' && !left.isEmpty()) {
                left.pop();
                continue;
            } else if(isPossible(pwd.charAt(i))){
                left.push(pwd.charAt(i));
            }
        }
        while(!right.isEmpty()){
            left.push(right.pop());
        }

        return left;
    }

    private static boolean isPossible(Character pwd){
        return Character.isUpperCase(pwd) || Character.isDigit(pwd) || Character.isLowerCase(pwd);
    }

}
