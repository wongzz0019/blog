import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * 两有序数组合为一个有序数组
 * @author Bosco
 * @date 2021/5/10
 */
public class Test {


    public static void main(String[] args){
        // 有序数组
        int[] arr1 = {1,3,7,5,9,19,20,24};
        int[] arr2 = {2,3,4,9,14,17};
        // 归并
        int[] concat2 = concat2(arr1,arr2);
        for (int i : concat2) {
            System.out.print(i + " ");
        }

    }

    // 普通方法。 运行速度慢
    private static int[] concat1(int[] a1,int[] a2){
        int[] temp = new int[a1.length + a2.length];
        for (int i = 0; i < a1.length; i++) {
            temp[i] = a1[i];
        }

        for (int i = a1.length; i < temp.length; i++) {
            temp[i] = a2[i - a1.length];
        }
        Arrays.sort(temp);
        return temp;
    }

    /**
     * 创建三个下标 ，分别是数组a的left、数组b的right、新数组temp的newIndex。两数组开始比较，小的放进数组temp,继续下一轮比较
     * @param a 数组a
     * @param b 数组b
     * @return 返回合并有序数组
     */
    private static int[] concat2(int[] a,int[] b){
        // 创建一个新数组
        int[] temp = new int[a.length+b.length];
        int left = 0;
        int right = 0;
        int newIndex = 0;

        while (a.length != left && b.length != right){
            if (a[left] > b[right]){
                temp[newIndex] = b[right];
                right++;
            } else {
                temp[newIndex] = a[left];
                left++;
            }
            newIndex++;
        }

        if (a.length == left){
            for (int i = right; i < b.length; i++){
                temp[newIndex++] = b[i];
            }
        }

        if (b.length == right){
            for (int i = left; i < a.length; i++) {
                temp[newIndex++] = a[i];
            }
        }
        return temp;
    }

}
