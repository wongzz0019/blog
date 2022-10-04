package com.xinzhi;

import com.sun.xml.internal.ws.api.model.wsdl.WSDLOutput;

/**
 * 可变参数
 * @author Bosco
 * @date 2021/4/28
 */
public class VariableElement {


    public static void main(String[] args) {
        int sums = sum(1,1,3,4,6,7);
        System.out.println(sums);

        int sums2 = sum2(10,1,3,4,2);
        System.out.println(sums2);
    }


    public static int sum(int... nums){
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
        }
        return sum;
    }

    // （int...nums）可变参数必须放在最后，一个方法只能有一个可变参数
    public static int sum2(int avg,int...nums){
        int sum = avg;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
        }
        return sum;
    }

}
