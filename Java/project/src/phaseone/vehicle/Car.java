package phaseone.vehicle;

/**
 * 面向对象--封装
 * @author Bosco
 * @date 2021/3/6
 */

public class Car {

    /**
     * 定义属性
     */
    public String  color;
    public int saddle;

    /**
     * 定义方法
     */
    public void run(){
        System.out.println("启动车");
        System.out.println("踩油门");
    }

    public static void main(String[] args) {
        Car car = new Car();
        car.color = "red";
        car.saddle = 4;
        car.run();

        Car car1 = new Car();
        car1.color = "green";
        car1.saddle = 2;
        car1.run();
    }
}

