import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int L;
    static int C;
    static boolean[] visit;
    static char[] li;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        L = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());

        li = new char[C];
        for (int i = 0; i < C; i++) {
            li[i] = st.nextToken().charAt(0);
        }

        Arrays.sort(li);

        visit = new boolean[C];

        dfs(0, 0);

        System.out.println(sb);
    }
    public static void dfs(int index, int count){
        //System.out.println(index + " " + count);
        if(count>=L){
            if(check()){
                for (int i = 0; i < C; i++){
                    if(visit[i]){
                        sb.append(li[i]);
                    }
                }
                sb.append("\n");
            }
            return;
        }
        if(index>=C)
            return;
        visit[index] = true;
        dfs(index + 1, count+1);
        visit[index] = false;
        dfs(index + 1, count);
    }
    public static boolean check(){
        int mnum = 0, znum = 0;
        for (int i = 0; i < C; i++) {
            if(visit[i]){
                if(li[i]=='a' || li[i]=='e' || li[i]=='i' || li[i]=='o' || li[i]=='u'){
                    mnum+=1;
                }else{
                    znum+=1;
                }
            }
        }
        if(mnum>=1 && znum>=2)
            return true;
        return false;
    }
}
