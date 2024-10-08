import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer tokens;

    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            int N = Integer.parseInt(br.readLine());
            TrieNode trie = new TrieNode();
            boolean isConsistent = true; // 일관성 있는지의 여부

            List<String> phoneNumbers = new ArrayList<>();
            for (int i = 0; i < N; i++) {
                phoneNumbers.add(br.readLine());
                trie.insert(phoneNumbers.get(i));
            }

            for (String phoneNumber : phoneNumbers) {
                if (trie.contains(phoneNumber)) {
                    isConsistent = false;
                    break;
                }
            }

            System.out.println(isConsistent ? "YES" : "NO");
        }
    }

    static class TrieNode {
        Map<Character, TrieNode> children = new HashMap<>();
        boolean isEnd;

        // 새로운 전화번호 추가
        void insert(String word) {
            TrieNode cur = this;

            for (int i = 0; i < word.length(); i++) {
                char c = word.charAt(i);

                cur.children.putIfAbsent(c, new TrieNode());
                cur = cur.children.get(c);

                if (i == word.length() - 1) {
                    cur.isEnd = true;
                    return;
                }
            }
        }

        // 전화번호가 존재하는지 검색
        boolean contains(String word) {
            TrieNode cur = this;

            for (int i = 0; i < word.length(); i++) {
                char c = word.charAt(i);

                TrieNode child = cur.children.get(c);
                if (child == null) {
                    return false;
                }
                cur = child;
            }

            if (cur.isEnd) {
                if (cur.children.isEmpty()) {
                    return false;
                }
            }
            return true;
        }
    }
}
