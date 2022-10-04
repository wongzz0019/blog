package com.xinzhi;

import java.util.Scanner;

/**
 * @author Bosco
 * @date 2021/4/24
 */
public class Game {


    public static void main(String[] args) {
        System.out.println("开始战斗");
        Game game = new Game();
//        game.showHeros();
        
        game.play();
    }

//    private void showHeros(){
//        System.out.println("请选择英雄：");
//        for (int i = 0; i < Heros.HEROS.size(); i++) {
//            System.out.println((i+1) + ":" +Heros.HEROS.get(i).getName());
//        }
//        Scanner scanner = new Scanner(System.in);
//        int heroNum = scanner.nextInt();
//
//    }

    public void play(){
        Equipment equipment = new Equipment("大刀",0,30,0);

        System.out.println("请选择英雄：");
        for (int i = 0; i < Heros.HEROS.size(); i++) {
            System.out.println((i+1) + ":" +Heros.HEROS.get(i).getName());
        }
        Scanner scanner = new Scanner(System.in);
        int heroNum = scanner.nextInt();
        String name = Heros.HEROS.get(heroNum-1).getName();
        Integer blood = Heros.HEROS.get(heroNum-1).getBlood();
        Integer attack = Heros.HEROS.get(heroNum-1).getAttack();
        Integer defense = Heros.HEROS.get(heroNum-1).getDefense();


        Hero hero = new Hero(name,blood,attack,defense);
        hero.wear(equipment);

        Boss boss = new Boss("主宰",800,300,300  );

        Hero anqila = new Hero();
        anqila.setAttack(200);
        anqila.setBlood(500);
        anqila.setName("安其拉");

        hero.fight(boss);
        hero.fight(anqila);
    }
}
