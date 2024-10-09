package com.example.javacodingtest.boj.silver;

import java.io.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

/*
 @author ranuinclulus
 @since
 @link
 @timecomplex
 @performance 24524kb, 244ms
 @category
 @note
 */
public class four17266 {
    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder builder = new StringBuilder();
    static StringTokenizer tokenizer;
    static int[] lights;
    static int length, n, answer;


    public void solution() throws IOException {;
        length = Integer.parseInt(reader.readLine());
        n = Integer.parseInt(reader.readLine());
        lights = new int[n];

        tokenizer = new StringTokenizer(reader.readLine());
        for (int i = 0; i < n; i++) {
            lights[i] = Integer.parseInt(tokenizer.nextToken());
        }

        answer = lights[0];
        for (int i = 0; i < n - 1; i++) {
            int value = (lights[i + 1] - lights[i]) / 2;
            if (((lights[i + 1] - lights[i]) % 2) != 0) {
                value++;
            }
            answer = Math.max(answer, value);
        }

        answer = Math.max(answer, length - lights[n - 1]);

        builder.append(answer);
        writer.write(builder.toString());
        writer.flush();
    }

    public static void main(String[] args) throws IOException {
        new four17266().solution();
    }
}
