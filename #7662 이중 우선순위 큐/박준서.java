package august.five;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class double_priority_queue {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {

        int T = Integer.parseInt(br.readLine());
        for (int i = 0; i < T; i++) {
            PriorityQueue<Integer> minQue = new PriorityQueue<>();
            PriorityQueue<Integer> maxQue = new PriorityQueue<>(Collections.reverseOrder());
            Map<Integer, Integer> map = new HashMap<>();
            int K = Integer.parseInt(br.readLine());
            for (int j = 0; j < K; j++) {
                String[] input = br.readLine().split(" ");
                char oper = input[0].charAt(0);
                int num = Integer.parseInt(input[1]);

                if (oper == 'I'){
                    map.put(num, map.getOrDefault(num,0) + 1);
                    minQue.add(num);
                    maxQue.add(num);

                } else {
                    if(map.size() == 0) continue;
                    if (num == -1) {
                        deleteMap(minQue, map);
                    } else {
                        deleteMap(maxQue, map);
                    }
                }
            }
            if(map.size() == 0){
                System.out.println("EMPTY");
            } else {
                int max = deleteMap(maxQue, map);
                int min = map.size() > 0 ? deleteMap(minQue, map) : max; // 다시 체크 해줘야 한다
                System.out.println(max + " " + min);
            }

        }

        }
    private static int deleteMap(PriorityQueue<Integer> queue, Map<Integer, Integer> map){
        int num;
        while(true){
            num = queue.poll();
            int count = map.getOrDefault(num, 0);

            if(count == 0) continue;

            if(count == 1){
                map.remove(num);
            } else {
                map.put(num, count-1);
            }
            break;
        }

        return num;
    }
}
