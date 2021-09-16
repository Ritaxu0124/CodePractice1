package DataStructure.sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * 插入排序
 */
public class InsertSort {
    public static void main(String[] args) {
//        int[] arr = {101, 34, 119, 1};
//        insertSort(arr);
        int[] arr = new int[80000];
        for (int i = 0; i < 80000; i++){
            arr[i] =(int) (Math.random() * 1500000);    //生成1-1500000之间的随机数
        }

        Date date1 = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-DD HH:mm:ss");
        String date1Str = simpleDateFormat.format(date1);   //将当前时间格式化
        System.out.println("排序前的时间为：" + date1Str);

        insertSort(arr);

        Date date2 = new Date();
        String date2Str = simpleDateFormat.format(date2);   //将当前时间格式化
        System.out.println("排序后的时间为：" + date2Str);
    }

    public static void insertSort(int[] arr) {
        //arr = {101, 34, 119, 1};

        int insertValue = 0;
        int insertIndex = 0; //待插入的位置
        for (int i = 1; i < arr.length; i++) {
            //定义待插入的数
            //1. insertIndex >= 0可以保证不越界
            //2. insertValue < arr[insertIndex]表示还未找到插入的位置
            //3. 未找到就将insertIndex后移--
            insertValue = arr[i];
            insertIndex = i - 1; //待插入的位置
            while (insertIndex >= 0 && insertValue < arr[insertIndex]) {
                arr[insertIndex + 1] = arr[insertIndex];
                insertIndex--;
            }
            //当退出循环时，说明找到了待插入的位置，为inserIndex + 1
            arr[insertIndex + 1] = insertValue;

//            System.out.printf("第%d轮排序后的结果为：\n", i);
//            System.out.println(Arrays.toString(arr));
        }


        /*
        //第2轮排序后
        insertValue = arr[2];
        insertIndex = 2 - 1; //待插入的位置
        while (insertIndex >= 0 && insertValue > arr[insertIndex]) {
            arr[insertIndex + 1] = arr[insertIndex];
            insertIndex--;
        }
        //当退出循环时，说明找到了待插入的位置，为inserIndex + 1
        arr[insertIndex + 1] = insertValue;

        System.out.println("第2轮排序后的结果为：");
        System.out.println(Arrays.toString(arr));

        //第3轮排序后
        insertValue = arr[3];
        insertIndex = 3 - 1; //待插入的位置
        while (insertIndex >= 0 && insertValue > arr[insertIndex]) {
            arr[insertIndex + 1] = arr[insertIndex];
            insertIndex--;
        }
        //当退出循环时，说明找到了待插入的位置，为inserIndex + 1
        arr[insertIndex + 1] = insertValue;

        System.out.println("第3轮排序后的结果为：");
        System.out.println(Arrays.toString(arr));

         */
    }
}
