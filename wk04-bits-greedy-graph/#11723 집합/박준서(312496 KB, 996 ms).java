import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static int M;
	private static int S = 0;
	private static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws IOException {
		M = Integer.parseInt(br.readLine());
		for(int i = 0; i < M; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			String command = st.nextToken();
			
			int num = 0;
            if (!command.equals("all") && !command.equals("empty")) {
                num = Integer.parseInt(st.nextToken());
            }
			
			if(command.equals("add")) {
				S = S | (1 << num);
			} else if(command.equals("remove")) {
				S = S & ~(1 << num);
			} else if(command.equals("check")) {
				if((S & (1 << num)) != 0) {
					sb.append(1).append("\n");
				} else {
					sb.append(0).append("\n");
				}
			} else if(command.equals("toggle")) {
				S = S ^ (1 << num);
			} else if(command.equals("all")) {
				S = (1 << 21) -1;
			} else if(command.equals("empty")) {
				S = 0;
			}
		}
		
		System.out.println(sb);
	}

}
