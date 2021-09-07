package DataStructure.search;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xu
 * 二分法查找，数组必须是有序的
 */
public class BinarySearch {
    public static void main(String[] args) {
        int[] arr = {1, 5, 13, 17, 17, 56, 110};
        int index = binarySearch(arr, 0, arr.length-1,2);
        if (index == -1){
            System.out.println("找不到该数");
        }else {
            System.out.println("该数所在位置为：" + index);
        }

        List<Integer> list = binarySearch2(arr, 0, arr.length - 1, 2);
        if (list.size() == 0){
            System.out.println("找不到该数");
        }else {
            System.out.println("该数所在位置为：" + list);
        }
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
     * 当一个数组中有多个相同数值时，将该数出现的所有位置都找到
     * 思路：1.找到mid的索引值时，不要直接返回 2.向左边扫描，将所有满足的下标都加入ArrayList中
     * 3.向右边扫描，将所有满足的下表都放入ArrayList中 4.返回ArrayList
     * @param arr
     * @param left
     * @param right
     * @param findVal
     * @return
     */
    public static List<Integer> binarySearch2(int[] arr, int left, int right, int findVal) {
        if (left > right) {
            return new ArrayList<>();
        }
        int mid = (left + right) / 2;
        int midVal = arr[mid];
        if (findVal > midVal) {
            return binarySearch2(arr, mid + 1, right, findVal);
        } else if (findVal < midVal) {
            return binarySearch2(arr, left, mid - 1, findVal);
        } else {
            //思路：1.找到mid的索引值时，不要直接返回 2.向左边扫描，将所有满足的下标都加入ArrayList中
            //3.向右边扫描，将所有满足的下表都放入ArrayList中 4.返回ArrayList
            List<Integer> list = new ArrayList<>();
            int temp = mid - 1;
            while (temp >= 0 && arr[temp] == findVal){
                list.add(temp);
                temp--;
            }
            list.add(mid);
            temp = mid + 1;
            while (temp < arr.length && arr[temp] == findVal){
                list.add(temp);
                temp++;
            }
            return list;
        }
    }
}
