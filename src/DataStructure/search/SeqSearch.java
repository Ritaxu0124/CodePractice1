package DataStructure.search;

import java.util.ArrayList;
import java.util.List;

public class SeqSearch {
    public static void main(String[] args) {
        int[] arr = {1, 9, 11, -1, 34, 89};
        int index = seqSearch(arr, 0);
        if (index == -1) {
            System.out.println("未找到该数");
        } else {
            System.out.println("元素所在位置为：" + index);
        }

        int[] arr2 = {1, 9, 1, -1, 1, 89};
        List<Integer> list = seqSort2(arr2, 0);
        if (list.size() == 0) {
            System.out.println("未找到该数");
        } else {
            System.out.println("所在位置为：" + list);
        }
    }

    /**
     * 只返回元素所在的第一个位置
     *
     * @param arr
     * @param value
     * @return
     */
    public static int seqSearch(int[] arr, int value) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == value) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 返回列表包括所有位置
     *
     * @param arr
     * @param value
     * @return
     */
    public static List<Integer> seqSort2(int[] arr, int value) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == value) {
                list.add(i);
            }
        }
        return list;
    }
}
