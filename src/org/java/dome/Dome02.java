package org.java.dome;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;

public class Dome02 {
    public static void main(String[] args) {
        //创建工厂
        Factory<SecurityManager> factory=new IniSecurityManagerFactory("classpath:shiro02.ini");
        //创建安全管理器
        SecurityManager factoryInstance = factory.getInstance();
        //运行环境中添加安全管理器
        SecurityUtils.setSecurityManager(factoryInstance);
        //通过用户提供的账号 密码 生成token
        UsernamePasswordToken token=new UsernamePasswordToken("jack1","1234");
        //通过运行环境创建主体
        Subject subject = SecurityUtils.getSubject();

        try {
            //主题执行认证
            subject.login(token);
            //根据用户登录状态判断是否登录成功
            if (subject.isAuthenticated()) {
                System.out.println("登录成功");
            }
        } catch (UnknownAccountException e) {
            System.out.println("账号错误...");
        } catch (IncorrectCredentialsException e) {
            System.out.println("密码错误...");
        }


    }
}
