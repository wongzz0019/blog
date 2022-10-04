package phaseone.完整类;

/**
 * @author Bosco
 * @date 2021/3/8
 */
public class User {

    //使用成员变量，必须要用set赋值，get取值
    public static void main(String[] args) {
        User user = new User();
        System.out.println(user.getAge());
        System.out.println(user.getName());
        System.out.println(user.getMoney());
        System.out.println(user.isFat());
        user.run();

        // 基本数据类型、引用数据类型的局部变量，都得要手动初始值,否则编译出错
        int i = 01;
        System.out.println(i);
        String j = "app";
        System.out.println(j);
    }

    // 成员变量 属性   定义了以后会有默认的初始值
    private String name;
    private int age;
    private double money;
    private boolean fat;


    public User(){}
    public User(String name, int age, int money) {
        this.name = name;
        this.age = age;
        this.money = money;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public boolean isFat() {
        return fat;
    }

    public void setFat(boolean fat) {
        this.fat = fat;
    }

    public void run(){
        System.out.println(name + "今年" + age + ",资产:" + money);
    }
}
