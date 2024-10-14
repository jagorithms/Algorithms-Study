import java.util.*;

class Solution {
    static int N, M;
    static int[] answer;
    static int[] discounts = new int[]{10,20,30,40};
    static int[][] users;
    static int[] emoticons;
    static int[] prices;
        
    public int[] solution(int[][] iusers, int[] iemoticons) {
        answer = new int[]{0,0};
        N = iusers.length;
        M = iemoticons.length;
        users = iusers;
        emoticons = iemoticons;
        
        prices = new int[N];
        
        dfs(0);
        
        return answer;
    }
    
    void dfs(int index){
        //System.out.println(Arrays.toString(emoticons));
        if(index == M){
            int people = 0;
            int price = 0;
            
            for(int i=0; i<N; i++){
                if(prices[i]>=users[i][1]){
                    people+=1;
                }else{
                    price+=prices[i];
                }
            }
            if(people>answer[0]){
                answer = new int[]{people, price};
            }
            if(people==answer[0] && price>answer[1]){
                answer = new int[]{people, price};
            }
            return;
        }
        for(int discount : discounts){
            for(int i=0; i<N; i++){
                if(discount>=users[i][0]){
                    prices[i]+=(100-discount)*emoticons[index]/100;
                }
            }
            
            dfs(index+1);
            
            for(int i=0; i<N; i++){
                if(discount>=users[i][0]){
                    prices[i]-=(100-discount)*emoticons[index]/100;
                }
            }
        }
    }
}
