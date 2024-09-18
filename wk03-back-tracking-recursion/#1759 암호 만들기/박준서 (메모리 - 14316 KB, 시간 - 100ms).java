import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static int L, C;
    private static char[] words;
    private static List<char[]> answer = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        L = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        words = new char[C];
        st = new StringTokenizer(br.readLine());
        
        for (int i = 0; i < C; i++) {
            words[i] = st.nextToken().charAt(0);
        }
        
        Arrays.sort(words);
        
        makeCode(0, new char[L], 0);
        
        StringBuilder sb = new StringBuilder();
        for(char[] word : answer){
            for(char c : word){
                sb.append(c);
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }

    private static void makeCode(int depth, char[] chosen, int start){
        if(depth == L){
            if(isValid(chosen)){
                answer.add(chosen.clone());
            }
            return;
        } else {
            for(int i = start; i < C; i++){
                chosen[depth] = words[i];
                makeCode(depth + 1, chosen, i + 1);
            }
        }
    }

    private static boolean isValid(char[] chosen){
        int vowel = 0;
        int consonant = 0;
        for(int i = 0; i < chosen.length; i++){
            if(chosen[i] == 'a' || chosen[i] == 'e' || chosen[i] == 'i' || chosen[i] == 'o' || chosen[i] == 'u'){
                vowel++;
            } else {
                consonant++;
            }
        }

        return consonant >= 2 && vowel >= 1;
    }
}
