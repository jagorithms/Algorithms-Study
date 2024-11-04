import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int N;
    static int result = 0;
    static String[] arr;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
        arr = new String[N];
        for (int i = 0; i < N; i++) {
            arr[i] = br.readLine();
        }
        Arrays.sort(arr, Comparator.comparingInt(String::length));

        for (int i = 0; i < N; i++) {
            boolean flag = true;
            for (int j = i+1; j < N; j++) {
                if(arr[j].startsWith(arr[i])){
                    flag = false;
                    break;
                }
            }
            if(flag){
                result+=1;
            }
        }
        System.out.println(result);
    }
}
