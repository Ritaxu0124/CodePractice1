package DataStructure.search;

import java.util.Arrays;

/**
 * @author Rita
 * 斐波那契查找算法
 * 任然是针对有序数组
 */
public class FibonacciSearch {

    static int maxSize = 20;
    public static void main(String[] args) {
        int[] arr = {1, 8, 10, 89, 1000, 1234};
        int index = fibSearch(arr, 8);
        if (index == -1){
            System.out.println("找不到该数字！");
        }else {
            System.out.println("index = " + index);
        }
    }

    //非递归方法构造斐波那契数列
    public static int[] fib(){
        int[] f = new int[maxSize];
        f[0] = 1;
        f[1] = 1;
        for (int i = 2; i < f.length; i++){
            f[i] = f[i - 1] + f[i -2];
        }
        return f;
    }

    /**
     * 斐波那契查找算法,非递归方式实现
     * @param arr   数组
     * @param findVal   所要查找数值
     * @return  返回的下标
     */
    public static int fibSearch(int[] arr, int findVal){
        int low = 0;
        int high = arr.length - 1;
        int k = 0;  //斐波那契分割数值的下标
        int mid = 0;    //存放mid值
        int[] f = fib();    //获取斐波那契数列
        //获取斐波那契分割数值的下标
        while (high > f[k] - 1){
            k++;
        }
        //因为f[k]的数值可能会大于arr的长度，所以要构造一个长度为f[k]的新数组
        int[] temp = Arrays.copyOf(arr, f[k]);
        //不足的部分需要用arr的最后一个数值填充
        for (int i = high + 1; i < temp.length; i++){
            temp[i] = arr[high];
        }

        //寻找findVal
        while (low <= high){
            mid = low + f[k - 1] - 1;
            if (temp[mid] > findVal){   //继续向左边查找
                high = mid - 1;
                //为什么是 k--的说明
                //1. 全部元素 = 前面的元素 + 后边元素
                //2. f[k] = f[k-1] + f[k-2]
                //因为 前面有 f[k-1]个元素,所以可以继续拆分 f[k-1] = f[k-2] + f[k-3]
                //即 在 f[k-1] 的前面继续查找 k--
                //即下次循环 mid = f[k-1-1]-1
                k--;
            }else if (arr[mid] < findVal){  //继续向右查找
                low = mid + 1;
                //右边元素右f[k-2]个，所以可以继续拆分 f[k-2] = f[k-3] + f[k-4]
                //即下次循环mid =low + f[k-2-1] - 1;
                k -= 2;
            }else { //找到
                //需要确实是哪个下标
                if(mid <= high){
                    return mid;
                }else {
                    return high;
                }
            }
        }
        return -1;
    }
}
