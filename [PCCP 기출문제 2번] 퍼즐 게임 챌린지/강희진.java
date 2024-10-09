class Solution {
    public int solution(int[] diffs, int[] times, long limit) {
        int answer = 0;
        
        int start = 1, end = 100000;
        while (start <= end) {
            int mid = start + (end - start)/2;
            if (check(mid, diffs, times, limit)) {
                answer = mid;
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }
        
        return answer;
    }
    
    private boolean check(int mid, int[] diffs, int[] times, long limit) {
        long timeTaken = times[0];
        for (int i = 1; i < diffs.length; i++) {
            if (diffs[i] <= mid) {
                timeTaken += times[i];
            } else {
                timeTaken += (diffs[i] - mid) * (times[i] + times[i-1]) + times[i];             
            }
        }
        return limit >= timeTaken;
    }
}
