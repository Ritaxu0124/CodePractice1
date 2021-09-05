package DataStructure.sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class QuickSort {
    public static void main(String[] args) {
//        int[] arr = {4, 5, 7, 8, 6, 9, 6, 2, 10};
//        int[] arr = {-9,78,0,23,-567,70, -1,900, 4561};
//        quickSort(arr, 0, arr.length - 1);
//        System.out.println(Arrays.toString(arr));

        int[] arr = new int[8000000];
        for (int i = 0; i < 8000000; i++) {
            arr[i] = (int) (Math.random() * 1500000);    //生成1-1500000之间的随机数
        }

        Date date1 = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-DD HH:mm:ss");
        String date1Str = simpleDateFormat.format(date1);   //将当前时间格式化
        System.out.println("排序前的时间为：" + date1Str);

        quickSort(arr, 0, arr.length - 1);

        Date date2 = new Date();
        String date2Str = simpleDateFormat.format(date2);   //将当前时间格式化
        System.out.println("排序后的时间为：" + date2Str);
    }

    public static void quickSort(int[] arr, int left, int right) {
        int l = left;
        int r = right;
        //pivot为中间位置的值，作为比较对象
        int pivot = arr[(left + right) / 2];
        //temp用于作为交换的临时值
        int temp = 0;
        while (l < r) {
            //在数组左半部分找到了大于等于pivot的数，才退出
            while (arr[l] < pivot) {
                l++;
            }
            //在数组右半部分找到了小于等于pivot的数，才退出
            while (arr[r] > pivot) {
                r--;
            }
            //l>=r说明左半部分全为小于pivot的数，右半部分全为大于pivot的数
            if (l >= r) {
                break;
            }
            //交换
            temp = arr[l];
            arr[l] = arr[r];
            arr[r] = temp;
            //若arr[l] = pivot，则r左移,r--,否则陷入死循环
            if (arr[l] == pivot) {
                r--;
            }
            //若arr[r] = pivot，则l右移,l++,否则陷入死循环
            if (arr[r] == pivot) {
                l++;
            }
        }

        //如果l==r，则需要l++,r--，否则栈溢出
        if (l == r) {
            l++;
            r--;
        }
        if (r > left) {
            quickSort(arr, left, r);
        }
        if (l < right) {
            quickSort(arr, l, right);
        }
    }
}
