import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static class Puyo{
        int x, y;
        char color;
        public Puyo(int x, int y, char color) {
            this.x = x;
            this.y = y;
            this.color = color;
        }
    }
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static char[][] game = new char[12][6];
    private static boolean[][] visited;
    private static List<Puyo> list;
    private static int[] dx = {-1,1,0,0};
    private static int[] dy = {0,0,-1,1};
    private static int answer;
    public static void main(String[] args) throws IOException {
        for (int i = 0; i < 12; i++) {
            String input = br.readLine();
            for (int j = 0; j < 6; j++) {
                game[i][j] = input.charAt(j);
            }
        }

        answer = 0;

        while (true) {
            boolean isFinish = true;
            visited = new boolean[12][6];
            for (int i = 0; i < 12; i++) {
                for (int j = 0; j < 6; j++) {
                    if (game[i][j] != '.' && !visited[i][j]) {
                        bfs(new Puyo(i, j, game[i][j]));
                        if (list.size() >= 4) {
                            isFinish = false;
                            for (Puyo puyo : list) {
                                game[puyo.x][puyo.y] = '.';
                            }
                        }
                    }
                }
            }
            if (isFinish) {
                System.out.println(answer);
                break;
            }
            setGame();
            answer++;
        }

    }

    private static void bfs(Puyo p) {
        list = new LinkedList<>();
        Queue<Puyo> queue = new LinkedList<>();
        visited[p.x][p.y] = true;
        queue.add(p);
        list.add(p);
        while(!queue.isEmpty()) {
            Puyo temp = queue.poll();
            for(int i = 0; i < 4; i++) {
                int nx = temp.x + dx[i];
                int ny = temp.y + dy[i];
                if(nx >= 0 && nx < 12 && ny >= 0 && ny < 6 && !visited[nx][ny] && game[nx][ny] == temp.color){
                    visited[nx][ny] = true;
                    list.add(new Puyo(nx, ny, temp.color));
                    queue.add(new Puyo(nx, ny, temp.color));
                }
            }
        }
    }

    private static void setGame() {
        for(int i = 11; i > 0; i--) {
            for(int j = 5; j >= 0; j--) {
                if(game[i][j] == '.') {
                   int nx = i - 1;
                    while(nx > 0 && game[nx][j] == '.') {
                        nx--;
                    }
                    if(game[nx][j] != '.'){
                        game[i][j] = game[nx][j];
                        game[nx][j] = '.';
                    }
                }
            }
        }
    }
}
