import java.util.HashSet;
import java.util.Set;
import java.util.ArrayList;
import java.util.List;

class Solution {
    public int solution(int N, int number) {
        if (N == number) {
            return 1;
        }
        
        List<Set<Integer>> dp = new ArrayList<>();
        
        for (int i = 0; i <= 8; i++) {
            dp.add(new HashSet<>());
        }
        
        dp.get(1).add(N);
        
        for (int i = 2; i <= 8; i++) {
            int repeatNum = Integer.parseInt(String.valueOf(N).repeat(i));
            dp.get(i).add(repeatNum);
            
            for (int j = 1; j < i; j++) {                
                for (int num1 : dp.get(j)) {
                    for (int num2 : dp.get(i - j)) {
                        dp.get(i).add(num1 + num2);
                        dp.get(i).add(num1 - num2);
                        dp.get(i).add(num1 * num2);
                        if (num2 != 0) {
                            dp.get(i).add(num1 / num2);   
                        }
                    }
                }
            }
        }
        
        for (Set<Integer> set : dp) {
            if (set.contains(number)) {
                return dp.indexOf(set);
            }
        }
        
        return -1;
    }
}
