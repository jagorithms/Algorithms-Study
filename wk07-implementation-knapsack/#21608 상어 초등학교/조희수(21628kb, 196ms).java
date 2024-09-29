package com.example.javacodingtest.boj.gold;

import java.io.*;
import java.util.*;

/*
 @author ranuinclulus
 @since 2024.09.23
 @link https://www.acmicpc.net/problem/21608
 @timecomplex
 @performance 21628kb, 196ms
 @category
 @note
 */
public class five21608 {
    class Place implements Comparable<Place>{
        int row;
        int col;
        int emptyCount;
        int preferCount;

        public Place(int row, int col, Student student) {
            this.row = row;
            this.col = col;
            this.emptyCount = calEmptyPlace(row, col);
            this.preferCount = calPreferPlace(row, col, student);
        }

        @Override
        public int compareTo(Place o) {
            if (this.preferCount == o.preferCount) {
                if (this.emptyCount == o.emptyCount) {
                    if (this.row == o.row) {
                        return Integer.compare(this.col, o.col);
                    }
                    return Integer.compare(this.row, o.row);
                }
                return -Integer.compare(this.emptyCount, o.emptyCount);
            }
            return -Integer.compare(this.preferCount, o.preferCount);
        }
    }

    class Student {
        int num;
        int preferOne;
        int preferTwo;
        int preferThree;
        int preferFour;

        public Student(int num, int preferOne, int preferTwo, int preferThree, int preferFour) {
            this.num = num;
            this.preferOne = preferOne;
            this.preferTwo = preferTwo;
            this.preferThree = preferThree;
            this.preferFour = preferFour;
        }


    }

    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder builder = new StringBuilder();
    static StringTokenizer tokenizer;
    static int n, answer;
    static int[][] deltas = new int[][] {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    static int[][] classRoom;
    static List<Student> students;

    public void solution() throws IOException {
        n = Integer.parseInt(reader.readLine());
        classRoom = new int[n][n];
        students = new LinkedList<>();

        for (int i = 0; i < n * n; i++) {
            tokenizer = new StringTokenizer(reader.readLine());
            students.add(new Student(
                    Integer.parseInt(tokenizer.nextToken()),
                    Integer.parseInt(tokenizer.nextToken()),
                    Integer.parseInt(tokenizer.nextToken()),
                    Integer.parseInt(tokenizer.nextToken()),
                    Integer.parseInt(tokenizer.nextToken())
            ));
        }

        students.forEach(student -> seating(student));
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int count = calPreferPlace(i, j, findStudent(classRoom[i][j]));
                if (count == 1) answer++;
                else if (count == 2) answer += 10;
                else if (count == 3) answer += 100;
                else if (count == 4) answer += 1000;
            }
        }
        builder.append(answer);
        writer.write(builder.toString());
        writer.flush();
    }

    private Student findStudent(int index) {
        for (Student student : students) {
            if (student.num == index) return student;
        }
        return null;
    }


    private void seating(Student student) {
        PriorityQueue<Place> places = new PriorityQueue<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (classRoom[i][j] != 0) continue;
                places.add(new Place(i, j, student));
            }
        }
        Place target = places.poll();
        classRoom[target.row][target.col] = student.num;
    }
    private int calPreferPlace(int row, int col, Student student) {
        int count = 0;
        for (int i = 0; i < 4; i++) {
            int nextRow = row + deltas[i][0];
            int nextCol = col + deltas[i][1];

            if (nextRow < 0 || nextRow >= n || nextCol < 0 || nextCol >= n) continue;
            if (classRoom[nextRow][nextCol] == student.preferOne ||
                    classRoom[nextRow][nextCol] == student.preferTwo ||
                    classRoom[nextRow][nextCol] == student.preferThree ||
                    classRoom[nextRow][nextCol] == student.preferFour) count++;
        }
        return count;
    }

    private int calEmptyPlace(int row, int col) {
        int count = 0;
        for (int i = 0; i < 4; i++) {
            int nextRow = row + deltas[i][0];
            int nextCol = col + deltas[i][1];

            if (nextRow < 0 || nextRow >= n || nextCol < 0 || nextCol >= n) continue;
            if (classRoom[nextRow][nextCol] == 0) count++;
        }
        return count;
    }

    public static void main(String[] args) throws IOException {
        new five21608().solution();
    }
}
