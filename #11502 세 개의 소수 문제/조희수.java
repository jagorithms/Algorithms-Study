package com.example.javacodingtest.boj.silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class four11502 {
    static boolean[] isPrime = new boolean[10001];
    public void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int testNum = Integer.parseInt(br.readLine());

        isPrime[2] = true;
        for (int i = 3; i < 1000; i++) {
            isPrime[i] = calPrime(i);
        }

        for (int test = 0; test < testNum; test++) {
            int number = Integer.parseInt(br.readLine());
            boolean isFind = false;
            for (int i = 2; i < 1000; i++) {
                if (!isPrime[i]) continue;
                int temp = number - i;
                for (int j = i; j < temp; j++) {
                    if (!isPrime[j]) continue;
                    temp = temp - i;
                    if (isPrime[temp]) {
                        System.out.printf("%d %d %d\n", i, j, temp);
                        isFind = true;
                        break;
                    } else break;
                }
                if (isFind) break;
            }
            if (!isFind) System.out.println(0);;
        }
    }

    private boolean calPrime(int num) {
        if (num % 2 == 0) return false;
        for (int i = 3; i <= Math.sqrt(num); i += 2) {
            if (num % i == 0) return false;
        }
        return true;
    }

    public static void main(String[] args) throws IOException {
        new four11502().solution();
    }
}
