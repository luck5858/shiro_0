package org.java.dome;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;

/**
 * shiro实现登录密码加密
 */
public class Dome03 {
    public static void main(String[] args) {
        //加载ini文件 创建工厂
        Factory<SecurityManager> factory=new IniSecurityManagerFactory("classpath:shiro03.ini");
        //创建安全管理器
        SecurityManager factoryInstance = factory.getInstance();
        //运行环境添加安全管理器
        SecurityUtils.setSecurityManager(factoryInstance);
        //生成token令牌
        UsernamePasswordToken token=new UsernamePasswordToken("jack1","123");
        //生成主体
        Subject subject = SecurityUtils.getSubject();

        try {
            //进行登录
            subject.login(token);
            //根据用户登录状态判断是否登录成功
            if (subject.isAuthenticated()) {
                System.out.println("登录成功");
            }
        } catch (UnknownAccountException e) {
            System.out.println("账户名错误...");
        } catch (IncorrectCredentialsException e) {
            System.out.println("密码错误...");
        }
    }
}
