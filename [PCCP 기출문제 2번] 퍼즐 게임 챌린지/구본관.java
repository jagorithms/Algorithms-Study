class Solution {
    public int solution(int[] diffs, int[] times, long limit) {
        int answer = 0;
        int N = diffs.length;
        
        int left = 1;
        int right = maxint(diffs);
        
        while(left<=right){
            long time = 0;
            int mid = (left + right)/2;
            for(int i=0; i<N; i++){
                if(diffs[i]<=mid){
                    time+=times[i];
                }else{
                    time+=(diffs[i]-mid+1)*times[i];
                    if(i>0){
                        time+=(diffs[i]-mid)*times[i-1];
                    }
                }
                if(time>limit){
                    break;
                }
            }
            if(time<=limit){
                answer = mid;
                right = mid-1;
            }else{
                left = mid+1;
            }
        }
        
        
        return answer;
    }
    
    public int maxint(int[] diffs){
        int maxi = 0;
        for(int i : diffs){
            maxi = Math.max(maxi,i);
        }
        return maxi;
    }
}
