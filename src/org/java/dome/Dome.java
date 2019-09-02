package org.java.dome;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.apache.shiro.mgt.SecurityManager;

/**
 * 使用明编码
 * 验证shiro登录
 */
public class Dome {
    public static void main(String[] args) {
        //根据ini文件创建工厂类   作用:产生安全管理器
        Factory<SecurityManager> facotry= new IniSecurityManagerFactory("classpath:shiro.ini");
        //产生安全管理器
        SecurityManager securityManager=facotry.getInstance();
        //将安全管理器添加到当前运行环境中
        SecurityUtils.setSecurityManager(securityManager);
        //通过运行环境创建主体   备注:只有主体 才能 进行认证操作
        Subject subject = SecurityUtils.getSubject();
        //产生令牌token 封装用户名 密码 准备认证
        UsernamePasswordToken token=new UsernamePasswordToken("jack","123");

        try {
            //用户登录
            subject.login(token);
            //判断 当前用户是否已经登录
            if (subject.isAuthenticated()) {
                System.out.println("用户已经登录");
            }
        } catch (UnknownAccountException ex) {
            System.out.println("用户不存在");
        } catch (IncorrectCredentialsException ex){
            System.out.println("密码错误");
        }

    }
}
