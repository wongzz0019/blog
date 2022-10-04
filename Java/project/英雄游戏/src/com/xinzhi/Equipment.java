package com.xinzhi;

/**
 * 装备
 * @author Bosco
 * @date 2021/4/26
 */
public class Equipment {
    /**
     * 装备昵称
     */
    private String name;

    /**
     * 血量
     */
    private Integer blood;

    /**
     * 攻击
     */
    private Integer attack;

    /**
     * 防御
     */
    private Integer defense;

    public Equipment(String name, Integer blood, Integer attack, Integer defense) {
        this.name = name;
        this.blood = blood;
        this.attack = attack;
        this.defense = defense;
    }

    public String getName() {
        return name;
    }

    public Integer getBlood() {
        return blood;
    }

    public Integer getAttack() {
        return attack;
    }

    public Integer getDefense() {
        return defense;
    }
}
