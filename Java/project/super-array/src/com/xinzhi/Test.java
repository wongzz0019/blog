package com.xinzhi;

/**
 * @author Bosco
 * @date 2021/3/17
 */
public class Test {
    public static void main(String[] args) {
        SuperArray superArray = new SuperArray(3);
        superArray.add(3);
        superArray.add(4);
        superArray.add(1);
        superArray.add(8);
        superArray.add(6);
        superArray.add(12);
        superArray.print();

        superArray.sort();
        superArray.print();

        superArray.delete(3);
        superArray.print();

        System.out.println(superArray.toSrt());

        // 获取数组赋值给arr
        int[] arr = superArray.get();
        for (int i = 0; i <arr.length ; i++) {
            System.out.println(arr[i]);
        }

    }
}
