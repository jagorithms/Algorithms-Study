import java.io.*;
import java.util.StringTokenizer;

/*
 @author ranuinclulus
 @since 2024.09.20
 @link https://www.acmicpc.net/problem/20040
 @timecomplex
 @performance
 @category
 @note
 */
public class Main {
    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder builder = new StringBuilder();
    static StringTokenizer tokenizer;
    static int n, m, result;
    static int[] parents;

    public void solution() throws IOException {
        tokenizer = new StringTokenizer(reader.readLine());
        n = Integer.parseInt(tokenizer.nextToken());
        m = Integer.parseInt(tokenizer.nextToken());

        parents = new int[n + 1];

        for (int i = 0; i <= n; i++) {
            parents[i] = i;
        }

        for (int i = 1; i <= m; i++) {
            tokenizer = new StringTokenizer(reader.readLine());
            int nodeOne = Integer.parseInt(tokenizer.nextToken());
            int nodeTwo = Integer.parseInt(tokenizer.nextToken());

            int parentOne = find(nodeOne);
            int parentTwo = find(nodeTwo);

            if (parentOne == parentTwo) {
                result = i;
                break;
            } else {
                if (parentOne < parentTwo) parents[parentTwo] = parentOne;
                else parents[parentOne] = parentTwo;
            }
        }
        builder.append(result);
        writer.write(builder.toString());
        writer.flush();
    }



    private int find(int node) {
        if (parents[node] == node) return node;
        else return parents[node] = find(parents[node]);
    }

    public static void main(String[] args) throws IOException {
        new Main().solution();
    }
}
