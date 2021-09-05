package DataStructure.sort;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Scanner;

/**
 * 冒泡排序
 */
public class BubbleSort {
    public static void main(String[] args) {
        /*
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        String[] s = str.split(" ");
        int[] arr = new int[s.length];
        for (int i = 0; i < s.length; i++){
            arr[i] = Integer.parseInt(s[i]);
        }

        for (int i = 0; i < arr.length; i++){
            System.out.print(arr[i] + " ");
        }
        //输入数组方式
         */

        //直接定义数组
        /*
        int[] arr = {3, 9, -1, 10, 20};
        System.out.println("排序前：");
        System.out.println(Arrays.toString(arr));

        bubbleSort(arr);
        System.out.println("排序后：");
        System.out.println(Arrays.toString(arr));
         */

        //测试冒泡排序的时间复杂度O(n^2)，用80000个数据测试
        int[] arr = new int[8];
        for (int i = 0; i < 8; i++){
            arr[i] =(int) (Math.random() * 1500000);    //生成1-1500000之间的随机数
        }
//        System.out.println("排序前：");
//        System.out.println(Arrays.toString(arr));

        Date date1 = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-DD HH:mm:ss");
        String date1Str = simpleDateFormat.format(date1);   //将当前时间格式化
        System.out.println("排序前的时间为：" + date1Str);

        bubbleSort(arr);
        System.out.println("排序后：");
        System.out.println(Arrays.toString(arr));

        Date date2 = new Date();
        String date2Str = simpleDateFormat.format(date2);   //将当前时间格式化
        System.out.println("排序后的时间为：" + date2Str);


        /*
        //第二趟排序就是将最大的数放在倒数第二位
        for (int j = 0; j < arr.length - 2; j++){
            if (arr[j] > arr[j + 1]){
                temp = arr[j + 1];
                arr[j + 1] = arr[j];
                arr[j] = temp;
            }
        }
        System.out.println("第二趟排序完的结果为：");
        System.out.println(Arrays.toString(arr));

        //第三趟排序就是将最大的数放在倒数第二位
        for (int j = 0; j < arr.length - 3; j++){
            if (arr[j] > arr[j + 1]){
                temp = arr[j + 1];
                arr[j + 1] = arr[j];
                arr[j] = temp;
            }
        }
        System.out.println("第三趟排序完的结果为：");
        System.out.println(Arrays.toString(arr));

        //第四趟排序就是将最大的数放在倒数第二位
        for (int j = 0; j < arr.length - 4; j++){
            if (arr[j] > arr[j + 1]){
                temp = arr[j + 1];
                arr[j + 1] = arr[j];
                arr[j] = temp;
            }
        }
        System.out.println("第四趟排序完的结果为：");
        System.out.println(Arrays.toString(arr));
         */
    }

    //将冒泡排序封装成一个方法
    public static void bubbleSort(int[] arr) {
        //一共排序arr.length-1趟
        //时间复杂度为O(n^2)
        boolean flag = false;
        int temp = 0;
        //一共交换n轮
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    flag = true;
                    temp = arr[j + 1];
                    arr[j + 1] = arr[j];
                    arr[j] = temp;
                }
            }
//            System.out.println("第" + (i + 1) + "趟排序完的结果为：");
//            System.out.println(Arrays.toString(arr));
            if (!flag) {
//                System.out.println("已得到最终结果");
                break;
            } else {
                flag = false;
            }

        }
    }
}
