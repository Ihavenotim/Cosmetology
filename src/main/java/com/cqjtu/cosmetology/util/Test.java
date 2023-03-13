package com.cqjtu.cosmetology.util;

import org.apache.shiro.crypto.hash.Md5Hash;

/**
 * @author LTX
 * @date 2023/3/8 16:36
 */
public class Test {
    public static void main(String[] args){
        Md5Hash md5Hash=new Md5Hash(123456,"cqjtu");
        System.out.println(md5Hash.toString());

    }
}
