package phaseone;

import java.util.Scanner;
/**
 * 欣知人事年龄管理系统
 * @author Bosco
 * @date 2021/2/10
 * 功能1.年龄按小到大排序
 * 2.找出年龄最大的员工
 * 3.添加新员工的年龄
 */

public class XinZhiHr {
    static int[] arr = new int[4];
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        System.out.println("欣知人事管理系统");

        for (int i = 0 ;i < arr.length ;i++) {
            System.out.print("请输入" + (i + 1) + "号员工年龄:");
            arr[i] = scanner.nextInt();
        }
        boolean flag = true;
        while (flag){
            System.out.println("1.年龄排序 2.找出最大年龄 3.新增员工年龄 4.查看现有员工年龄 5.退出");
            System.out.print("请输入选项查看：");
            int function = scanner.nextInt();
            switch (function){
                case 1:
                    sort(arr);
                    break;
                case 2:
                    int max = maxAge(arr);
                    System.out.println("最大年龄为："+ max +"岁");
                    break;
                case 3:
                    System.out.print("请输入新的员工年龄：");
                    int newAge = scanner.nextInt();
                    arr = addAge(arr,newAge);
                    break;
                case 4:
                    for (int i = 0; i < arr.length; i++){
                        System.out.println((i+1) + "号员工年龄为：" + arr[i] + "岁");
                    }
                    break;
                case 5:
                    flag = false;
                    break;
                default:
                    break;
            }
        }

    }

    /**
     * 功能1.年龄排序，由小到大。
     */
    public static void sort(int[] arr){
        for (int j = 0; j < arr.length-1; j++){
            for (int i = 0; i < arr.length-1-j; i++){
                if (arr[i] > arr[i+1]){
                    int temp = arr[i];
                    arr[i] = arr[i+1];
                    arr[i+1] = temp;
                }
            }
        }
        System.out.println("年龄由小到大排序!");
        for (int value : arr) {
            System.out.println(value);
        }
    }

    /**
     * 功能2.找出最大年龄
     * @param arr
     * @return
     */
    public static int maxAge(int[] arr){
        int max = arr[0];
        for (int i = 0; i < arr.length-1; i++){
            if (max < arr[i+1]){
                max = arr[i+1];
            }
        }
        return max;
    }

    /**
     * 功能3.添加新的员工年龄
     * @param oldArr
     * @param newAge
     * @return
     */
    public static int[] addAge(int[] oldArr,int newAge){
        int[] tempArr = new int[arr.length+1];
        for (int i = 0; i < arr.length; i++){
            tempArr[i] = arr[i];
        }
        tempArr[tempArr.length-1] = newAge;
        return tempArr;

    }
}
