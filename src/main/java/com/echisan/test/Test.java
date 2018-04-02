package com.echisan.test;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author E73AN
 */
public class Test {

    public static void main(String[] args) {

//        String fileName = "566141d9-1fa9-4c17-a44e-291fb3955fb1用例图2.png";
////        File file = new File("F:\\学习\\大三下\\实训\\upload\\2018\\2\\14\\566141d9-1fa9-4c17-a44e-291fb3955fb1用例图2.png");
//        System.out.println(fileName.substring(fileName.lastIndexOf(".")));
//
//        Date date = new Date();
//        System.out.println(date.getTime());

        Calendar calendar = Calendar.getInstance();
        System.out.println(calendar.get(Calendar.MONTH));
        System.out.println(calendar.get(Calendar.DAY_OF_MONTH));
        System.out.println(calendar.get(Calendar.DAY_OF_WEEK_IN_MONTH));
        System.out.println(calendar.get(Calendar.WEEK_OF_MONTH));
    }

}
