package com.example.javacodingtest.boj.gold;

import java.io.*;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

/*
 @author ranuinclulus
 @since 2024.11.14
 @link https://www.acmicpc.net/problem/9252
 @timecomplex
 @performance 18500kb 132ms
 @category
 @note
 */
public class four9252 {
    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder builder = new StringBuilder();
    static StringTokenizer tokenizer;
    static char[] one, two;
    static int oneLength, twoLength;
    static int[][] dp;

    public void solution() throws IOException {
        one = reader.readLine().toCharArray();
        two = reader.readLine().toCharArray();
        oneLength = one.length;
        twoLength = two.length;

        dp = new int[twoLength + 1][oneLength + 1];

        for (int i = 1; i <= twoLength; i++) {
            char c = two[i - 1];
            for (int j = 1; j <= oneLength; j++) {
                if (c == one[j - 1]) dp[i][j] = dp[i - 1][j - 1] + 1;
                else dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
            }
        }

        builder.append(dp[twoLength][oneLength]).append('\n');
        
        int now = dp[twoLength][oneLength];
        int twoCursor = twoLength;
        int oneCursor = oneLength;

        Deque<Character> stack = new ArrayDeque<>();
        while (0 < twoCursor && 0 < oneCursor && 0 < now) {
            if (dp[twoCursor - 1][oneCursor] == now) {
                twoCursor--;
            } else if (dp[twoCursor][oneCursor - 1] == now) {
                oneCursor--;
            } else {
                oneCursor--;
                twoCursor--;
                now--;
                stack.push(one[oneCursor]);
            }
        }

        while (!stack.isEmpty()) {
            builder.append(stack.pop());
        }

        writer.write(builder.toString());
        writer.flush();
    }



    public static void main(String[] args) throws IOException {
        new four9252().solution();
    }
}
