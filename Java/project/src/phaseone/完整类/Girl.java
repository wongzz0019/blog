package phaseone.完整类;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;

/**
 * @author Bosco
 * @date 2021/3/8
 */
public class Girl {

    // 属性
    private String name;
    private int age;

    // 构造方法
    public Girl(){}
    public Girl(String name,int age){
        this.name = name;
        this.age = age;
    }

    // 普通方法
    public void meet(String boyName){
        makeUp();
        System.out.println(boyName + "觉得" + this.age + "岁的" + this.name + "很漂亮...");
    }

    // 私有方法
    private void makeUp(){
        System.out.println("今天化了浓浓的妆！");
    }

    // 还有一些方法  setter getter
    public void setName(String name){
        this.name = name;
    }
    public String getName(){
        return this.name;
    }

    public void setAge(int age){
        if (age > 18){
            age = 18;
        }
        this.age = age;
    }
    public int getAge(){
        return this.age;
    }
}
