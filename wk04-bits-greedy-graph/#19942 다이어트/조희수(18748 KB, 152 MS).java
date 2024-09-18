package boj;

import java.io.*;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

/*
 @author ranuinclulus
 @since 2024.08.26
 @link https://www.acmicpc.net/problem/19942
 @timecomplex
 @performance 18748 KB, 152 MS
 @category
 @note
 */
public class goldFour19942 {
    class Food {
        int p;
        int f;
        int s;
        int v;
        int c;

        public Food(int p, int f, int s, int v, int c) {
            this.p = p;
            this.f = f;
            this.s = s;
            this.v = v;
            this.c = c;
        }
    }

    class Node implements Comparable<Node> {
        int cost;
        String result;

        public Node(int cost, String result) {
            this.cost = cost;
            this.result = result;
        }

        @Override
        public int compareTo(Node o) {
            if (this.cost == o.cost) {
                return this.result.compareTo(o.result);
            }
            return this.cost - o.cost;
        }
    }

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder sb;
    static StringTokenizer st;
    static int n;
    static Food[] foods;
    static int mp;
    static int mf;
    static int ms;
    static int mv;
    static int mc = Integer.MAX_VALUE;
    static List<Node> candidate = new LinkedList<>();

    public void solution() throws IOException {
        n = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        mp = Integer.parseInt(st.nextToken());
        mf = Integer.parseInt(st.nextToken());
        ms = Integer.parseInt(st.nextToken());
        mv = Integer.parseInt(st.nextToken());;
        foods = new Food[n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            foods[i] = new Food(Integer.parseInt(st.nextToken()),
                    Integer.parseInt(st.nextToken()),
                    Integer.parseInt(st.nextToken()),
                    Integer.parseInt(st.nextToken()),
                    Integer.parseInt(st.nextToken()));
        }

        recursive(0, 0);
        Collections.sort(candidate);

        sb = new StringBuilder();
        if (candidate.isEmpty()) {
            sb.append(-1);
        } else {
            sb.append(candidate.get(0).cost).append('\n').append(candidate.get(0).result);
        }

        bw.write(sb.toString());
        bw.flush();
    }

    private void recursive(int depth, int check) {
        if (depth == n) {
            calculate(check);
            return;
        }
        recursive(depth + 1, check);
        recursive(depth + 1, check | (1 << depth));
    }

    private void calculate(int check) {
        int totalP = 0;
        int totalF = 0;
        int totalS = 0;
        int totalV = 0;
        int totalC = 0;
        for (int i = 0; i < n; i++) {
            if ((check & (1 << i)) > 0) {
                totalP += foods[i].p;
                totalF += foods[i].f;
                totalS += foods[i].s;
                totalV += foods[i].v;
                totalC += foods[i].c;
            }
        }
        if (totalP < mp || totalF < mf || totalS < ms || totalV < mv) return;
        if (totalC <= mc) {
            mc = totalC;
            sb = new StringBuilder();
            for (int i = 0; i < n; i++) {
                if ((check & (1 << i)) > 0) {
                    sb.append(i + 1).append(' ');
                }
            }
            candidate.add(new Node(mc, sb.toString()));
        }
    }

    public static void main(String[] args) throws IOException {
        new goldFour19942().solution();
    }
}

