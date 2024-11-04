package com.example.javacodingtest.boj.gold;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;


/*
 @author ranuinclulus
 @since 2024.11.04
 @link https://www.acmicpc.net/problem/13459
 @timecomplex
 @performance 14396kb 104ms
 @category
 @note
 */
public class one13459 {
    class Marble {
        int redCol;
        int redRow;
        int blueCol;
        int blueRow;
        int count;

        public Marble(int redCol, int redRow, int blueCol, int blueRow, int count) {
            this.redCol = redCol;
            this.redRow = redRow;
            this.blueCol = blueCol;
            this.blueRow = blueRow;
            this.count = count;
        }
    }
    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder builder = new StringBuilder();
    static StringTokenizer tokenizer;
    static int colNum, rowNum;
    static char[][] map;
    static int[] dCol = new int[] {-1, 0, 1, 0};
    static int[] dRow = new int[] {0, 1, 0, -1};
    static int holeCol, holeRow;
    static boolean[][][][] visited;
    static Marble red, blue;

    public void solution() throws IOException {
        tokenizer = new StringTokenizer(reader.readLine());
        colNum = Integer.parseInt(tokenizer.nextToken());
        rowNum = Integer.parseInt(tokenizer.nextToken());

        map = new char[colNum][rowNum];
        visited = new boolean[colNum][rowNum][colNum][rowNum];

        for (int i = 0; i < colNum; i++) {
            String input = reader.readLine();
            for (int j = 0; j < rowNum; j++) {
                map[i][j] = input.charAt(j);

                if (map[i][j] == 'O') {
                    holeCol = i;
                    holeRow = j;
                } else if (map[i][j] == 'B') {
                    blue = new Marble(0, 0, i, j, 0);
                } else if (map[i][j] == 'R') {
                    red = new Marble(i, j, 0, 0, 0);
                }
            }
        }
        builder.append(bfs());
        writer.write(builder.toString());
        writer.flush();
    }

    private int bfs() {
        Queue<Marble> queue = new LinkedList<>();
        queue.add(new Marble(red.redCol, red.redRow, blue.blueCol, blue.blueRow, 1));
        visited[red.redCol][red.redRow][blue.blueCol][blue.blueRow] = true;

        while (!queue.isEmpty()) {
            Marble marble = queue.poll();

            int currentRedCol = marble.redCol;
            int currentRedRow = marble.redRow;
            int currentBlueCol = marble.blueCol;
            int currentBlueRow = marble.blueRow;
            int currentCount = marble.count;

            if (currentCount > 10) {
                return 0;
            }

            for (int i = 0; i < 4; i++) {
                int newRedCol = currentRedCol;
                int newRedRow = currentRedRow;
                int newBlueCol = currentBlueCol;
                int newBlueRow = currentBlueRow;

                boolean isRedHole = false;
                boolean isBlueHole = false;

                while (map[newRedCol + dCol[i]][newRedRow + dRow[i]] != '#') {
                    newRedCol += dCol[i];
                    newRedRow += dRow[i];

                    if (newRedCol == holeCol && newRedRow == holeRow) {
                        isRedHole = true;
                        break;
                    }
                }

                while (map[newBlueCol + dCol[i]][newBlueRow + dRow[i]] != '#') {
                    newBlueCol += dCol[i];
                    newBlueRow += dRow[i];

                    if (newBlueCol == holeCol && newBlueRow == holeRow) {
                        isBlueHole = true;
                        break;
                    }
                }

                if (isBlueHole) {
                    continue;
                }
                if (isRedHole && !isBlueHole) {
                    return 1;
                }

                if (newRedCol == newBlueCol && newRedRow == newBlueRow) {
                    if (i == 0) {
                        if (currentRedCol > currentBlueCol) newRedCol -= dCol[i];
                        else newBlueCol -= dCol[i];
                    } else if (i == 1) {
                        if (currentRedRow < currentBlueRow) newRedRow -= dRow[i];
                        else newBlueRow -= dRow[i];
                    } else if (i == 2) {
                        if (currentRedCol < currentBlueCol) newRedCol -= dCol[i];
                        else newBlueCol -= dCol[i];
                    } else {
                        if (currentRedRow > currentBlueRow) newRedRow -= dRow[i];
                        else newBlueRow -= dRow[i];
                    }
                }

                if (!visited[newRedCol][newRedRow][newBlueCol][newBlueRow]) {
                    visited[newRedCol][newRedRow][newBlueCol][newBlueRow] = true;
                    queue.add(new Marble(newRedCol, newRedRow, newBlueCol, newBlueRow, currentCount + 1));
                }
            }
        }
        return 0;
    }


    public static void main(String[] args) throws IOException {
        new one13459().solution();
    }
}
