package DataStructure.sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * 选择排序
 */
public class SeclectSort {
    public static void main(String[] args) {
//        int[] arr = {24, 12, 2, 6, 18};
//        System.out.println("原始数组为：");
//        System.out.println(Arrays.toString(arr));
//        selectSort(arr);

        int[] arr = new int[80000];
        for (int i = 0; i < 80000; i++){
            arr[i] =(int) (Math.random() * 1500000);    //生成1-1500000之间的随机数
        }

        Date date1 = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-DD HH:mm:ss");
        String date1Str = simpleDateFormat.format(date1);   //将当前时间格式化
        System.out.println("排序前的时间为：" + date1Str);

        selectSort(arr);

        Date date2 = new Date();
        String date2Str = simpleDateFormat.format(date2);   //将当前时间格式化
        System.out.println("排序后的时间为：" + date2Str);
    }

    //定义选择排序方法（降序）
    public static void selectSort(int[] arr){
        for (int i = 0; i < arr.length - 1; i++){
            int max = arr[i];
            int maxIndex = i;
            for (int j = i + 1; j < arr.length; j++){
                if (arr[j] > max){
                    max = arr[j];
                    maxIndex = j;
                }
            }
            if (maxIndex != i){
                arr[maxIndex] = arr[i];
                arr[i] = max;
            }
//            System.out.println("第" + (i + 1) + "轮排序后的结果为：");
//            System.out.println(Arrays.toString(arr));
        }
    }
}
