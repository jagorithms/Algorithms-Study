package com.example.javacodingtest.boj.gold;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 @author ranuinclulus
 @since
 @link https://www.acmicpc.net/problem/2565
 @timecomplex
 @performance 14244kb 104ms
 @category
 @note
 */
public class five2565 {
    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder builder = new StringBuilder();
    static StringTokenizer tokenizer;
    static int n, answer;
    static Wire[] wires;
    static int[] dp = new int[101];

    class Wire implements Comparable<Wire>{
        int start;
        int end;

        Wire(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public int compareTo(Wire o) {
            return Integer.compare(this.start, o.start);
        }
    }

    public void solution() throws IOException {
        n = Integer.parseInt(reader.readLine());
        wires = new Wire[n];

        for (int i = 0; i < n; i++) {
            tokenizer = new StringTokenizer(reader.readLine());
            wires[i] = new Wire(Integer.parseInt(tokenizer.nextToken()), Integer.parseInt(tokenizer.nextToken()));
        }

        Arrays.sort(wires);
        dp = new int[n];
        dp[0] = 1;

        for (int i = 1; i < n; i++) {
            int index = -1;
            int maxValue = -1;

            for (int j = 0; j < i; j++) {
                if (wires[j].end < wires[i].end
                && dp[j] >= maxValue) {
                    maxValue = dp[j];
                    index = j;
                }
            }
            if (index == -1) {
                dp[i] = 1;
            } else {
                dp[i] = maxValue + 1;
            }
        }

        for(int value : dp) {
            answer = Math.max(answer, value);
        }

        builder.append(n - answer);
        writer.write(builder.toString());
        writer.flush();
    }

    public static void main(String[] args) throws IOException {
        new five2565().solution();
    }
}
