package DataStructure.search;

import java.util.Arrays;

/**
 * @author Rita
 * 插值查找算法
 * 减少搜索次数
 */
public class InsertValueSearch {
    public static void main(String[] args) {
        int[] arr = new int[20];
        for (int i = 0; i < arr.length; i++){
            arr[i] = i + 1;
        }

        int index = insertValueSearch(arr, 0, arr.length - 1, 1);
        System.out.println("index = " + index);

        int index2 = binarySearch(arr, 0, arr.length - 1, 1);
        System.out.println("index = " + index2);
    }

    /**
     * 二分法查找（只能找到一个位置）
     * @param arr   数组
     * @param left  左边界
     * @param right 右边界
     * @param findVal   要查找数字
     * @return
     */
    public static int binarySearch(int[] arr, int left, int right, int findVal) {
        System.out.println("二分法查找次数~~~");
        if (left > right) {
            return -1;
        }
        int mid = (left + right) / 2;
        int midVal = arr[mid];
        if (findVal > midVal) {
            return binarySearch(arr, mid + 1, right, findVal);
        } else if (findVal < midVal) {
            return binarySearch(arr, left, mid - 1, findVal);
        } else {
            return mid;
        }
    }

    /**
     * 插值法
     * @param arr   当前数组
     * @param left  左边界
     * @param right 右边界
     * @return
     */
    public static int insertValueSearch(int[] arr, int left, int right, int findVal){
        System.out.println("插值插值次数~~~~");

        //注意，findVal < arr[0] || findVal > arr[arr.length - 1]是必须的
        //否则，mid可能会越界
        if (left > right || findVal < arr[0] || findVal > arr[arr.length - 1]){
            return -1;
        }

        int mid = (right - left) * (findVal - arr[left])/(arr[right] - arr[left]);
        if (findVal > arr[mid]){
            return insertValueSearch(arr, mid + 1, right, findVal);
        }else if (findVal < arr[mid]){
            return insertValueSearch(arr, left, mid - 1, findVal);
        }else {
            return mid;
        }
    }
}
