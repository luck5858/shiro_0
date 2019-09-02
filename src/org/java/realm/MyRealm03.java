package org.java.realm;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

public class MyRealm03 extends AuthorizingRealm {
    /**
     * 授权:得到用户的访问权限
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }

    /**
     * 认证:用户登录
     * @param authenticationToken 参数代表 封装账号 密码的token令牌
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@正在进行认证@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
        //得到账号
        String principal = (String) authenticationToken.getPrincipal();
        //判断账户是否存在
        if (principal.equals("jack")) {
            //这里返回null 代表用户名不存在 系统会自动抛出异常UnknownAccountException
            return null;
        }
        //默认用户存在 从数据库中,返回当前用户的正确密码
        String pwd = "4ebe4ae946ee2d290a499c6e44497090";

        //指定盐
        String salt = "accp";

        //根据账户密码创建一个AuthenticationInfo对象  返回认证
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(principal, pwd, ByteSource.Util.bytes(salt), "myrealm");

        return info;
    }
}
