import java.io.*;
import java.util.*;

public class Main {
    static int flag = 0;;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int M = Integer.parseInt(st.nextToken());
        for (int i =0 ; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            String cmd = st.nextToken();
            int x = 0;
            if (needsParam(cmd)) x = Integer.parseInt(st.nextToken());
            parseCmd(cmd, x);
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
    
    static boolean needsParam(String cmd) {
        if (cmd.equals("all") || cmd.equals("empty")) return false;
        return true;
    }

    static void parseCmd(String cmd, int x) {
        switch (cmd) {
            case "add":
                add(x);
                break;
            case "remove":
                remove(x);
                break;
            case "check":
                if (check(x)) sb.append(1);
                else sb.append(0);
                sb.append("\n");
                break;
            case "toggle":
                toggle(x);
                break;
            case "all":
                all();
                break;
            case "empty":
                empty();
                break;
            default:
                sb.append("UNKNOWN");
        }
    }
    static void add(int x) {
        flag = flag | (1 << x);
    }
    static void remove(int x) {            
        flag = flag & ~(1 << x);
    }
    static boolean check(int x) {
        return (flag & (1 << x)) != 0;
    }
    static void toggle(int x) {
        flag = flag ^ (1 << x);
    }
    static void all() {
        flag = ~0;
    }
    static void empty() {
        flag = 0;
    }
}
