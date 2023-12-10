package com.wykd.event.service;


import com.wykd.event.vo.User;
import com.wykd.event.vo.UserRegisterEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

/**
 * @author jackcooper
 * @create 2018-02-27 14:03
 */
@Service
public class UserService {

    @Autowired
    private ApplicationContext applicationContext;

    public void register(User user){
        //省略业务逻辑
        //。。。。
        //发布UserRegisterEvent事件
        applicationContext.publishEvent(new UserRegisterEvent(this,user));
    }

}
