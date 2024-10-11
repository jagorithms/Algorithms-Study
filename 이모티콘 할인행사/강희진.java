class Solution {
    private static final int[] discounts = {10, 20, 30, 40};
    private static int subscribers, profit;
    public int[] solution(int[][] users, int[] emoticons) {
        int[] answer = new int[2];
        genComb(0, new int[emoticons.length], users, emoticons);
        answer[0] = subscribers;
        answer[1] = profit;
        
        
        return answer;
    }
    
    private void genComb(int depth, int[] res, int[][] users, int[] emoticons) {
        if (depth == emoticons.length) {
            int subs = 0, spentTotal = 0;
            for (int[] user : users) {
                int spent = 0;
                for (int i = 0; i < emoticons.length; i++) {
                    if (res[i] >= user[0]) {
                        spent += emoticons[i] * (100 - res[i]) / 100;
                    }
                }
                if (spent >= user[1]) {
                    subs ++;
                } else {
                    spentTotal += spent;
                }
            }
            if (subs > subscribers) {
                subscribers = subs;
                profit = spentTotal;
            } else if (subs == subscribers) {
                profit = Math.max(profit, spentTotal);
            }
            return;
        }
        
        for (int i = 0; i < discounts.length; i++) {
            res[depth] = discounts[i];
            genComb(depth + 1, res, users, emoticons);
        }
    }
}
