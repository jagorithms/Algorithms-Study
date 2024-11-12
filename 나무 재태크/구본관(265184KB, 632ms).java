import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int N, M, K;
    static int[][] graph;
    static int[][] Sgraph;
    static List<Tree> treeList = new ArrayList<>();
    static Deque<Tree> treeDq = new ArrayDeque<>();
    static List<Tree> bornTreeList = new ArrayList<>();
    static List<Tree> dieTreeList = new ArrayList<>();
    static int[][] dydx = {{0,1},{1,1},{1,0},{1,-1},{0,-1},{-1,-1},{-1,0},{-1,1}};

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        graph = new int[N][N];

        Sgraph = new int[N][N];
        for (int y = 0; y < N; y++) {
            st = new StringTokenizer(br.readLine());
            for (int x = 0; x < N; x++) {
                graph[y][x] = 5;
                Sgraph[y][x] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int age = Integer.parseInt(st.nextToken());
            treeList.add(new Tree(x-1, y-1, age));
        }
        Collections.sort(treeList, (a,b)->(a.age - b.age));
        treeDq = new ArrayDeque<>(treeList);

        for (int day = 1; day <= K; day++) {
//            for (int y = 0; y < N; y++) {
//                System.out.println(Arrays.toString(graph[y]));
//            }
//            System.out.println(treeDq);
            int size = treeDq.size();
            for (int i = 0; i < size; i++) {
                Tree tree = treeDq.pollFirst();
                if(graph[tree.y][tree.x] >= tree.age){
                    graph[tree.y][tree.x] -= tree.age;
                    tree.age+=1;
                    if(tree.age%5==0){
                        bornTreeList.add(tree);
                    }
                    treeDq.offerLast(tree);
                }else{
                    dieTreeList.add(tree);
                }
            }

            for (Tree tree : dieTreeList){
                graph[tree.y][tree.x]+=tree.age/2;
            }
            dieTreeList = new ArrayList<>();

            for(Tree tree : bornTreeList){
                for(int[] dyx : dydx){
                    int ny = tree.y + dyx[0];
                    int nx = tree.x + dyx[1];
                    if(ny<0 || ny>=N || nx<0 || nx>=N){
                        continue;
                    }
                    treeDq.offerFirst(new Tree(ny, nx, 1));
                }
            }
            bornTreeList = new ArrayList<>();

            for (int y = 0; y < N; y++) {
                for (int x = 0; x < N; x++) {
                    graph[y][x] += Sgraph[y][x];
                }
            }
        }
        System.out.println(treeDq.size());
    }

    static class Tree{
        int y, x, age;
        Tree(int y, int x, int age){
            this.y = y;
            this.x = x;
            this.age = age;
        }

        @Override
        public String toString() {
            return "Tree{" + "y=" + y + ", x=" + x + ", age=" + age + '}';
        }
    }
}
