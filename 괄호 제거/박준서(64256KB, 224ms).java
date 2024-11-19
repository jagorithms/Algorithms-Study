import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.TreeSet;

/*
 * 시간 : 224ms
 * 메모리 : 64256KB
 * */

public class Main {
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static String oper;
	private static int N;
	private static Deque<Integer> queue  = new LinkedList<>();
	private static List<int[]> wait = new ArrayList<>();
	private static TreeSet<String> result = new TreeSet<>();
	public static void main(String[] args) throws IOException {
		oper = br.readLine();
		
		for(int i = 0 ; i < oper.length(); i++) {
			if(oper.charAt(i) == '(') {
				queue.add(i);
			} else if(oper.charAt(i) == ')') {
				wait.add(new int[] {queue.pollLast(), i});
			}
		}
		
		N = wait.size();
		partSet(0,new boolean[N]);
		
		for(String set : result) {
			System.out.println(set);
		}
	}
	
	
	private static void partSet(int depth, boolean[] visited) {
		if(depth == N) {
			boolean isAllFalse = true;
			for(int i = 0; i < N; i++) {
				if(visited[i] == true) isAllFalse = false;
			}
			if(!isAllFalse) {
				remove(visited);
			}
			return;
		}
		visited[depth] = true;
		partSet(depth + 1, visited);
		visited[depth] = false;
		partSet(depth + 1, visited);
	}
	
	private static void remove(boolean[] visited) { // wait의 index 값의 T/F가 들어있음
		boolean[] selected = new boolean[oper.length()];
		for(int i = 0; i < N; i++) {
			if(visited[i]) {
				int first = wait.get(i)[0];
				int second = wait.get(i)[1];
				selected[first] = true;
				selected[second] = true;
			}
		}
		
		String temp = "";
		for(int i = 0; i < oper.length(); i++) {
			if(!selected[i]) temp += oper.charAt(i);
		}
		result.add(temp);
	}
}
