import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws Exception {
        int T = Integer.parseInt(br.readLine());

        for (int t = 0; t < T; t++) {
            int N = Integer.parseInt(br.readLine());
            Node root = new Node();  // 트라이의 루트 노드
            boolean isConsistent = true;

            for (int n = 0; n < N; n++) {
                String number = br.readLine();
                if (!insert(root, number)) {
                    isConsistent = false;
                }
            }

            if (isConsistent) {
                System.out.println("YES");
            } else {
                System.out.println("NO");
            }
        }
    }

    static class Node {
        Node[] nextNode = new Node[10];  // 0~9까지의 숫자를 저장할 배열
        boolean isEnd = false;  // 번호가 끝나는 지점 표시
    }

    // 트라이에 번호를 삽입하면서 일관성 체크
    static boolean insert(Node root, String number) {
        Node currentNode = root;
        for (int i = 0; i < number.length(); i++) {
            int num = number.charAt(i) - '0';

            // 중간에 이미 끝나는 번호가 있으면 일관성 위배
            if (currentNode.isEnd) {
                return false;
            }

            if (currentNode.nextNode[num] == null) {
                currentNode.nextNode[num] = new Node();
            }

            currentNode = currentNode.nextNode[num];
        }

        // 번호가 끝났을 때, 현재 노드에 자식이 있으면 일관성 위배
        for (int i = 0; i < 10; i++) {
            if (currentNode.nextNode[i] != null) {
                return false;
            }
        }

        // 현재 번호를 종료 지점으로 설정
        currentNode.isEnd = true;
        return true;
    }
}
