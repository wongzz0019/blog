package com.xinzhi;

/**
 * @author Bosco
 * @date 2021/4/12
 */
public class Test {
    public static void main(String[] args) {

        Boy boy = new Boy();
        boy.merry(new Girl() {
            @Override
            public void meet() {
                System.out.println("约会");
            }
        });


        Boy boy1 = new Boy();
        Girl girl = new Girl() {
            @Override
            public void meet() {

            }
        };
        boy1.merry(girl);
    }
}
