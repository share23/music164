package com.share.log.util;

import java.io.InputStream;
import java.util.Properties;

/**
 * @user: share
 * @date: 2019/2/18
 * @description: 配置文件工具类
 */
public class ConfUtil {

    static Properties prop ;

    static {

        prop = new Properties();

        InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream("jdbc.properties");

        try {
            prop.load(is);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    public String driver = prop.getProperty("driver");
    public String url = prop.getProperty("url");
    public String username = prop.getProperty("username");
    public String password = prop.getProperty("password");

}
