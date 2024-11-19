package com.example.javacodingtest.boj.silver;

import java.io.*;
import java.util.StringTokenizer;

/*
 @author ranuinclulus
 @since 2024.11.13
 @link https://www.acmicpc.net/problem/1522
 @timecomplex
 @performance 14288kb 108ms
 @category
 @note
 */
public class one1522 {
    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder builder = new StringBuilder();
    static StringTokenizer tokenizer;
    static int exchange, aCount;
    static String input;


    public void solution() throws IOException {
        input = reader.readLine();
        exchange = Integer.MAX_VALUE;

        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) == 'a') aCount++;
        }

        for (int i = 0; i < input.length(); i++) {
            int bCount = 0;
            for (int j = i; j < aCount + i; j++) {
                if (input.charAt(j % input.length()) == 'b') bCount++;
            }
            exchange = Math.min(exchange, bCount);
        }

        builder.append(exchange);
        writer.write(builder.toString());
        writer.flush();
    }


    public static void main(String[] args) throws IOException {
        new one1522().solution();
    }
}
