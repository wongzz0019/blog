package phaseone;

/**
 * @author Bosco
 * @date 2021/3/3
 */
public class Random {
    public static void main(String[] args) {
        //生成随机数。只可生成 0 ~ 1 之间的浮点型数字
        System.out.println(Math.random());

        //生成 0 ~ 50 之间的浮点型数字
        System.out.println(Math.random()*50);

        //生成 0 ~ 100 之间的整型数字
        System.out.println((int)(Math.random()*100));

        //生成 30 ~ 60 之间的整型数字    0~1 *30= 0~30; +30= 30~60!
        System.out.println((int)(Math.random()*30+30));
        System.out.println((int)(Math.random()*10+40));
    }
}
