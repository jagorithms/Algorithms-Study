package com.example.javacodingtest.boj.gold;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 @author ranuinclulus
 @since 2024.09.30
 @link https://solved.ac/en/search?query=5052
 @timecomplex
 @performance 33936kb, 548ms
 @category
 @note
 */
public class four5052 {
    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder builder = new StringBuilder();
    static StringTokenizer tokenizer;
    static int testNum, n;
    static String[] numbers;

    public void solution() throws IOException {
        testNum = Integer.parseInt(reader.readLine());
        for (int test = 1; test <= testNum; test++) {
            n = Integer.parseInt(reader.readLine());
            numbers = new String[n];
            for (int i = 0; i < n; i++) {
                numbers[i] = reader.readLine();
            }
            Arrays.sort(numbers);

            boolean signal = true;
            for (int i = 0; i < n - 1; i++) {
                if (numbers[i + 1].startsWith(numbers[i])) {
                    signal = false;
                    break;
                }
            }

            builder.append(signal ? "YES" : "NO").append('\n');
        }
        writer.write(builder.toString());
        writer.flush();
    }

    public static void main(String[] args) throws IOException {
        new four5052().solution();
    }
}
