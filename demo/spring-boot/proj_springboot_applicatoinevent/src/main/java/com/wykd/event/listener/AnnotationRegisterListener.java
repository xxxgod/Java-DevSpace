package com.wykd.event.listener;


import com.wykd.event.vo.User;
import com.wykd.event.vo.UserRegisterEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;


@Component
public class AnnotationRegisterListener {
    /**
     * 注册监听实现方法
     * @param userRegisterEvent 用户注册事件
     */
    @EventListener
    public void sendEmail(UserRegisterEvent userRegisterEvent)
    {
        //获取注册用户对象
        User user = userRegisterEvent.getUser();

        //todo

        //输出注册用户信息
        System.out.println("AnnotationRegisterListener ——  发送邮件，用户名："+user.getName()+"，邮箱："+user.getEmail());
    }



}
