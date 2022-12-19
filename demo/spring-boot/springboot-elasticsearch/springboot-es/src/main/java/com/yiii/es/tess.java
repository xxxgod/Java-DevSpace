package com.yiii.es;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class tess {


        public static void main(String[] args) throws IOException {
            File fileRoot1 = new File(" /");
            System.out.println(" 绝对路径：" + fileRoot1.getAbsolutePath());
            System.out.println(" 标注路径：" + fileRoot1.getCanonicalPath());
            System.out.println(" 相对路径：" + fileRoot1.getPath());



            String str = "2017-11-21 14:41:06:612";
            DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss:SSS");
            LocalDate date = LocalDate.parse(str, fmt);
            LocalDateTime time = LocalDateTime.parse(str, fmt);
            System.out.println("date:"+date);
            System.out.println("time:"+time);



            ArrayList a1 = new ArrayList();

            a1.add("java");

            a1.add("php");//List集合中的元素可以重复

            a1.add(".net");

            System.out.println("原集合："+a1);

            a1.add(1, "Flash");

            a1.add(0, "ps");

            System.out.println(a1);

            ArrayList a2 = new ArrayList();

            a2.add("javascript");

            a2.add("3dMax");

            a2.add("IBM");

            a1.addAll(0, a2);

            System.out.println(a1);

        }
}
