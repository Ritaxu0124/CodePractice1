package DataStructure.sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * @author xu
 * 归并排序
 */
public class MergerSort {
    public static void main(String[] args) {
        //一共合并n-1次
//        int[] arr = {8, 4, 5, 7, 1, 3, 6, 2};
//        int[] temp = new int[arr.length];   //需要额外的数组空间
//        mergerSort(arr, 0, arr.length - 1, temp);
//        System.out.println("归并后的数组为：" + Arrays.toString(arr));

        int[] arr = new int[8000000];
        for (int i = 0; i < 8000000; i++) {
            arr[i] = (int) (Math.random() * 1500000);    //生成1-1500000之间的随机数
        }

        Date date1 = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-DD HH:mm:ss");
        String date1Str = simpleDateFormat.format(date1);   //将当前时间格式化
        System.out.println("排序前的时间为：" + date1Str);

        int[] temp = new int[arr.length];   //需要额外的数组空间
        mergerSort(arr, 0, arr.length - 1, temp);

        Date date2 = new Date();
        String date2Str = simpleDateFormat.format(date2);   //将当前时间格式化
        System.out.println("排序后的时间为：" + date2Str);
    }

    public static void mergerSort(int[] arr, int left, int right, int[] temp) {
        if (left < right) {
            int mid = (left + right) / 2;
            //向左递归进行分解
            mergerSort(arr, left, mid, temp);
            //向右递归进行分解
            mergerSort(arr, mid + 1, right, temp);
            //合并
            merge(arr, left, mid,right,temp);
        }
    }

    /**
     * 合并方法
     * @param arr   原始数组
     * @param left  左边有序序列的初始索引
     * @param mid   中间索引
     * @param right 右边索引
     * @param temp  中转数组
     */
    public static void merge(int[] arr, int left, int mid, int right, int[] temp) {
//        System.out.println("xxxxx");    //每打印一次就表示合并了一次
        int i = left;   //初始化i为左边有序序列的初始索引
        int j = mid + 1;    //初始化j为右边有序序列的初始索引
        int t = 0;  //指向temp数组的当前索引

        //第一步
        //先把左右两边的有序数组按照规则填充到temp数组
        //直到左右两边有序序列有一边处理完为止
        while (i <= mid && j <= right) {
            if (arr[i] <= arr[j]) {
                //如果左边的有序序列的当前元素，小于等于右边有序序列的当前元素
                //将左边的当前元素，填充到 temp数组
                temp[t] = arr[i];
                t++;
                i++;
            } else { //反之,将右边有序序列的当前元素，填充到temp数组
                temp[t] = arr[j];
                t++;
                j++;
            }
        }

        //第二步
        //将剩余未处理完的有序数组的剩余部分全部依次填充到temp中
        while (i <= mid) {
            temp[t] = arr[i];
            i++;
            t++;
        }
        while (j <= right) {
            temp[t] = arr[j];
            j++;
            t++;
        }

        //第三步
        //将temp数组的元素拷贝到arr
        //并不是每次都拷贝所有元素
        t = 0;
        int tempLeft = left;
        //第一次合并 left = 0 , right = 1  //left = 2  right = 3 //left=0 right=3
        //最后一次 left = 0  right = 7
//        System.out.println("tempLeft = " + tempLeft + "; right = " + right);
        while (tempLeft <= right) {
            arr[tempLeft] = temp[t];
            tempLeft++;
            t++;
        }

    }
}
