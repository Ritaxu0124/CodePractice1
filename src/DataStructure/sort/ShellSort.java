package DataStructure.sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class ShellSort {
    public static void main(String[] args) {
//        int[] arr = {8, 9, 1, 7, 2, 3, 5, 4, 6, 0};
//        shellSort(arr);

        int[] arr = new int[80000];
        for (int i = 0; i < 80000; i++){
            arr[i] =(int) (Math.random() * 1500000);    //生成1-1500000之间的随机数
        }

        Date date1 = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-DD HH:mm:ss");
        String date1Str = simpleDateFormat.format(date1);   //将当前时间格式化
        System.out.println("排序前的时间为：" + date1Str);

        shellSort2(arr);

        Date date2 = new Date();
        String date2Str = simpleDateFormat.format(date2);   //将当前时间格式化
        System.out.println("排序后的时间为：" + date2Str);
//        System.out.println(Arrays.toString(arr));
    }

    //希尔排序（交换法）
    //相对于直接插入排序，移动次数变少，速度明显提升
    public static void shellSort(int[] arr) {
        //整合之后
        int temp = 0;
        int count = 0;
        for (int gap = arr.length/2; gap > 0; gap /= 2){
            for (int i = gap; i < arr.length; i++) {
                for (int j = i - gap; j >= 0; j -= gap) {
                    if (arr[j] > arr[j + gap]) {
                        temp = arr[j];
                        arr[j] = arr[j + gap];
                        arr[j + gap] = temp;
                    }
                }
            }
            count++;
//            System.out.printf("第%d轮排序后：\n",count);
//            System.out.println(Arrays.toString(arr));
        }


        /*
        //首先使用逐步推导的方式进行说明
        int temp = 0;
        //第一轮排序
        for (int i = 5; i < arr.length; i++) {
            for (int j = i - 5; j >= 0; j -= 5) {
                if (arr[j] > arr[j + 5]) {
                    temp = arr[j];
                    arr[j] = arr[j + 5];
                    arr[j + 5] = temp;
                }
            }
        }
        System.out.println("第1轮排序后：");
        System.out.println(Arrays.toString(arr));

        //第一轮排序
        for (int i = 2; i < arr.length; i++) {
            for (int j = i - 2; j >= 0; j -= 2) {
                if (arr[j] > arr[j + 2]) {
                    temp = arr[j];
                    arr[j] = arr[j + 2];
                    arr[j + 2] = temp;
                }
            }
        }
        System.out.println("第2轮排序后：");
        System.out.println(Arrays.toString(arr));

        //第3轮排序
        for (int i = 1; i < arr.length; i++) {
            for (int j = i - 1; j >= 0; j -= 1) {
                if (arr[j] > arr[j + 1]) {
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
        System.out.println("第3轮排序后：");
        System.out.println(Arrays.toString(arr));
         */
    }

    //移位法
    public static void shellSort2(int[] arr){
        //增量为gap，gap的值逐渐减小
        int count = 0;
        for (int gap = arr.length/2; gap > 0; gap /= 2){
            //从第gap个元素开始，逐渐对其所在的组进行直接插入排序
            for (int i = gap; i >= 0; i -= gap){
                int temp = arr[i];
                int j = i;
                while (j - gap >= 0 && temp < arr[j - gap]){
                    arr[j] = arr[j - gap];
                    j -= gap;
                }
                arr[j] = temp;
            }
//            count++;
//            System.out.printf("第%d轮排序后：\n",count);
//            System.out.println(Arrays.toString(arr));
        }
    }
}
