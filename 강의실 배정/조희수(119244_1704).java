import java.io.*;
import java.util.*;

/*
 @author ranuinclulus
 @since 2024.10.18
 @link https://www.acmicpc.net/problem/1202
 @timecomplex
 @performance
 @category
 @note
 */
public class Main {
    class Jewerly {
        int weight;
        int price;

        Jewerly(int weight, int price) {
            this.weight = weight;
            this.price = price;
        }
    }
    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder builder = new StringBuilder();
    static StringTokenizer tokenizer;
    static int n, k;
    static long answer;
    static Jewerly[] jewerlies;
    static int[] backpacks;

    public void solution() throws IOException {
        tokenizer = new StringTokenizer(reader.readLine());
        n = Integer.parseInt(tokenizer.nextToken());
        k = Integer.parseInt(tokenizer.nextToken());

        jewerlies = new Jewerly[n];
        backpacks = new int[k];

        for (int i = 0; i < n; i++) {
            tokenizer = new StringTokenizer(reader.readLine());
            jewerlies[i] = new Jewerly(Integer.parseInt(tokenizer.nextToken()), Integer.parseInt(tokenizer.nextToken()));
        }

        Arrays.sort(jewerlies, (o1, o2) -> {
            if (o1.weight == o2.weight) return -Integer.compare(o1.price, o2.price);
            else return Integer.compare(o1.weight, o2.weight);
        });

        for (int i = 0; i < k; i++) {
            backpacks[i] = Integer.parseInt(reader.readLine());
        }

        Arrays.sort(backpacks);

        PriorityQueue<Integer> candidates = new PriorityQueue<>(Comparator.reverseOrder());
        for(int i = 0, j = 0; i < k; i++) {
            while (j < n && jewerlies[j].weight <= backpacks[i]) {
                candidates.add(jewerlies[j++].price);
            }
            if (!candidates.isEmpty()) {
                answer += candidates.poll();
            }
        }
        builder.append(answer);
        writer.write(builder.toString());
        writer.flush();
    }

    public static void main(String[] args) throws IOException {
        new Main().solution();
    }
}
