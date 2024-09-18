import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        int result = 0;
        int maxh = 0;

        List<int[]> li = new ArrayList<>();


        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            li.add(new int[]{Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())});
            maxh = Math.max(maxh, li.get(i)[1]);
        }

        Collections.sort(li, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0]-o2[0];
            }
        });

        int preh = 0;
        int prex = 0;

        int i = 0;
        for (i = 0; i < n; i++) {
            int x = li.get(i)[0], h = li.get(i)[1];
            result += (x-prex)*preh;
            prex = x;
            preh = Math.max(preh,h);
            if(h == maxh)
                break;
        }
        
        li = li.subList(i, li.size());
        Collections.reverse(li);
        
        preh = 0;
        prex = 1000;
        
        n = li.size();
        for (i = 0; i < n; i++) {
        	int x = li.get(i)[0], h = li.get(i)[1];
        	result += (prex-x)*preh;
        	prex = x;
        	preh = Math.max(preh,h);
        }
        
        sb.append(result + maxh);


        System.out.println(sb);
    }
}
