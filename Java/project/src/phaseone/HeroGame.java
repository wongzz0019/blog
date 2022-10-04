package phaseone;

import java.util.Scanner;

/**
 * 英雄者游戏
 * @author Bosco
 * @date 2021/3/3
 */
public class HeroGame {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("请输入英雄的名字：");
        String heroName = scanner.next();
        System.out.print("请输入英雄的血量：");
        int heroBlood = scanner.nextInt();
        System.out.print("请输入英雄的攻击力：");
        int heroAttack = scanner.nextInt();
        System.out.print("请输入英雄的防御力：");
        int heroDefense = scanner.nextInt();

        String bossName = "主宰";
        int bossBlood = 1000;
        int bossAttack = 200;
        int bossDefense = 40;

        System.out.println("开始干架了----");

        while (heroBlood > 0 && bossBlood > 0){
            sleep(1);
            System.out.println(heroName + "打了" + bossName + "一下，" + bossName + "受到了" + (heroAttack - bossDefense) + "伤害！");
            System.out.println(bossName + "打了" + heroName + "一下，" + heroName + "受到了" + (bossAttack - heroDefense) + "伤害！");
            System.out.println("-----------------------------");
            //掉血
            heroBlood -= (bossAttack - heroDefense);
            bossBlood -= (heroAttack - bossDefense);
        }

        if (heroBlood <= 0){
            System.out.println(heroName + "死了--");
        }

        if (bossBlood <= 0){
            System.out.println(heroName + "赢了!!");
        }
        /**
        while (true) {
            sleep(3);
            System.out.println(heroName + "打了" + bossName + "一下，" + bossName + "受到了" + (heroAttack - bossDefense) + "伤害！");
            System.out.println(bossName + "打了" + heroName + "一下，" + heroName + "受到了" + (bossAttack - heroDefense) + "伤害！");
            System.out.println("-----------------------------");
            //掉血
            heroBlood -= (bossAttack - heroDefense);
            if (heroBlood <= 0){
                System.out.println(heroName + "输了-");
                break;
            }

            bossBlood -= (heroAttack - bossDefense);
            if (bossBlood <= 0){
                System.out.println(heroName + "赢了!!!");
                break;
            }

        }
         */
    }
    /**
     * Thread.sleep() : 定时触发功能，毫秒
     */
    public static void sleep(int time){
        try {
            Thread.sleep(time*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
