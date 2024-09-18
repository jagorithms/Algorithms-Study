package com.example.javacodingtest.boj.silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class two2304 {
    static int[] ground = new int[1001];
    static int answer = 0;
    static int minIndex = 1001;
    static int maxIndex = 0;
    public void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int position = Integer.parseInt(st.nextToken());
            int height = Integer.parseInt(st.nextToken());
            ground[position] = height;
            minIndex = Math.min(position, minIndex);
            maxIndex = Math.max(position, maxIndex);
        }

        int range = maxIndex - minIndex + 1;
        Deque<Integer> frontier = new ArrayDeque<>();
        Deque<Integer> backtier = new ArrayDeque<>();
        frontier.addFirst(ground[minIndex]);
        backtier.push(ground[maxIndex]);
        for (int i = 1; i < range; i++) {
            if (ground[minIndex + i] > frontier.peekLast()) {
                frontier.addLast(ground[minIndex + i]);
            } else {
                frontier.addLast(frontier.peekLast());
            }
            if (ground[maxIndex - i] < backtier.peek()) {
                backtier.push(backtier.peek());
            } else {
                backtier.push(ground[maxIndex - i]);
            }
        }

        for (int i = 0; i < range; i++) {
            answer += Math.min(frontier.pop(), backtier.pop());
        }
        System.out.println(answer);
    }

    public static void main(String[] args) throws IOException {
        new two2304().solution();
    }
}
