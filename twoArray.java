import java.util.*;

class DimArray{
    private int[][] dimArray;
    private int num;
    private Set<Integer> set = new HashSet<>();
    public DimArray(int n){
        num = n;
        dimArray = new int[num][num];
    }
    public int[][] Complete(){
        for(int i = 0;i<dimArray.length;i++){
            for(int j = i;j<dimArray[i].length;j++){
                if(i==j)
                    dimArray[i][j] = 0;
                else{
                    int y = random(1,num);
                    dimArray[i][j] = y;
                    dimArray[j][i] = y;
                }
            }
        }
        return dimArray;
    }//yeah~~ correct!next step!
    public int[][] Sparse(){
        Set<Integer> set = new HashSet<>();
        int[] firstPosition = generateRandom();
        int temp = firstPosition[0];
        int temp1 = firstPosition[1];
//        System.out.println(Arrays.toString(firstPosition));
        int s = random(1,num);
        dimArray[temp][temp1] = s;
        dimArray[temp1][temp] = s;
        set.add(temp);
        set.add(temp1);
//        System.out.println(set);
        while(set.size() < num){
            int y = 0;
            //System.out.println(set.size());
            Object[] setArray = set.toArray();
            int tempx  = random(0, setArray.length);
            int x = (int)setArray[tempx];
            for(int i = 0;i<num;i++){
                if(!set.contains(i)){
                    y = i;
                    break;
                }
            }
            int r = random(1,num);
            dimArray[x][y] = r;
            dimArray[y][x] = r;
            set.add(y);
        }
        return dimArray;
    }
    public int[] generateRandom(){
        int[] res = new int[2];
        int temp = random(0,num-1);//横可以填除第一格
        int temp1 = random(0,num-1);//竖着填除最后一格
        if(temp == temp1){
            return generateRandom();
        }
        else{
            res[0] = temp;
            res[1] = temp1;
            return res;
        }
    }
    public int random(int s,int e){
        int rand = s + (int)(Math.random() * (e-1+1));
        return rand;
    }
}
class Path{
    private int[][] dimArray;
    private Set<Integer> allNum = new HashSet<>();
    private int startPoint;
    private int num;
    public Path(int[][] x){
        dimArray = x;
        num = dimArray.length;
    }
    public void DijkAlg(int[][] dimArray,int start){
        for(int i = 0;i<num;i++)
            allNum.add(i);
        Set<Integer> containNum = new HashSet<>();
        //int startPoint;
        int dist = Integer.MAX_VALUE;
        int endPoint = 0;
        int i = start;//注意i改回0
        Map<Integer,Integer> map = new HashMap<>();
        startPoint = i;//1
        containNum.add(startPoint);//0
        for(int j = 0;j<dimArray[i].length;j++){
            if(dimArray[i][j] == 0)
                map.put(j, Integer.MAX_VALUE);
            if(dimArray[i][j] != 0){
                map.put(j, dimArray[i][j]);
                if(dist>dimArray[i][j]){
                    dist = dimArray[i][j];
                    endPoint = j;
                }
            }  
        }
        containNum.add(endPoint);
        //endpoint就是新加进去的点
        while(containNum.size()!=allNum.size()){
        for(int all:allNum){
            if(!containNum.contains(all)){
                int index = endPoint;
                //endpoint更新为2
                for(int x = 0;x <dimArray[index].length;x++){
                    if(dimArray[index][x] != 0){//[5][0] != 0 
                        int now = dimArray[index][x] + map.get(endPoint);//get(5)是1加6 7
                        //6+map.get(2) = 3 = 9
                        if(now<map.get(x))//x = 5
                            map.put(x, now);//1到2更新为7
                    }
                }//遍历加进去的那个点的那行
                //containNum.add(index);
                Set<Integer> keys = map.keySet();
                Collection<Integer> col = map.values();//找到当前map里最小的数
                int min = Integer.MAX_VALUE;
                int minKey = 0;
                for(int key : keys){
                    int n = map.get(key);
                    //System.out.println(n);
                    if(n < min&&(!containNum.contains(key))){
                        min = n;//map里最小的数
                        minKey = key;//该数对应的截止点，即endpoint
                        //System.out.println(n);

                    }//把2加进去了

                }//System.out.println(" ");
                containNum.add(minKey);
                //System.out.println(minKey);
                endPoint = minKey;//endpoint更新为刚加进去的点
            }
        }
        map.put(i, 0);
//        containNum.removeAll(allNum);
        }
        System.out.println(map);
    }
    public int[][] dijPoint(int start, int end, int [][] graph){
        int size = graph.length;
        int [][] touch = new int [size][size];
        int [][] length = new int [size][size];
        for(int i = 0; i < size; i ++){      
                for(int j = 0; j < size; j ++){
                        length[i][j] = graph[i][j];
                }
        }
        int temp = Integer.MIN_VALUE;
        for(int i = 0; i < graph.length; i++) {
            for(int j = 0; j < graph.length; j++) {
                if(i == j) {
                    touch[i][j] = 0;
                } else {
                    touch[i][j] = i;
                }
            }
            for (int m = 0; m < size - 1; m++) {
                int min = Integer.MAX_VALUE;
                for (int n = 0; n < graph.length; n++) {
                    if (length[i][n] == 0) {
                            continue;
                    } else {
                        if(length[i][n] > 0&&length[i][n] <= min){
                            min = graph[i][n];
                            temp = n;
                        }
                    }
                }
                for (int p = 0; p < size; p++) {
                    if (p == i || p == temp) {
                            continue;
                    } 
                    else {
                        if (graph[temp][p] == 0 || graph[i][temp] == 0){
                            continue;
                        } if (graph[i][p] == 0) {
                            graph[i][p] = graph[i][temp]+ graph[temp][p];
                            length[i][p] = graph[i][temp] + graph[temp][p];
                            touch[i][p] = temp;
                        } 
                        else {
                            if (graph[i][p] > graph[i][temp] + graph[temp][p]) {
                                graph[i][p] = graph[i][temp] + graph[temp][p];
                                length[i][p] = graph[i][temp] + graph[temp][p];
                                touch[i][p] = temp;
                            }
                        }
                    }
                }
            length[i][temp] = 0;
            }
        }	
        System.out.println("The Shortest Path from v" + start + " to v" + end + " :");
        printDijkstra(touch, start - 1,end - 1);
        System.out.println("v" + end);
        System.out.println("The Shortest Distance from v" + start + " to v" + end + " :");
        System.out.println(graph[start - 1][end - 1]);
        return graph;
    }
    public static void printDijkstra(int[][] Path, int i, int j) {
        if (Path[i][j] != 0) {
            printDijkstra(Path, i, Path[i][j]);
            System.out.print("v" + (Path[i][j] + 1) + "->");
        }
    }               
    public int findV(int i,int n){
        int min = 0; 
        int max = 0;
        if (i > n) {
            max = i;
            min = n;
        } else {
            max = n;
            min = i;
        }
        return max * (max - 1) / 2 + min;
    }
    public void FloydAlg(int[][] dimArray){
        for (int k = 0; k < num; k++) {
            for (int i = 0; i < num; i++) {
                for (int j = 0; j < num; j++) {
                    if( i != j){
                        if(dimArray[i][k] != 0 && dimArray[k][j] != 0){
                            if (dimArray[i][k] + dimArray[k][j] < dimArray[i][j]) {
                                dimArray[i][j] = dimArray[i][k] + dimArray[k][j];
                            }
                            if(dimArray[i][j]==0){
                                dimArray[i][j] = dimArray[i][k] + dimArray[k][j];
                            }
                        }
                    }
                }
            }
        }
        for(int i = 0;i<dimArray.length;i++){
            for(int j = 0;j<dimArray[i].length;j++){
                System.out.print(dimArray[i][j]+" ");
            }
            System.out.println(" ");
        }
    }
}

public class twoArray {
    public static void main(String[] args) {
        
       int[][] test1 = {   {0,100,100,29,100,100,100,100},
       {100,0,100,100,100,11,11,100},
       {100,100,0,12,100,5,5,100},
       {29,100,12,0,5,100,13,100},
       {100,100,100,5,0,100,7,11},
       {100,11,5,100,100,0,100,17},
       {100,11,5,13,7,100,0,100},
       {100,100,100,100,11,17,100,0}
        };//算法test case 1
        System.out.println("The result of Dijkstra’s algorithm: ");
        Path d = new Path(test1);
        int[][] y = d.dijPoint(1, 8, test1);
        int x = test1.length;
        for(int i = 0;i<x;i++){
            d.DijkAlg(test1,i);
        }
        
        System.out.println("The result of Floyd’s algorithm: ");
        d.FloydAlg(test1);
        
        System.out.println("The result of test2: ");
        int[][] test2 = {
            {0,11,14,0,8,0,29,28,0,0,14,0},
            {11,0,12,0,6,0,0,0,0,0,0,0},
            {14,12,0,18,13,13,0,0,25,0,0,16},
            {0,0,18,0,0,0,27,17,9,25,0,0},
            {8,6,13,0,0,0,0,0,0,0,0,22},
            {0,0,13,0,0,0,0,15,5,0,0,0},
            {29,0,0,27,0,0,0,0,0,0,0,0},
            {28,0,0,17,0,15,0,0,5,9,0,0},
            {0,0,25,9,0,5,0,5,0,0,25,0},
            {0,0,0,25,0,0,0,9,0,0,0,0},
            {14,0,0,0,0,0,0,0,25,0,0,0},
            {0,0,16,0,22,0,0,0,0,0,0,0}
        };
        int n1 = 12;
        Path d1 = new Path(test2);
        int l = test2.length;
        for(int i = 0;i<l;i++){
            d1.DijkAlg(test2,i);
        }
        
        System.out.println("The result of Floyd’s algorithm: ");
        d1.FloydAlg(test2);
    
    }
}
