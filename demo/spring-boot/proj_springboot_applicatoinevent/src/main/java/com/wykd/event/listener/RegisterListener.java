package com.wykd.event.listener;


import com.wykd.event.vo.User;
import com.wykd.event.vo.UserRegisterEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * @author jackcooper
 * @create 2018-02-27 14:29
 * ApplicationListener实现监听：这种方式也是Spring之前比较常用的监听事件方式，在实现ApplicationListener接口时需要将监听事件作为泛型传递，监听实现代码如下所示
 */
@Component
public class RegisterListener implements ApplicationListener<UserRegisterEvent> {

    /**
     * 实现监听
     * @param userRegisterEvent
     */
    @Override
    public void onApplicationEvent(UserRegisterEvent userRegisterEvent) {
        //获取注册用户对象
        User user = userRegisterEvent.getUser();

        //../省略逻辑

        //输出注册用户信息
        System.out.println("2注册信息，用户名："+user.getName()+"，密码："+user.getEmail());
    }
}
