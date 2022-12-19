package com.wykd.event.vo;

import com.wykd.event.vo.User;
import org.springframework.context.ApplicationEvent;

/**
 * @author jackcooper
 * @create 2018-02-27 13:54
 */
public class UserRegisterEvent extends ApplicationEvent {

    public User user;

    /**
     *
     * @param source 发生事件的对象
     * @param user 注册用户对象
     */
    public UserRegisterEvent(Object source,User user) {
        super(source);
        this.user = user;
    }





    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
