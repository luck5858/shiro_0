package org.java.md5;

import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.junit.Test;

/**
 * 方式一和方式二  参数自行任选  最低只需要pwd
 */
public class MD5Dome {

    private String pwd="123";
    private String salt="accp";

    /**
     * 加密方式一
     */
    @Test
    public void first(){
        //对pwd进行加密  加密方式:加盐 加密三次
        Md5Hash md5=new Md5Hash(pwd,salt,3);
        System.out.println(md5.toString());
    }

    /**
     * 加密方式二
     */
    @Test
    public void second(){
        //对pwd进行加密  加密方式:加盐 加密三次
        SimpleHash sh=new SimpleHash("md5",pwd,salt,3  );
        System.out.println(sh.toString());
    }
}
