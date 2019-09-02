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
 * shiro验证登录 退出
 */
public class Dome01 {
    public static void main(String[] args) {
        //加载配置文件 创建工厂
        Factory<SecurityManager> factory=new IniSecurityManagerFactory("classpath:shiro.ini");
        //获得安全管理器
        SecurityManager instance = factory.getInstance();
        //运行环境中 添加安全管理器
        SecurityUtils.setSecurityManager(instance);
        //产生令牌 封装账号 密码  用于验证
        UsernamePasswordToken token=new UsernamePasswordToken("jack","123");
        //运行环境中得到主体  用于执行验证
        Subject subject = SecurityUtils.getSubject();

        try {
            //开始登录
            System.out.println("**************************开始登录**********************************");
            subject.login(token);
            //如果运行环境中的是登录的状态
            if (subject.isAuthenticated()) {
                System.out.println("用户已经登录");
            }
            System.out.println("**************************开始退出**********************************");
            //开始退出
            subject.logout();
            //如果运行环境中的是 未登录 的状态
            if (!subject.isAuthenticated()) {
                System.out.println("用户已退出登录");
            }
        } catch (UnknownAccountException e) {
            System.out.println("账户名错误...");
        } catch (IncorrectCredentialsException e){
            System.out.println("密码错误");
        }
    }
}
