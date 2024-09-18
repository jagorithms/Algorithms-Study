import java.io.*;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class four1158 {
    static int n;
    static int k;
    static LinkedList<Integer> list = new LinkedList<>();

    public void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken()) - 1;

        sb.append("<");

        for (int i = 1; i <= n; i++) {
            list.add(i);
        }

        int index = 0;
        while (list.size() > 1) {
            index = (index + k) % list.size();
            sb.append(list.get(index)).append(", ");
            list.remove(index);
        }
        sb.append(list.get(0));
        sb.append(">");
        bw.write(sb.toString());
        bw.flush();
    }

    public static void main(String[] args) throws IOException {
        new four1158().solution();
    }
}
