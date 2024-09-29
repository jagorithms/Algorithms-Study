import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// 파이어볼 속성 정의를 위한 클래스
class Fireball {
    int r, c, mass, speed, direction;

    public Fireball(int r, int c, int mass, int speed, int direction) {
        this.r = r;
        this.c = c;
        this.mass = mass;
        this.speed = speed;
        this.direction = direction;
    }
}

public class Main {

    static int n;
    static int[][] directions = { {-1, 0}, {-1, 1}, {0, 1}, {1, 1}, {1, 0}, {1, -1}, {0, -1}, {-1, -1} };
    static List<Fireball> fireballs = new ArrayList<>();
    static List<Fireball>[][] board;

    // 파이어볼을 이동시키는 메서드
    public static void move(Fireball fb) {
        // 현재 방향과 속도를 반영해 새로운 위치 계산
        int r = (fb.r + directions[fb.direction][0] * fb.speed) % n;
        int c = (fb.c + directions[fb.direction][1] * fb.speed) % n;

        // 음수일 경우, 맵 크기만큼 더해주어 반대편으로 돌아오게 함
        if (r < 0) r += n;
        if (c < 0) c += n;

        // 새로운 위치에 파이어볼 추가
        board[r][c].add(new Fireball(r, c, fb.mass, fb.speed, fb.direction));
    }

    // 모든 파이어볼의 방향이 짝수 또는 홀수인지 확인
    public static boolean isAllEvenOrOdd(List<Integer> ds) {
        int evenCount = 0, oddCount = 0;
        for (int d : ds) {
            if (d % 2 == 0) evenCount++; 
            else oddCount++;
        }
        // 모든 방향이 짝수이거나 모든 방향이 홀수일 경우 true 반환
        return evenCount == ds.size() || oddCount == ds.size();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt(); // 맵의 크기
        int m = sc.nextInt(); // 파이어볼 개수
        int k = sc.nextInt(); // 이동 횟수

        // 보드 초기화 (n x n 크기의 리스트 배열 생성)
        board = new ArrayList[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                board[i][j] = new ArrayList<>(); 
            }
        }

        // 파이어볼 정보 입력 받기
        for (int i = 0; i < m; i++) {
            int r = sc.nextInt() - 1; 
            int c = sc.nextInt() - 1; 
            int mass = sc.nextInt(); // 질량
            int speed = sc.nextInt(); // 속도
            int direction = sc.nextInt(); // 방향
            fireballs.add(new Fireball(r, c, mass, speed, direction)); 
        }
        
        for (int step = 0; step < k; step++) {
            // 파이어볼 이동
            while (!fireballs.isEmpty()) {
                move(fireballs.remove(fireballs.size() - 1)); 
            }

            // 파이어볼 병합 및 분리
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (board[i][j].size() == 1) { // 파이어볼이 하나만 있는 경우
                        fireballs.add(board[i][j].remove(0)); // 그대로 fireballs에 추가
                    } else if (board[i][j].size() >= 2) { // 파이어볼이 두 개 이상 있는 경우
                        int totalMass = 0, totalSpeed = 0;
                        List<Integer> directions = new ArrayList<>(); // 파이어볼의 방향을 저장
                        int size = board[i][j].size(); // 해당 위치의 파이어볼 개수

                        // 파이어볼 병합
                        while (!board[i][j].isEmpty()) {
                            Fireball fb = board[i][j].remove(0);
                            totalMass += fb.mass; 
                            totalSpeed += fb.speed; 
                            directions.add(fb.direction); 
                        }

                        totalMass /= 5; // 새로운 파이어볼의 질량 계산
                        if (totalMass > 0) { // 질량이 0이 아닌 경우에만 파이어볼 생성
                            int avgSpeed = totalSpeed / size;
                            boolean allEvenOrOdd = isAllEvenOrOdd(directions); // 방향이 모두 짝수 또는 홀수인지 확인
                            int[] newDirections = allEvenOrOdd ? new int[]{0, 2, 4, 6} : new int[]{1, 3, 5, 7}; // 새로운 방향 설정

                            // 네 개의 새로운 파이어볼 생성
                            for (int d : newDirections) {
                                fireballs.add(new Fireball(i, j, totalMass, avgSpeed, d));
                            }
                        }
                    }
                }
            }
        }

        // 결과: 남아있는 파이어볼들의 질량 합 출력
        int totalMass = fireballs.stream().mapToInt(fb -> fb.mass).sum();
        System.out.println(totalMass);
    }
}

