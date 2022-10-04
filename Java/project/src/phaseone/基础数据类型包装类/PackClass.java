package phaseone.基础数据类型包装类;

/**
 * @author Bosco
 * @date 2021/3/15
 */
public class PackClass {
    public static void main(String[] args) {
        // 八种基础数据包装类，首字母都大写
        // 包装类里有很多自带的方法
        // 包装类能传null
        Byte b = 1;
        Short s = 99;
        Integer i = 100;
        Long l = 44444444L;

        Float f = 3.3f;
        Double d = 2.2;

        Character c = 'a';

        Boolean B = true;

        // 自动装箱
        Integer num = 111;
        // 自动拆箱
        int j = num;
        // 字符串变数字.   parse:解析
        int nui1 = Integer.parseInt("123");
        System.out.println(nui1);
        // 包装类自带方法，比较大小
        int nui2 = Integer.max(100,54);
        System.out.println(nui2);
    }
}
