package com.dcr.sort;

/**
 * @descriptions:
 * @author: dcr
 * @date: 2021/12/24 14:14
 */
public class TestSort {

    public static void main(String[] args) {

        int[] num = {1, 5, 2, -8, 4, 15, -19, 6, 7, 10};
        subColumnSum(num);
        bubble(num);
    }

    /**
     * 冒泡排序
     * @param num
     */
    public static void bubble(int[] num) {
        for (int i = 0; i < num.length; i++) {
            int k;
            for (int j = 0; j < num.length; j++) {
                if(num[i] < num[j]) {
                    k = num[i];
                    num[i] = num[j];
                    num[j] = k;
                }
            }
        }
        print(num);
    }

    /**
     * 任意序列最大连续子列和
     * @param num
     */
    public static void subColumnSum(int[] num) {
        int sum = 0;
        for (int i = 0; i < num.length; i++) {
            sum += num[i];
            if(num[i] >= sum) {
                sum = num[i];
            }
        }
        System.out.println("任意序列最大连续子列和: " + sum);
    }

    /**
     * 打印方法
     * @param num
     */
    public static void print(int[] num) {
        for (int i = 0; i < num.length; i++) {
            System.out.print(num[i] + " ");
        }
    }
}
