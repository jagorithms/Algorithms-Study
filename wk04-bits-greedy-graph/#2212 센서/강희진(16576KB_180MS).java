
import java.util.*;
import java.io.*;
public class Main{
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int k = Integer.parseInt(br.readLine());
        int[] sensors = new int[n];
        String[] input = br.readLine().split(" ");
        for (int i = 0; i < n; i++) {
            sensors[i] = Integer.parseInt(input[i]);
        }

        Arrays.sort(sensors);

        int[] differences = new int[n];
        for (int i = 0; i < n-1; i++) {
            differences[i] = sensors[i+1]-sensors[i];
        }

        Arrays.sort(differences);
        int sum = 0;
        for (int i = 0; i < n - (k-1); i++) {
            sum += differences[i];
        }
        System.out.println(sum);
        br.close();
    }
}
