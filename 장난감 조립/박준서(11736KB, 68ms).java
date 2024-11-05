import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static class Node {
        int num, count;
        Node(int num, int count) {
            this.num = num;
            this.count = count;
        }
    }


    private static int N,M;
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static int[] link;
    private static ArrayList<Node>[] parts;
    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        link = new int[N + 1];
        parts = new ArrayList[N + 1];
        int[] find = new int[N + 1];

        for(int i = 1; i <= N; i++) {
            parts[i] = new ArrayList<>();
        }

        for(int i = 0; i < M; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int count = Integer.parseInt(st.nextToken());

            parts[a].add(new Node(b, count)); // 필요한 기본 부품 넣기
            link[b]++;
            find[a]++;
        }

        int[] result = topologicalSort();
        for(int i = 1; i <= N; i++) {
            if(find[i] == 0) System.out.println(i + " " + result[i]);
        }

    }

    private static int[] topologicalSort() {
        Queue<Node> queue = new LinkedList<>();
        queue.add(new Node(N, 1));
        int[] counters = new int[N + 1]; // 타고 내려가면서 저장할 거임
        counters[N] = 1;

        while(!queue.isEmpty()) {
            Node temp = queue.poll();
            for(int i = 0; i < parts[temp.num].size(); i++) {
                Node part= parts[temp.num].get(i);
                counters[part.num] += counters[temp.num] * part.count;
                link[part.num]--;
                if(link[part.num] == 0) queue.add(new Node(part.num, counters[part.num]));
            }
        }
        return counters;
    }


}
