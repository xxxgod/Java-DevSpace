package com.wykd.event.listener;

import com.wykd.event.service.UserService;
import com.wykd.event.vo.User;
import com.wykd.event.vo.UserRegisterEvent;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.event.SmartApplicationListener;
import org.springframework.stereotype.Component;

/**
 * @author 10400
 * @create 2018-02-27 15:03
 * 有序监听
 */
@Component
public class SmartRegisterListener implements SmartApplicationListener {
    @Override
    public boolean supportsEventType(Class<? extends ApplicationEvent> aClass) {
        //只有UserRegisterEvent监听类型才会执行下面逻辑
        return aClass == UserRegisterEvent.class;
    }

    @Override
    public boolean supportsSourceType(Class<?> aClass) {
        //只有在UserService类发布的UserRegisterEvent事件时才会执行下面逻辑
        return aClass == UserService.class;
    }

    @Override
    public void onApplicationEvent(ApplicationEvent applicationEvent) {
        //转换事件类型
        UserRegisterEvent userRegisterEvent = (UserRegisterEvent) applicationEvent;
        //获取注册用户对象信息
        User user = userRegisterEvent.getUser();
        //.../完成注册业务逻辑

        System.out.println("SmartRegisterListener" + user.getName());

    }

    /**
     * return 的数值越小证明优先级越高，执行顺序越靠前。
     * @return
     */
    @Override
    public int getOrder() {
        return 10;
    }
}
