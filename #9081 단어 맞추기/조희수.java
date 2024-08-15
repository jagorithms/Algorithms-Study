package com.example.javacodingtest.boj.silver;

import java.io.*;
import java.util.*;

/*
 @author ranuinclulus
 @since 2024.08.15
 @link https://www.acmicpc.net/problem/9081
 @timecomplex
 @performance 14192KB 100MS
 @category
 @note
 */
public class one9081 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static int n;
    static String input;
    static int size;
    static List<Character> chars;

    public void solution() throws IOException {
        n = Integer.parseInt(br.readLine());
        for (int test = 0; test < n; test++) {
            input = br.readLine();
            chars = new LinkedList<>();
            size = input.length() - 1;

            for (int i = 0; i <= size; i++) {
                chars.add(input.charAt(i));
            }

            for (int i = size; i > 0; i--) {
                Character now = chars.get(i);
                Character prev = chars.get(i - 1);

                if (now > prev) {
                    Character min = now;
                    int minIndex = i;

                    for (int j = i + 1; j <= size; j++) {
                        if (chars.get(j) > prev) {
                            if (chars.get(j) < min) {
                                min = chars.get(j);
                                minIndex = j;
                            }
                        }
                    }

                    chars.remove(minIndex);
                    chars.remove(i - 1);
                    chars.add(i - 1, min);
                    chars.add(prev);
                    Collections.sort(chars.subList(i, size + 1));
                    break;
                }

            }
            for (Character ch : chars) {
                sb.append(ch);
            }
            sb.append('\n');
        }
        bw.write(sb.toString());
        bw.flush();
    }



    public static void main(String[] args) throws IOException {
        new one9081().solution();
    }
}
