class Solution {
    // 한 문제 풀기
    private static int solve(int level, int diff, int curTime, int prevTime) {
        int time = 0;
        
        int fail = diff - level;
        if (fail > 0) {
            time += (curTime + prevTime) * fail;
        }
        
        time += curTime;
        return time;
    }
    
    // 모든 문제 풀기
    // 제한 시간 내에 풀 수 있으면 true, 그렇지 않으면 false 반환
    private static boolean solveAll(int level, int[] diffs, int[] times, long limit) {
        // 총 문제 개수
        int n = diffs.length;
        // 총 소요 시간
        long timeSum = times[0];
        // 모든 문제 풀기
        for (int i = 1; i < n; i++) {
            timeSum += solve(level, diffs[i], times[i], times[i-1]);
            if (timeSum > limit) {
                return false;
            }
        }
        
        return true;
    }
    
    // 이진 탐색으로 숙련도의 최솟값 찾기
    private static int binarySearch(int left, int right, int[] diffs, int[] times, long limit) {
        int answer = 0;
        
        while (left <= right) {
            int mid = (left + right) / 2;
            
            if (solveAll(mid, diffs, times, limit)) {
                answer = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        
        return answer;
    }
    
    public int solution(int[] diffs, int[] times, long limit) { 
        int answer = binarySearch(1, 100000, diffs, times, limit);
        return answer;
    }
}
