import java.util.*;
import java.io.*;

public class 강희진 {

    private static final char MOVE_LEFT = '<', MOVE_RIGHT = '>', BACKSPACE = '-';

    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int i = 0; i < T; i++) {
            sb.append(solvePassword(br.readLine().toCharArray())).append("\n");
        }
        System.out.append(sb);
    }

    private static String solvePassword(char[] input) {
        LinkedList<Character> output = new LinkedList<>();
        int idx = 0;
        int outputIdx = 0;
        while (idx < input.length) {
            char current = input[idx];
            if (current == MOVE_LEFT) {
                outputIdx = Math.max(0, outputIdx - 1);
            } else if (current == MOVE_RIGHT) {
                outputIdx = Math.min(output.size(), outputIdx + 1);
            } else if (current == BACKSPACE) {
                if (!output.isEmpty()) {
                    if (outputIdx != 0) {
                        output.remove(outputIdx - 1);
                        outputIdx = Math.max(0, --outputIdx);
                    }
                }
            } else {
                output.add(outputIdx++, current);
                outputIdx = Math.min(output.size(), outputIdx);
            }
            idx++;
        }

        StringBuilder sb = new StringBuilder();
        for (char c : output)
            sb.append(c);

        return sb.toString();
    }
}