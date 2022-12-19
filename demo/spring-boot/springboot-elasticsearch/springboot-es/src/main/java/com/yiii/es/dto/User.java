package com.yiii.es.dto;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class User {

    private int id;

    private int age;

    private String name;

    private Date startTime;

    private Date endTime;

    public static List<User> getAll() {
        List<User> list = new ArrayList<User>();
        try {
            Calendar no = Calendar.getInstance();
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String time = "2019-09-25 19:12:32";
            User user = null;
            for(int i=1;i<11;i++) {
                no.setTime(format.parse(time));
                no.set(Calendar.DATE, no.get(Calendar.DATE) -new Random().nextInt(15));
                Date startTime = no.getTime();
                no.set(Calendar.DATE, no.get(Calendar.DATE) +new Random().nextInt(30));
                Date endTime = no.getTime();

                user = new User(i,
                        new Random().nextInt(100),
                        "吴磊"+i,
                        startTime,
                        endTime);
                list.add(user);
            }
        } catch (Exception e) {

        }
        return list;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public User(int id, int age, String name, Date startTime, Date endTime) {
        super();
        this.id = id;
        this.age = age;
        this.name = name;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public User() {
        super();
    }
    @Override
    public String toString() {
        return "User [id=" + id + ", age=" + age + ", name=" + name + ", startTime=" + startTime + ", endTime="
                + endTime + "]";
    }
}
