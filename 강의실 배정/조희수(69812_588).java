import java.io.*;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
 @author ranuinclulus
 @since 2024.10.16
 @link
 @timecomplex
 @performance
 @category
 @note
 */
public class Main {
    class Class implements Comparable<Class>{
        int startTime;
        int endTime;

        public Class(int startTime, int endTime) {
            this.startTime = startTime;
            this.endTime = endTime;
        }

        @Override
        public int compareTo(Class o) {
            if (this.startTime == o.startTime) return Integer.compare(this.endTime, o.endTime);
            return Integer.compare(this.startTime, o.startTime);
        }
    }
    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder builder = new StringBuilder();
    static StringTokenizer tokenizer;
    static int n;
    static PriorityQueue<Class> classes;

    public void solution() throws IOException {
        n = Integer.parseInt(reader.readLine());
        classes = new PriorityQueue<>();

        for (int i = 0; i < n; i++) {
            tokenizer = new StringTokenizer(reader.readLine());
            classes.add(new Class(Integer.parseInt(tokenizer.nextToken()), Integer.parseInt(tokenizer.nextToken())));
        }

        PriorityQueue<Integer> rooms = new PriorityQueue<>();
        while (!classes.isEmpty()) {
            Class now = classes.poll();
            if (!rooms.isEmpty() && rooms.peek() <= now.startTime) {
                rooms.poll();
            }
            rooms.add(now.endTime);
        }
        builder.append(rooms.size());
        writer.write(builder.toString());
        writer.flush();
    }

    public static void main(String[] args) throws IOException {
        new Main().solution();
    }
}
