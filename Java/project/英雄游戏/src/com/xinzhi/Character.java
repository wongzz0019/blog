package com.xinzhi;

/**
 * 角色
 * @author Bosco
 * @date 2021/4/26
 */
public class Character {
    /**
     * 昵称
     */
    private String name;

    /**
     * 血量
     */
    private Integer blood = 100;

    /**
     * 攻击
     */
    private Integer attack = 100;

    /**
     * 防御
     */
    private Integer defense = 100;

    public Character(String name, Integer blood, Integer attack, Integer defense) {
        this.name = name;
        this.blood = blood;
        this.attack = attack;
        this.defense = defense;
    }

    public Character() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getBlood() {
        return blood;
    }

    public void setBlood(Integer blood) {
        this.blood = blood;
    }

    public Integer getAttack() {
        return attack;
    }

    public void setAttack(Integer attack) {
        this.attack = attack;
    }

    public Integer getDefense() {
        return defense;
    }

    public void setDefense(Integer defense) {
        this.defense = defense;
    }
}
