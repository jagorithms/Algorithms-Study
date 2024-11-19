import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int acount = 0;

    public static void main(String[] args) throws Exception {
       String s = br.readLine();
       int N = s.length();
       for(int i = 0; i < N; i++) {
           if(s.charAt(i) == 'a') {
               acount+=1;
           }
       }
        s += s.substring(0,acount);
       int bcount = 0;
       for(int i = 0; i < acount; i++) {
           if(s.charAt(i) == 'b') {
               bcount++;
           }
       }
        int result = bcount;

        for(int i = acount; i < N+acount; i++) {
            //System.out.println(bcount);
            if(s.charAt(i) == 'b') {
                bcount++;
            }
            if(s.charAt(i-acount) == 'b') {
                bcount--;
            }
            result = Math.min(result, bcount);
        }
        System.out.println(result);
    }
}
