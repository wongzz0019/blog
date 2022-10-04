package phaseone.vehicle;

/**
 * @author Bosco
 * @date 2021/3/6
 */
public class Bike {
    public int saddle;
    public String color;

    public void star(){
        System.out.println("坐上自行车");
        System.out.println("骑自行车");
    }

    public static void main(String[] args) {
        Bike bike = new Bike();
        bike.color = "yellow";
        bike.saddle = 1;
        bike.star();
    }
}
