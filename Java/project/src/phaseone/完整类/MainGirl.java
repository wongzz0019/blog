package phaseone.完整类;

import phaseone.完整类.Girl;

/**
 * @author Bosco
 * @date 2021/3/8
 */
public class MainGirl {
    public static void main(String[] args) {
        Girl XiaoHong = new Girl();
        XiaoHong.setName("小红");
        XiaoHong.setAge(58);
        XiaoHong.meet("猪八戒");
        System.out.println(XiaoHong.getAge());

        Girl ZhenZhen = new Girl("珍珍",24);
        ZhenZhen.meet("八戒");
    }
}
