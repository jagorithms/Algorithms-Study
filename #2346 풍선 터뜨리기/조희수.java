import java.io.*;
import java.util.ArrayDeque;
import java.util.Deque;

public class three2346 {
    static class Balloon {
        int index;
        int next;

        public Balloon(int index, int next) {
            this.index = index;
            this.next = next;
        }
    }
    static int n;
    static Deque<Balloon> deque = new ArrayDeque<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        n = Integer.parseInt(br.readLine());
        String[] inputs = br.readLine().split(" ");
        for (int i = 0; i < n; i++) {
            deque.offer(new Balloon(i + 1, Integer.parseInt(inputs[i])));
        }

        sb.append(1 + " ");
        int moveSize = deque.poll().next;
        while (!deque.isEmpty()) {
            if (moveSize > 0) {
                for (int i = 1; i < moveSize; i++) {
                    deque.offerLast(deque.pollFirst());
                }
            } else {
                for (int i = 1; i <= -moveSize; i++) {
                    deque.offerFirst(deque.pollLast());
                }
            }
            Balloon target = deque.poll();
            sb.append(target.index + " ");
            moveSize = target.next;
        }
        System.out.println(sb);
    }
}
