import java.util.*;
import java.io.*;

public class Main {
    private static final String LT = "<", GT = ">", COMMA = ",", WHITESPACE = " ";

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());

        CircularLinkedList ll = new CircularLinkedList();
        int idx = 1;
        while (st.hasMoreTokens()) {
            ll.add(new Node(Integer.parseInt(st.nextToken()), idx++));
        }
        Node removed = ll.remove();
        LinkedList<Integer> ans = new LinkedList<>();
        ans.add(removed.idx);

        while (!ll.isEmpty()) {
            ll.move(removed.data);
            if (removed.data > 0) {
                ll.move(-1);
            }
            removed = ll.remove();
            ans.add(removed.idx);
        }

        for (int i = 0; i < ans.size(); i++) {
            sb.append(ans.get(i));
            if (i == ans.size() - 1) {
                break;
            }
            sb.append(WHITESPACE);
        }
        System.out.append(sb);
    }

    static class Node {
        int data, idx;
        Node prev, next;

        Node(int data, int idx) {
            this.data = data;
            this.idx = idx;
        }
    }

    static class CircularLinkedList {
        Node head;
        Node tail;
        int size;
        Node cursor;

        CircularLinkedList() {
            this.size = 0;
        }

        boolean isEmpty() {
            return this.size == 0;
        }

        void add(Node newNode) {
            if (size == 0) {
                this.head = this.tail = this.cursor = newNode;
                this.head.next = tail;
                this.tail.next = head;
                this.head.prev = tail;
                this.tail.next = head;
            } else {
                this.tail.next = newNode;
                newNode.prev = this.tail;
                newNode.next = this.head;
                this.head.prev = newNode;
                this.tail = newNode;
            }
            this.size++;
        }

        void move(int times) {
            if (times == 0)
                return;
            if (times > 0) {
                for (int i = 0; i < times; i++) {
                    cursor = cursor.next;
                }
            } else {
                for (int i = 0; i < -times; i++) {
                    cursor = cursor.prev;
                }
            }
        }

        void next() {
            this.cursor = this.cursor.next;
            // return this.cursor.data;
        }

        void prev() {
            this.cursor = this.cursor.prev;
        }

        Node remove() {
            if (cursor == head) {
                this.head = this.head.next;
                this.head.prev = this.tail;
                this.tail.next = this.head;
            } else if (cursor == tail) {
                this.tail = this.tail.prev;
                this.tail.next = this.head;
                this.head.prev = this.tail;
            }

            this.cursor.prev.next = this.cursor.next;
            this.cursor.next.prev = this.cursor.prev;
            this.size--;

            Node removed = this.cursor;
            this.cursor = this.cursor.next;
            return removed;
        }
    }
}
