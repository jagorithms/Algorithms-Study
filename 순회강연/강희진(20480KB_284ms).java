import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        List<Job> list = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int price = Integer.parseInt(st.nextToken());
            int day = Integer.parseInt(st.nextToken());
            list.add(new Job(day, price));
        }

        Collections.sort(list, (a, b) -> Integer.compare(b.price, a.price) == 0 ? Integer.compare(a.day, b.day) : Integer.compare(b.price, a.price));

        boolean[] check = new boolean[10001];
        int sum = 0;
        for (int i = 0; i < N; i++) {
            for (int j = list.get(i).day; j > 0; j--) {
                if (!check[j]) {
                    check[j] = true; 
                    sum += list.get(i).price;
                    break;
                }
            }
        }

        System.out.println(sum);


    }

    static class Job {
        int day, price;
        Job(int day, int price) {
            this.day = day;
            this.price = price;
        }
    }
}
