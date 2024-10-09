import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class Main {
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	private static int N;
	private static String[] nicknames;
	private static TrieNode root;
	private static HashMap<String, Integer> nicknameCount;

	public static void main(String[] args) throws NumberFormatException, IOException {
		N = Integer.parseInt(br.readLine());
		nicknames = new String[N];

		root = new TrieNode();
		nicknameCount = new HashMap<>();

		for (int i = 0; i < N; i++) {
			nicknames[i] = br.readLine();
			String prefix = root.insert(nicknames[i]);
			System.out.println(prefix);
		}
	}

	static class TrieNode {
		Map<Character, TrieNode> children = new HashMap<>();

		String insert(String word) {
			TrieNode cur = root;

			boolean flag = false;
			int prefixEnd = word.length();

			for (int i = 0; i < word.length(); i++) {
				char c = word.charAt(i);

				if (!(cur.children.containsKey(c))) {
					cur.children.put(c, new TrieNode());

					if (!flag) {
						flag = true;
						prefixEnd = i + 1;
					}
				}

				cur = cur.children.get(c);
			}

			if (!nicknameCount.containsKey(word)) {
				nicknameCount.put(word, 1);
				return word.substring(0, prefixEnd);
			} else {
				int count = nicknameCount.get(word) + 1;
				nicknameCount.put(word, count);
				return word.concat(Integer.toString(count));
			}

		}
	}

}
