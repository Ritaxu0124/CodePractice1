package DataStructure.recursion;

/**
 * 八皇后问题
 */
public class EightQueue {
    //定义皇后数量
    int max = 8;
    //定义数组arr[max]表示皇后放置位置的结果,比如 arr = {0 , 4, 7, 5, 2, 6, 1, 3}
    int[] array = new int[max];
    static int count = 0;
    static int judgeCount = 0;
    public static void main(String[] args) {
        EightQueue queue = new EightQueue();
        queue.check(0);
        System.out.printf("共有%d种解法", count);
        System.out.printf("共判断了%d次", judgeCount);
    }

    /**
     * 编写一个方法放置第n个皇后
     * 注意：check是每一次递归时，进入到check中都会进行一次循环，因此可是实现回溯
     * 事实上在执行过程中大循环嵌套了很多小循环
     * @param n
     */
    private void check(int n){
        if (n == max){
            print();
            count++;
            return;
        }
        for (int i = 0; i < max; i++){
            //先把当前这个皇后n放到该行的第i列
            array[n] = i;
            //判断是否符合放置规则
            if (judge(n)){
                check(n + 1);
            }
            //如果不符合规则的话，就继续执行循环，放下一列继续
        }
    }

    /**
     * 判断前n行是否符合放置规则
     * @param n 表示第n个皇后
     * @return
     */
    private boolean judge(int n){
        judgeCount++;
        for (int i = 0; i < n; i++){
            //1. array[i] == array[n] 表示是否在同一列
            //2. Math.abs(n - i) == Math.abs(array[n] - array[i])表示是否在同一斜线
            if (array[i] == array[n] || Math.abs(n - i) == Math.abs(array[n] - array[i])){
                return false;
            }
        }
        return true;
    }

    //将皇后放置的位置输出
    private void print(){
        for (int i = 0; i < array.length; i++){
            System.out.print(array[i] + " ");
        }
        System.out.println();
    }
}
