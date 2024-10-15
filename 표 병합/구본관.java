import java.util.*;

class Solution {
    static int[] parent = new int[100000];
    static String[][] graph = new String[51][51];
    
    public String[] solution(String[] commands) {
        List<String> answer = new ArrayList<>();
        
        for(int y=1; y<=50; y++){
            for(int x=1; x<=50; x++){
                parent[y*50+x] = y*50+x;
            }
        }
        
        for(String command : commands){
            String[] com = command.split(" ");
            Init();
            
            switch (com[0]){
                case "UPDATE": 
                    if(com.length == 4){
                        int updateY = Integer.parseInt(com[1]);  // 변수명 변경
                        int updateX = Integer.parseInt(com[2]);  // 변수명 변경
                        String value = com[3];
                        Update(updateY, updateX, value);
                    } else {
                        String value1 = com[1];
                        String value2 = com[2];
                        Update(value1, value2);
                    }
                    break;
                case "MERGE": 
                    int y1 = Integer.parseInt(com[1]);
                    int x1 = Integer.parseInt(com[2]);
                    int y2 = Integer.parseInt(com[3]);
                    int x2 = Integer.parseInt(com[4]);
                    Merge(y1, x1, y2, x2);
                    break;
                case "UNMERGE":
                    int unmergeY = Integer.parseInt(com[1]);  // 변수명 변경
                    int unmergeX = Integer.parseInt(com[2]);  // 변수명 변경
                    UnMerge(unmergeY, unmergeX);
                    break;
                case "PRINT":
                    int printY = Integer.parseInt(com[1]);  // 변수명 변경
                    int printX = Integer.parseInt(com[2]);  // 변수명 변경
                    answer.add(Print(printY, printX));
                    break;
            }
        }
        
        
        return answer.toArray(new String[0]);
    }
    
    void Update(int sy, int sx, String value){
        int syx = find(sy*50+sx);
        
        for(int y=1; y<=50; y++){
            for(int x=1; x<=50; x++){
                if(find(y*50+x) == syx){
                    graph[y][x] = value;
                }
            }
        }
    }
    
    void Update(String value1, String value2){
        for(int y=1; y<=50; y++){
            for(int x=1; x<=50; x++){
                if(value1.equals(graph[y][x])){
                    graph[y][x] = value2;
                }
            }
        }
    }
    
    void Merge(int y1, int x1, int y2, int x2){
        if(y1 == y2 && x1 == x2){
            return;
        }
        union(y1*50+x1,y2*50+x2);
        String value = graph[y1][x1];
        if(value==null){
            value = graph[y2][x2];
        }
        Update(y1,x1,value);
    }
    
    void UnMerge(int sy, int sx){
        int syx = find(sy*50+sx);
        String value = graph[sy][sx];
        
        for(int y=1; y<=50; y++){
            for(int x=1; x<=50; x++){
                if(find(y*50+x) == syx){
                    graph[y][x] = null;
                    parent[y*50+x] = y*50+x;
                }
            }
        }
        graph[sy][sx] = value;
    }
    
    String Print(int y, int x){
        if(graph[y][x] == null){
            return "EMPTY";
        }
        return graph[y][x];
    }
    
    void Init(){
        for(int y=1; y<=50; y++){
            for(int x=1; x<=50; x++){
                parent[y*50+x] = find(y*50+x);
            }
        }
    }
    
    int find(int x){
        if(parent[x] == x){
            return x;
        }
        return find(parent[x]);
    }
    
    void union(int x, int y){
        x = find(x);
        y = find(y);
        
        if(x>y){
            parent[x] = y;
        }else{
            parent[y] = x;
        }
    }
}
