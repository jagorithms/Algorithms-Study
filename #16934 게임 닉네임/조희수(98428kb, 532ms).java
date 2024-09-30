package com.example.javacodingtest.boj.gold;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/*
 @author ranuinclulus
 @since
 @link https://www.acmicpc.net/problem/16934
 @timecomplex
 @performance 98428kb, 532ms
 @category
 @note
 */
public class three16934 {
    class NickNameCount {
        Map<String, Integer> nicknameCount = new HashMap<>();
        public void insert(String word) {
            if(nicknameCount.containsKey(word)) {
                nicknameCount.put(word, nicknameCount.get(word) + 1);
            } else {
                nicknameCount.put(word, 1);
            }
        }

        public int getCount(String word) {
            return nicknameCount.getOrDefault(word, 0);
        }
    }
    class TrieNode {
        Map<Character, TrieNode> childNodes;
        boolean isTerminated;

        public TrieNode() {
            this.childNodes = new HashMap<>();
            this.isTerminated = false;
        }
    }
    class Trie {
        TrieNode rootNode;

        public Trie() {
            this.rootNode = new TrieNode();
        }

        public void insert(String word) {
            TrieNode node = rootNode;

            for(Character ch : word.toCharArray()) {
                node = node.childNodes.computeIfAbsent(ch, key -> new TrieNode());
            }
            node.isTerminated = true;
        }

        public int searchMatchLastIndex(String word) {
            int index = -1;
            TrieNode node = rootNode;
            for (char ch : word.toCharArray()) {
                node = node.childNodes.getOrDefault(ch, null);

                if (node == null) {
                    break;
                }
                index++;
            }
            return index;
        }

    }
    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder builder = new StringBuilder();
    static int n;
    static NickNameCount nickNameCount;
    static Trie trie;

    public void solution() throws IOException {
        n = Integer.parseInt(reader.readLine());
        nickNameCount = new NickNameCount();
        trie = new Trie();
        for (int i = 0; i < n; i++) {
            String nickname = reader.readLine();
            nickNameCount.insert(nickname);

            int matchLastIndex = trie.searchMatchLastIndex(nickname);
            if (matchLastIndex == nickname.length() - 1) {
                int x = nickNameCount.getCount(nickname);
                builder.append(nickname).append((x > 1) ? x : "").append('\n');
            } else if (matchLastIndex > -1) {
                builder.append(nickname, 0, matchLastIndex + 2).append('\n');
            } else if (matchLastIndex == -1) {
                trie.insert(nickname);
                builder.append(nickname.charAt(0)).append('\n');
            }
            trie.insert(nickname);
        }
        writer.write(builder.toString());
        writer.flush();
    }

    public static void main(String[] args) throws IOException {
        new three16934().solution();
    }
}
