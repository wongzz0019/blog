package phaseone.构造方法;

/**
 * @author Bosco
 * @date 2021/3/7
 */
public class Animal {
    /**
     * 1.new 其实是在调用构造方法
     * 2如果一个类里边没有构造方法 会只创建一个空的构造方法
     * 3.构造方法能传参数，在构造期间就把对象的值赋好
     * 4.一旦有了新的有参数的构造方法，空构造就不在了，如果想保留，就得动手写上
     * @param args
     */
    public static void main(String[] args) {
        Animal animal = new Animal("dog","white",5);
        animal.print();
        Animal animal1 = new Animal();
        animal1.print();
        animal.eat();
        animal.eat("骨头",2);
    }

    private String animalName;
    private String color;
    private int age;

    /**
     * 构造方法不需要写返回值相关的东西（void,int...）
     * 重载的构造方法
     * this 内存里分配好空间的对象
     */
    public Animal(String name,String color,int age){
        animalName = name;
        this.color = color;
        this.age = age;
    }
    public Animal(){

    }
    public void print(){
        System.out.println(color+"的"+animalName+"今年"+age+"岁");
    }

    /**
     * 必须在同一个类里，方法名相同
     * 方法的参数类型、参数个数不一样
     * 方法的返回类型可以不相同
     * 方法的修饰符也可以不相同
     * main 方法也可以被重载
     */
    public void eat(String foot,int count){
        System.out.println("我爱吃"+foot);
    }

    public void eat(){
        System.out.println("我什么都吃！");
    }
}
