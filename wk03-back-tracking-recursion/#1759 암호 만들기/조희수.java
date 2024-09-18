package com.example.javacodingtest.boj.gold;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 @author ranuinclulus
 @since 2024.08.14
 @link https://www.acmicpc.net/problem/1759
 @timecomplex
 @performance 14332 KB, 104 MS
 @category
 @note
 */
public class five1759 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static int r;
    static int n;
    static char[] chars;
    static char[] ans;
    static char[] vowels = new char[] {'a', 'e', 'i', 'o', 'u'};

    public void solution() throws IOException {
        st = new StringTokenizer(br.readLine());
        r = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());
        chars = new char[n];
        ans = new char[r];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            chars[i] = st.nextToken().charAt(0);
        }

        Arrays.sort(chars);
        combination(0, 0);
        bw.write(sb.toString());
        bw.flush();
    }

    private void combination(int start, int depth) {

        if (depth == r) {
            if (!check()) return;
            for (char ch : ans) {
                sb.append(ch);
            }
            sb.append('\n');
            return;
        }

        if (start == n ) return;

        ans[depth] = chars[start];
        combination(start + 1, depth + 1);
        combination(start + 1, depth);

    }

    private boolean check() {
        int vowelCnt = 0;
        for (char ch : ans) {
            for (char vowel : vowels) {
                if (ch == vowel) vowelCnt++;
            }
        }
        int consonantCnt = r - vowelCnt;
        return (consonantCnt >= 2 && vowelCnt >= 1);
    }

    public static void main(String[] args) throws IOException {
        new five1759().solution();
    }
}
