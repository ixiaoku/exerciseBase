package com.dcr.stream;

/**
 * @descriptions:
 * @author: dcr
 * @date: 2021/4/27 16:33
 */
public class TestBase {

    public static void main(String[] args){
        Integer a1 = Integer.valueOf(325);
        Integer a3 = 325;
        Integer a2 = new Integer(125);
        System.out.println(a1.equals(a3));
        System.out.println(a1 == a3);
        boolean flag = false;
    }

}
