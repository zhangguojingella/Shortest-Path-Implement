import java.util.*;
class List{
    private LinkedList<Integer> list1;
    private LinkedList<Integer> list;
    private int num;//几个点
    private int l;//list长度
    private int[] temp;
    private LinkedList<LinkedList<Integer>> res;
    public List(int n){
        num = n;
        l = n*(n-1)/2;
    }
    public int random(int s,int e){
        int rand = s + (int)(Math.random() * (e-1+1));
        return rand;
    }
    public LinkedList<LinkedList<Integer>> Complete(){
        LinkedList<Integer> list1 = new LinkedList<>();//index
        LinkedList<Integer> list = new LinkedList<>();//value
        LinkedList<LinkedList<Integer>> res = new LinkedList<>();
        int index = 0;
        while(index < l){
            list.add(random(1,num));
            list1.add(index);
            index++;
        }
        res.add(list);
        res.add(list1);
        return res;
    }
    public LinkedList<LinkedList<Integer>> Sparse(){
        LinkedList<Integer> list1 = new LinkedList<>();//index
        LinkedList<Integer> list = new LinkedList<>();//value
        LinkedList<LinkedList<Integer>> res = new LinkedList<>();
        Set<Integer> set = new HashSet<>();
        int[] temp = generateRandom();
        int x = temp[0];
        int y = temp[1];
        int index = (x*(x-1))/2 + y;//x,y对应的点
        //System.out.println(index);
        set.add(y);
        set.add(x);
        //System.out.println(set);
        list1.add(index);
        list.add(random(1,num-1));
        while(set.size() < num){
            int te = 0;
            Object[] setArray = set.toArray();
            int tempx  = random(0, setArray.length);
            int t = (int)setArray[tempx];//set里随机一个数
            for(int i = 1;i<num;i++){
                if(!set.contains(i)){
                    te = i;
                    break;
                }
            }//te是不在set里的一个数
            if(t > te){
                x = t;
                y = te;
                set.add(te);
                index = (x*(x-1))/2 + y;
                list1.add(index);
                list.add(random(1,num-1));
            }
            else if(t < te){
                x = te;
                y = t;
                set.add(te);
                index = (x*(x-1))/2 + y;
                list1.add(index);
                list.add(random(1,num-1));
            }
        }
        res.add(list);//value
        res.add(list1);//index
        return res;
        //list1里是int[], 第一个数是(i-1)(i-2)/2+j对应的数字，第二个数是weight.
    }
    public int[] generateRandom(){
        int[] temp = new int[2];
        int x = random(1,num-1);//x,竖着的那行
        int y = random(1,num-1);//y,横着的那行,x一定比y大
        if(x > y){
            temp[0] = x;
            temp[1] = y;
            return temp;
        }
        else
            return generateRandom();
    }
}
class Path2{
    private LinkedList<Integer> list;
    private int[] OneArray;
    private Set<Integer> allNum = new HashSet<>();
    private int startPoint;
    private int num;//有几个数
    private int l;//array的length
    public Path2(LinkedList l,int y){//y作为有几个数参入
        list = l;
        num = y;
        OneArray = new int[list.size()];
        int i = 0;
        for (int x : list)  
            OneArray[i++] = x;
    }
    public void DijkAlg(int start){
        int[][] dimArray = fill(OneArray);
        
        //System.out.println(dimArray);
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
    public int[][] fill(int[] OneArray ){
        int[][] dimArray = new int[num][num];
        for(int i = 1;i<num;i++){
            for (int j = 0; j < i ;j++){
                int index = i*(i-1)/2+j;//i,j对应array的index
                int value = OneArray[index];
                dimArray[i][j] = value;
                dimArray[j][i] = value;
            }
        }
        return dimArray;
    }
    public void FloydAlg(){
        for (int k = 0; k < num; k++) {
            for (int i = 1; i < num;i++){
                for (int j = 0; j < i ;j++){
                    int index = i*(i-1)/2+j;//i,j对应array的index
                    int value = OneArray[index];
                    
                    if(k>i){
                        int ki = k*(k-1)/2+i;//k到i
                        int kiv = OneArray[ki];
                        int kj = k*(k-1)/2+j;//k到j
                        int kjv = OneArray[kj];
                        if(kiv!=0&&kjv!=0){
                            if (kiv+kjv < value) {
                                OneArray[index] = kiv+kjv;
                            }
                            if(value == 0)
                                OneArray[index] = kiv+kjv;
                        }
                    }
                    else if(k<i&&k>j){
                        int ik = i*(i-1)/2+k;//i到k
                        int ikv = OneArray[ik];
                        int kj = k*(k-1)/2+j;//k到j
                        int kjv = OneArray[kj];
                        if(ikv!=0&&kjv!=0){
                            if (ikv+kjv < value) {
                                OneArray[index] = ikv+kjv;
                            }
                            if(value == 0)
                                OneArray[index] = ikv+kjv;
                        }
                    }
                    else if(k<j){
                        if(j == 0 )//j等于0的情况下k就出错了。
                            ;
                        else{
                            int jk = j*(j-1)/2+k;
                            //System.out.println(i);
                            int jkv = OneArray[jk];
                            int ik = i*(i-1)/2+k;//i到k
                            int ikv = OneArray[ik];
                            if(ikv!=0&&jkv!=0){
                                if (ikv+jkv < value) {
                                    OneArray[index] = ikv+jkv;
                                }
                                if(value == 0)
                                    OneArray[index] = ikv+jkv;
                            }
                        }
                    }
                }
            }
        }
        for(int i = 0;i<OneArray.length;i++){
            System.out.print(OneArray[i] + " ");
            
        }
    }
}
public class linkedlist {
    public static void main(String[] args) {
        int[] test1 = {
            100,
            100,100,
            29,100,12,
            100,100,100,5,
            100,11,5,100,100,
            100,11,5,13,7,100,
            100,100,100,100,11,17,100
        };//算法test case 1
        LinkedList<Integer> l = new LinkedList<>();
        for(int i : test1){
            l.add(i);
        }
        int n = 8;
        Path2 p = new Path2(l,n);
        System.out.println(" From point "+ 2 + " to all point");
        p.DijkAlg(1);
        System.out.println("The result of Floyd’s algorithm: ");
        p.FloydAlg();
        System.out.println(" ");
        System.out.println("The result of test2: ");
        int[] test2 = {
            
            11
            ,14,12
            ,0,0,18
            ,8,6,13,0
            ,0,0,13,0,0
            ,29,0,0,27,0,0,
            28,0,0,17,0,15,0,
            0,0,25,9,0,5,0,5,
            0,0,0,25,0,0,0,9,0,
            14,0,0,0,0,0,0,0,25,0,
            0,0,16,0,22,0,0,0,0,0,0
        };
        int n2 = 12;
        LinkedList<Integer> l1 = new LinkedList<>();
        for(int i : test2){
            l1.add(i);
        }
        Path2 p2 = new Path2(l1,n2);
        System.out.println("From point "+ 2 + " to all point");
        p2.DijkAlg(1);
        System.out.println("The result of Floyd’s algorithm: ");
        p2.FloydAlg();
    }
}
