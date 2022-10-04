package phaseone;

import java.util.Scanner;

/**
 * 数组求和，个位不为7，十位不为5，且是偶数
 * @author Bosco
 * @date 2021/3/4
 */
public class ArrayAdd {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] array = new int[6];
        int result = 0;
        System.out.println("求个位不为7，十位不为5，且是偶数的和！");
        for (int i = 0; i < array.length; i++) {
            System.out.print("请输入第" + (i+1) +"数字：");
            array[i] = scanner.nextInt();
        }

        for (int i = 0; i < array.length; i++) {
            if (array[i] % 10 != 7 && array[i]/10 % 10 != 5 && array[i] % 2 ==0) {
                result += array[i];
            }
        }
        System.out.println("求和结果：" + result);
    }
}
