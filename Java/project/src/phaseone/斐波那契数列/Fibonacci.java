package phaseone.斐波那契数列;

/**
 * @author Bosco
 * @date 2021/3/14
 */
public class Fibonacci {
    public static void main(String[] args) {
        System.out.println(fibonacci(6));
    }

    /**
     * 第一个0，第二个1，后面的都是前两个之和
     * 自己调用自己叫递归，如：最后的return
     * @param count
     * @return
     */
    public static int fibonacci(int count){
        if (count == 1){
            return 0;
        }
        if (count == 2){
            return 1;
        }
        if (count < 1){
            System.out.println("您的输入不合法");
            return -1;
        }
        return fibonacci(count - 1) + fibonacci(count - 2);

    }
}
