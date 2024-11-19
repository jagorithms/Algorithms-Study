package com.example.javacodingtest.boj.gold;

import java.io.*;
import java.util.*;

/*
 @author ranuinclulus
 @since 2024.11.13
 @link https://www.acmicpc.net/problem/2800
 @timecomplex
 @performance 28780kb 244ms
 @category
 @note
 */
public class four2800 {
    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder builder = new StringBuilder();
    static StringTokenizer tokenizer;
    static String input;
    static List<int[]> brackets;
    static Set<String> result;
    static boolean[] check;

    public void solution() throws IOException {
        input = reader.readLine();

        brackets = new LinkedList<>();
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if (c == '(') {
                stack.push(i);
            } else if (c == ')') {
                brackets.add(new int[] {stack.pop(), i});
            }

        }

        check = new boolean[input.length()];
        result = new TreeSet<>();

        makeCombination(0, input.toCharArray());

        for(String s : result) {
            builder.append(s).append('\n');
        }
        writer.write(builder.toString());
        writer.flush();
    }

    private void makeCombination(int depth, char[] charArray) {
        if (depth == brackets.size()) {
            boolean signal = false;
            String candidate = "";
            for (int i = 0; i < input.length(); i++) {
                if (!check[i]) candidate += charArray[i];
                else signal = true;
            }
            if (signal) result.add(candidate);
            return;
        }

        makeCombination(depth + 1, charArray);

        int[] bracket = brackets.get(depth);
        check[bracket[0]] = true;
        check[bracket[1]] = true;

        makeCombination(depth + 1, charArray);

        check[bracket[0]] = false;
        check[bracket[1]] = false;
    }

    public static void main(String[] args) throws IOException {
        new four2800().solution();
    }
}
