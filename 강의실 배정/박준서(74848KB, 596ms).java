import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static class Lecture implements Comparable<Lecture> {
        int start, end;
        public Lecture(int start, int end) {
            this.start = start;
            this.end = end;
        }
        @Override
        public int compareTo(Lecture o) {
            return this.start - o.start;
        }
    }
    private static int N;
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static PriorityQueue<Lecture> lectures = new PriorityQueue<Lecture>();
    private static PriorityQueue<Integer> endTimes = new PriorityQueue<>();
    private static int answer = 1;
    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            lectures.add(new Lecture(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }
        makeLecture();
        System.out.println(answer);
    }

    private static void makeLecture() {
        Lecture lecture = lectures.poll();
        endTimes.add(lecture.end);
        while(!lectures.isEmpty()) {
            Lecture temp = lectures.poll();
            if(temp.start >= endTimes.peek()) {
                endTimes.poll();
            } else {
                answer++;
            }
            endTimes.add(temp.end);
        }
    }
}
