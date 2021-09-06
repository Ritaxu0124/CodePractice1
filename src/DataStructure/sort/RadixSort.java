package DataStructure.sort;

import java.util.Arrays;

/**
 * @author xu
 * 基数排序
 */
public class RadixSort {
    public static void main(String[] args) {
        int[] arr = {53, 3, 542, 748, 14, 214};
        radixSort(arr);
    }

    public static void radixSort(int[] arr) {
        //获取到数组中最大的数
        int max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
        }
        //获取最大位数，一共排序maxLength轮
        int maxLength = (max + "").length();

        //定义一个二维数组表示10个桶，每个桶都是一个一维数组
        //1.二维数组包含十个一维数组
        //2.为了防止溢出，每个一维数组的长度为arr.length
        //3.基数排序是明显的空间换时间的排序方法
        int[][] bucket = new int[10][arr.length];
        //为了记录每个桶中存放的数据格式，定义一维数组bucketElementCount来存放每个桶中的数据个数
        int[] bucketElementCount = new int[10];

        for (int i = 0, n = 1; i < maxLength; i++, n *= 10) {
            //第i轮
            for (int j = 0; j < arr.length; j++) {
                //取出每个元素的个位数的值
                int digitOfElement = arr[j] / n % 10;
                //放入到对应的桶中
                bucket[digitOfElement][bucketElementCount[digitOfElement]] = arr[j];
                bucketElementCount[digitOfElement]++;
            }
            //按照桶中数字的顺序依次取出数字，放入到原来的数组中
            int index = 0;
            for (int k = 0; k < bucket.length; k++) {
                //如果桶中有数据，则取出放入原数组
                if (bucketElementCount[k] != 0) {
                    //依次将桶中的每个数据放入原数组
                    for (int l = 0; l < bucketElementCount[k]; l++) {
                        arr[index++] = bucket[k][l];
                    }
                    //取完数据后，需要将bucketElementCount[k]清零
                    bucketElementCount[k] = 0;
                }
            }
            System.out.println("第" + (i + 1) + "轮排序后的结果：" + Arrays.toString(arr));
        }
        System.out.println("####################");


        /*
        //第1轮
        for (int j = 0; j < arr.length; j++) {
            //取出每个元素的个位数的值
            int digitOfElement = arr[j] % 10;
            //放入到对应的桶中
            bucket[digitOfElement][bucketElementCount[digitOfElement]] = arr[j];
            bucketElementCount[digitOfElement]++;
        }
        //按照桶中数字的顺序依次取出数字，放入到原来的数组中
        int index = 0;
        for (int k = 0; k < bucket.length; k++) {
            //如果桶中有数据，则取出放入原数组
            if (bucketElementCount[k] != 0) {
                //依次将桶中的每个数据放入原数组
                for (int l = 0; l < bucketElementCount[k]; l++) {
                    arr[index++] = bucket[k][l];
                }
                //取完数据后，需要将bucketElementCount[k]清零
                bucketElementCount[k] = 0;
            }
        }
        System.out.println("第1轮排序后的结果：" + Arrays.toString(arr));

        //第2轮
        for (int j = 0; j < arr.length; j++) {
            //取出每个元素的十位数的值
            int digitOfElement = arr[j] / 10 % 10;
            //放入到对应的桶中
            bucket[digitOfElement][bucketElementCount[digitOfElement]] = arr[j];
            bucketElementCount[digitOfElement]++;
        }
        //按照桶中数字的顺序依次取出数字，放入到原来的数组中
        index = 0;
        for (int k = 0; k < bucket.length; k++) {
            //如果桶中有数据，则取出放入原数组
            if (bucketElementCount[k] != 0) {
                //依次将桶中的每个数据放入原数组
                for (int l = 0; l < bucketElementCount[k]; l++) {
                    arr[index++] = bucket[k][l];
                }
                //取完数据后，需要将bucketElementCount[k]清零
                bucketElementCount[k] = 0;
            }
        }
        System.out.println("第2轮排序后的结果：" + Arrays.toString(arr));

        //第3轮
        for (int j = 0; j < arr.length; j++) {
            //取出每个元素的百位数的值
            int digitOfElement = arr[j] / 100 % 10;
            //放入到对应的桶中
            bucket[digitOfElement][bucketElementCount[digitOfElement]] = arr[j];
            bucketElementCount[digitOfElement]++;
        }
        //按照桶中数字的顺序依次取出数字，放入到原来的数组中
        index = 0;
        for (int k = 0; k < bucket.length; k++) {
            //如果桶中有数据，则取出放入原数组
            if (bucketElementCount[k] != 0) {
                //依次将桶中的每个数据放入原数组
                for (int l = 0; l < bucketElementCount[k]; l++) {
                    arr[index++] = bucket[k][l];
                }
                //取完数据后，需要将bucketElementCount[k]清零
                bucketElementCount[k] = 0;
            }
        }
        System.out.println("第3轮排序后的结果：" + Arrays.toString(arr));

         */
    }


}
