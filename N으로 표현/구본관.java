import java.util.*;

class Solution {
    static int[] el = {0,1,11,111,1111,11111,111111,1111111,11111111};
    public int solution(int N, int number) {
        int answer = 0;
        Set<Integer>[] setli = new HashSet[9];
        setli[0] = new HashSet<Integer>();
        setli[0].add(0);
        
        int i=0;
        for(i=1; i<=8; i++){
            Set<Integer> set = new HashSet();
            set.add(N*el[i]);
            
            for(int t=0; t<i; t++){
                int num = N*el[i-t];
                //System.out.println(num);
                cal(setli[t], num, set);
            }
            if(set.contains(number)){
                break;
            }
            setli[i] = set;
        }
        if(i>8){
            return -1;
        }
        return i;
    }
    
    static void cal(Set<Integer> set1, int num, Set<Integer> set2){
        for(int s : set1){
            set2.add(s+num);
            set2.add(s*num);
            set2.add(s-num);
            set2.add(num-s);
            if(num!=0){
                set2.add(s/num);
            }
            if(s!=0){
                set2.add(num/s);
            }
        }
    }
}
