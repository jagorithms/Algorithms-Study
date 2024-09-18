import java.io.*;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;


class Person implements Comparable<Person>{
    public int startTime;
    public int endTime;

    public Person(int startTime, int endTime) {
        this.startTime = startTime;
        this.endTime = endTime;
    }


    @Override
    public int compareTo(Person o) {
        return Integer.compare(this.startTime, o.startTime);
    }

    @Override
    public String toString() {
        return "Person{" +
                "startTime=" + startTime +
                ", endTime=" + endTime +
                '}';
    }
}

public class three12764 {
    public void solution() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder builder = new StringBuilder();
        int n = Integer.parseInt(reader.readLine());
        PriorityQueue<Person> people = new PriorityQueue<>();

        for (int i = 0; i < n; i++) {
            StringTokenizer infoToken = new StringTokenizer(reader.readLine());
            people.add(new Person(Integer.parseInt(infoToken.nextToken()),
                    Integer.parseInt(infoToken.nextToken())));
        }

        int size = 0;
        int[][] seats = new int[n][2];
        int[] seatNum = new int[n];

        for (int i = 0; i < n; i++) {
            boolean isFlag = false;
            Person p = people.poll();

            for (int j = 0; j < size; j++) {
                if (seats[j][1] < p.startTime) { // 지난 사람의 종료 시간이 이번 사람의 시작 시간보다 이전이라면
                    seats[j][0] = p.startTime;
                    seats[j][1] = p.endTime; // 그 자리 차지
                    seatNum[j]++;
                    isFlag = true; // 자리 앉았다 표시
                    break;
                }
            }

            if (!isFlag) {// 아직 못 앉았으면 새로운 자리에
                seats[size][0] = p.startTime;
                seats[size][1] = p.endTime;
                seatNum[size]++;
                size++;

            }
        }
        builder.append(size).append('\n');
        for (int num : seatNum) {
            if (num == 0) break;
            else builder.append(num).append(" ");
        }
        writer.write(builder.toString());
        writer.flush();
    }

    public static void main(String[] args) throws IOException {
        new three12764().solution();
    }

}
