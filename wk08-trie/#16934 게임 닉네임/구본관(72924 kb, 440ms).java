import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        int N = Integer.parseInt(br.readLine());
        HashMap<String, Integer> dic = new HashMap<>();

        Node[] start = new Node[26];

        for (int i = 0; i < N; i++) {
            String s = br.readLine();

            if(dic.containsKey(s)){
                dic.put(s, dic.get(s)+1);
                sb.append(s).append(dic.get(s)).append("\n");
            }else{
                dic.put(s, 1);
                if(start[s.charAt(0)-'a'] == null){
                    start[s.charAt(0)-'a'] = new Node();
                }
                insert(start[s.charAt(0)-'a'], s.toCharArray(), 0, false);
            }
            //System.out.println(Arrays.toString(start));
        }
        System.out.println(sb);
    }

    private static void insert(Node parent , char[] charArray, int index, boolean isprint) {
        if(index>=charArray.length){
            if(!isprint){
                sb.append("\n");
            }
            return;
        }

        if(!isprint){
            sb.append(charArray[index]);
        }

        if(parent.child[charArray[index]-'a'] == null){
            parent.child[charArray[index]-'a'] = new Node();
            if(!isprint){
                sb.append("\n");
                isprint = true;
            }
        }
        insert(parent.child[charArray[index]-'a'], charArray, index+1, isprint);
    }

    private static class Node{
        Node[] child = new Node[26];
    }
}
