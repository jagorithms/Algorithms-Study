class Solution {
    private static int m; // 이모티콘 개수
    private static final int[] discountRate = { 10, 20, 30, 40 };
    
    private static int maxSubscriber = 0;
    private static int maxSellAmount = 0;
    
    // 모든 이모티콘에 대하여 모든 할인율 적용해보기
    // (완전 탐색 : 4^7 = 16,384)
    private static void completeSearch(int nth, int[][] users, int[] emoticons, int[] emoticonsDiscount) {
        if (nth == m) {
            // 모든 유저 이모티콘 구매
            // 최대 플러스 가입자와 최대 판매액 갱신
            buy(users, emoticons, emoticonsDiscount);
            return;
        }
        
        for (int rate : discountRate) {
            emoticonsDiscount[nth] = rate;
            completeSearch(nth + 1, users, emoticons, emoticonsDiscount);
        }
    }
    
    private static void buy(int[][] users, int[] emoticons, int[] emoticonsDiscount) {
        int subscriber = 0;
        int sellAmount = 0;
        
        for (int[] user : users) {
            int rate = user[0];
            int price = user[1];
            
            int sum = 0; // 한 유저의 구매 비용
            
            for (int i = 0; i < m; i++) {
                // 해당 비율 이상 할인하면 이모티콘 구매
                if (rate <= emoticonsDiscount[i]) {
                    sum += emoticons[i] - (int) (emoticons[i] * (emoticonsDiscount[i] / 100.0));
                }
                // 해당 가격 이상이면 이모티콘 플러스 가입
                if (sum >= price) {
                    subscriber++;
                    sum = 0;
                    break;
                }
            }
            
            sellAmount += sum;
        }
        
        // 최대 플러스 가입자와 최대 판매액 갱신
        if (maxSubscriber < subscriber) {
            maxSubscriber = subscriber;
            maxSellAmount = sellAmount;
        } else if (maxSubscriber == subscriber) {
            maxSellAmount = Math.max(maxSellAmount, sellAmount);
        }
    }
    
    public int[] solution(int[][] users, int[] emoticons) {
        // 전역 변수 설정(이모티콘 수)
        m = emoticons.length;
        int[] emoticonsDiscount = new int[m];
        completeSearch(0, users, emoticons, emoticonsDiscount);
        
        int[] answer = { maxSubscriber, maxSellAmount };
        return answer;
    }
}
