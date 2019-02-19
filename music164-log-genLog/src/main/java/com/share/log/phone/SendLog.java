package com.share.log.phone;

import com.share.log.util.GenLogAggUtil;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.Random;

/**
 * @user: share
 * @date: 2019/2/18
 * @description: 发送日志到nginx服务器
 */
public class SendLog {
    public static void main(String[] args) throws Exception {

        Random r = new Random();

        //格式化数字串
        DecimalFormat df = new DecimalFormat("000000");

        //发送的日志个数
        for (; ; ) {
            String deviceId = "Device" + df.format(r.nextInt(100) + 1);
            String s = GenLogAggUtil.genLogAgg(deviceId);

            String strUrl = "http://192.168.1.103:8089/index.html";
            URL url = new URL(strUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            //定义post请求类型
            conn.setRequestMethod("POST");
            conn.setRequestProperty("content-Type", "application/json");
            //自定义变量到nginx服务器
            conn.setRequestProperty("client_time", System.currentTimeMillis() + "");
            //允许输出到服务器
            conn.setDoOutput(true);

            OutputStream os = conn.getOutputStream();

            os.write(s.getBytes());
            os.flush();
            os.close();
            System.out.println(conn.getResponseCode());
//            Thread.sleep(3000);
//            Thread.sleep(500);
        }

    }
}
