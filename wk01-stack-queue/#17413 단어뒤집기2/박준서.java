package august.five;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class reverse_word {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        String S = br.readLine();
        StringBuilder sb = new StringBuilder();
        List<String> words = new ArrayList<>();
        boolean isBrace = false;
        for(int i = 0; i < S.length(); i++){
            if(S.charAt(i) == ' ' && !isBrace){
                words.add(sb.reverse().toString());
                words.add(String.valueOf(' '));
                sb.setLength(0);
            } else if(S.charAt(i) == '<'){
                words.add(sb.reverse().toString());
                words.add(String.valueOf('<'));
                sb.setLength(0);
                isBrace = true;
            } else if(S.charAt(i) == '>'){
                words.add(sb.toString());
                sb.setLength(0);
                words.add(String.valueOf('>'));
                isBrace = false;
            } else {
                sb.append(S.charAt(i));
            }
        }
        if(sb.length() > 0){
            words.add(sb.reverse().toString());
        }

        for(String word : words){
            System.out.print(word);
        }
    }
}
