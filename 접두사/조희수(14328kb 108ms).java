package com.example.javacodingtest.boj.silver;

import java.io.*;
import java.util.*;

/*
 @author ranuinclulus
 @since 2024.10.31
 @link https://www.acmicpc.net/problem/1141
 @timecomplex
 @performance 14328kb 108ms
 @category
 @note
 */
public class one1141 {
    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder builder = new StringBuilder();
    static int n;
    static List<String> words, list;

    public void solution() throws IOException {
        n = Integer.parseInt(reader.readLine());
        words = new LinkedList<>();
        list = new LinkedList<>();

        for (int i = 0; i < n; i++) {
            words.add(reader.readLine());
        }

        words.sort(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return -Integer.compare(o1.length(), o2.length());
            }
        });

        for (String word : words) {
            if (list.size() == 0) {
                list.add(word);
                continue;
            }

            boolean isPrefix = false;

            for (String exist : list) {
                if (exist.indexOf(word) == 0) {
                    isPrefix = true;
                    break;
                }
            }

            if (!isPrefix) list.add(word);
        }

        builder.append(list.size());
        writer.write(builder.toString());
        writer.flush();
    }


    public static void main(String[] args) throws IOException {
        new one1141().solution();
    }
}
