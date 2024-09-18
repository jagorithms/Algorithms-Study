package com.example.javacodingtest.boj.silver;

import java.io.*;
import java.util.StringTokenizer;

/*
 @author ranuinclulus
 @since 2024.08.15
 @link https://www.acmicpc.net/problem/13335
 @timecomplex
 @performance 14996KB, 172MS
 @category
 @note
 */
public class one13335 {
    class Truck {
        int weight;
        int position;
        boolean on;

        public Truck(int weight) {
            this.weight = weight;
            this.position = 0;
            this.on = false;
        }
    }
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static int n;
    static int w;
    static int l;
    static int time;
    static Truck[] trucks;
    static int currentWeight;

    public void solution() throws IOException {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken()); // 트럭 수
        w = Integer.parseInt(st.nextToken()); // 다리 길이
        l = Integer.parseInt(st.nextToken()); // 최대 하중
        trucks = new Truck[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            trucks[i] = new Truck(Integer.parseInt(st.nextToken()));
        }

        currentWeight = 0; // 현재 다리에 올라간 무게
        while (!allClear()) {
            time++;

            for (Truck truck : trucks) {
                if (truck.position == -1) continue;
                if (truck.position == w) { // 어떤 트럭이 해당 다리 끝까지 왔다면
                    truck.position = -1; // 이동 종료
                    currentWeight -= truck.weight;
                    truck.on = false; // 내림
                }
                else if (!truck.on && (currentWeight + truck.weight <= l)){ // 새로 올리는 경우
                    truck.position++;
                    currentWeight += truck.weight;
                    truck.on = true; // 올림
                    break;
                }
                else if (truck.on) {
                    truck.position++;
                }
                else break;
            }
        }

        sb.append(time);
        bw.write(sb.toString());
        bw.flush();
    }

    private boolean allClear() {
        for (Truck truck : trucks) {
            if (truck.position != -1) return false;
        }
        return true;
    }

    public static void main(String[] args) throws IOException {
        new one13335().solution();
    }
}
