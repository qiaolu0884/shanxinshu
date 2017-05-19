package com.shan.org.shan.utils;

import java.security.MessageDigest;
import java.util.Random;

public class MD5Util {
    public static final String INIT_PWD = "123456";
    private static String byte2hex(byte[] b) {
        StringBuffer hs = new StringBuffer();
        String stmp = "";
        for (int n = 0; n < b.length; n++) {
            stmp = (Integer.toHexString(b[n] & 0XFF));
            if (stmp.length() == 1) {
                hs = hs.append("0").append(stmp);
            } else {
                hs = hs.append(stmp);
            }
        }
        return hs.toString();//.toUpperCase();
    }

    public static String encode(String input) {
        try {
            MessageDigest alga = MessageDigest.getInstance("MD5");
            alga.update(input.getBytes());
            return byte2hex(alga.digest());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";

    }
    
    /*
     * 随机产生6位数
     **/
    public static String getNumber6FromRandom(){
    	  Random r = new Random();
    	  int xx = r.nextInt(1000000);
    	  while(xx<100000){
    	   xx = r.nextInt(1000000);
    	  }
    	  return String.valueOf(xx);
   }

   /* public static void main(String[] args) {
		System.out.println(MD5Util.encode(MD5Util.INIT_PWD));
	}*/
}