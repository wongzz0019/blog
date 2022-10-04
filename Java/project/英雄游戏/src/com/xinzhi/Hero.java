package com.xinzhi;

/**
 * @author Bosco
 * @date 2021/4/24
 */
public class Hero extends Character {

    public Hero() {
        super();
    }

    public Hero(String name, Integer blood, Integer attack, Integer defense) {
        super(name, blood, attack, defense);
    }

    /**
     * 穿装备
     * @param equipment
     */
    public void wear(Equipment equipment){
        this.setBlood(this.getBlood() + equipment.getBlood());
        this.setAttack(this.getAttack() + equipment.getAttack());
        this.setDefense(this.getDefense() + equipment.getDefense());
    }

    /**
     * 战斗对象
     * @param character
     */
    public void fight(Character character){
        System.out.println(this.getName() + "和" + character.getName() + "打架了");
        while (this.getBlood() > 0 && character.getBlood() > 0){
            this.setBlood(this.getBlood() - character.getAttack() );
            character.setBlood(character.getBlood() - this.getAttack());
            System.out.println(this.getName() + "还剩:" + (this.getBlood()>0 ? this.getBlood() : 0));
            System.out.println(character.getName() + "还剩:" + (character.getBlood() >0 ? character.getBlood() : 0));

        }

        if (this.getBlood() > 0){
            System.out.println(this.getName() + "赢了");
        } else {
            System.out.println(character.getName() + "赢了");
        }

    }

}