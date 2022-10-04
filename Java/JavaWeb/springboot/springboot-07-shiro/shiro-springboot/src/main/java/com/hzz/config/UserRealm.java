package com.hzz.config;

import com.hzz.pojo.User;
import com.hzz.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 自定义的UserRealm  extends AuthorizingRealm
 */
public class UserRealm extends AuthorizingRealm {

    //自动装配，从userService获取数据
    @Autowired
    UserService userService;

    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("执行了=》授权doGetAuthorizationInfo");
        //SimpleAuthorizationInfo new一个简单的授权
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        //拿到当前登录的这个对象
        Subject subject = SecurityUtils.getSubject();
        //这是个user，从下面认证获取的
        User currentUser = (User) subject.getPrincipal();
        //设置当前用户的权限
        info.addStringPermission(currentUser.getPerms());

        return info;
    }

    //认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("执行了=》doGetAuthenticationInfo");


        //类的参数需要强转为UsernamePasswordToken(这是个MyController里的封装用户)
        UsernamePasswordToken userToken = (UsernamePasswordToken) authenticationToken;
        //连接真实的数据库
        User user = userService.queryUserByName(userToken.getUsername());

        if (user == null){ //没有这个人
            return null; //抛出异常 UnknownAccountException
        }

        //密码认证，shiro做，不需要我们做。这三个参数分别是（user对象，密码，当前realm的名称）
        return new SimpleAuthenticationInfo(user,user.getPwd(),"");
    }
}
