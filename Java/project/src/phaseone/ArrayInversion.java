package phaseone;

/**
 * 数组倒置
 * @author Bosco
 * @date 2021/3/5
 */
public class ArrayInversion {
    public static void main(String[] args) {
        int[] array = {1,23,5,293,71,56,93,3,21};
/**
        int[] newArray = new int[array.length];
        for (int i = 0; i < array.length; i++) {
            newArray[i] = array[array.length-1-i];
        }

        for (int i = 0; i < newArray.length; i++) {
            System.out.println(newArray[i]);
        }
 */
        int temp = 0;
        for (int i = 0; i < array.length/2; i++) {
            temp = array[array.length-1-i];
            array[array.length-1-i] = array[i];
            array[i] = temp;
        }
        for (int i = 0; i < array.length; i++) {
            System.out.println(array[i]);
        }
    }
}
