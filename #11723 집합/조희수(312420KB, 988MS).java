package com.example.javacodingtest.boj.silver;

import java.io.*;
import java.util.StringTokenizer;

/*
 @author ranuinclulus
 @since 2024.08.24
 @link https://www.acmicpc.net/problem/11723
 @timecomplex
 @performance 312420KB, 988MS
 @category
 @note
 */
public class five11723_2 {
    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder builder = new StringBuilder();
    static StringTokenizer tokenizer;
    static int n;
    static long bitmask;
    static String operation;
    static int value;


    public void solution() throws IOException {
        n = Integer.parseInt(reader.readLine());
        bitmask = 0;
        for (int i = 0; i < n; i++) {
            tokenizer = new StringTokenizer(reader.readLine());
            operation = tokenizer.nextToken();

            if (operation.equals("add")) {
                value = Integer.parseInt(tokenizer.nextToken());
                bitmask = bitmask | (1 << value);
            } else if (operation.equals("remove")) {
                value = Integer.parseInt(tokenizer.nextToken());
                bitmask = bitmask & ~(1 << value);
            } else if (operation.equals("check")) {
                value = Integer.parseInt(tokenizer.nextToken());
                builder.append(((bitmask & (1 << value)) > 0) ? 1 : 0).append('\n');
            } else if (operation.equals("toggle")) {
                value = Integer.parseInt(tokenizer.nextToken());
                bitmask = bitmask ^ (1 << value);

            } else if (operation.equals("all")) {
                bitmask = bitmask | (1 << 21) - 1;
            } else if (operation.equals("empty")) {
                bitmask = 0;
            }
            //System.out.println(bitmask);
        }
        writer.write(builder.toString());
        writer.flush();

    }

    public static void main(String[] args) throws IOException {
        new five11723_2().solution();
    }
}
