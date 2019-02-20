package com.share.userdraw;

import org.apache.hadoop.hive.ql.exec.Description;
import org.apache.hadoop.hive.ql.exec.UDF;

import java.util.Calendar;
import java.util.Date;

/**
 * @user: share
 * @date: 2019/2/20
 * @description: UDF函数实现判断周末和工作日
 */
@Description(
        name = "parseWeek",
        value = "this is a parseweek function",
        extended = "select parseweek('1535935197000') => 2"
)
public class ParseWeek extends UDF {

    public int evaluate(String ts) {
        //ts为timestamp
        long timestamp = Long.parseLong(ts);

        Calendar calendar = Calendar.getInstance();

        calendar.setTime(new Date(timestamp));

        //周一 => 2
        int i = calendar.get(Calendar.DAY_OF_WEEK);

        //周末返回1，工作日返回2
        if (i == 1 || i == 7) {
            return 1;
        } else {
            return 2;
        }
    }


    public static void main(String[] args) {

        int evaluate = new ParseWeek().evaluate("1535935197000");
        System.out.println(evaluate);

    }

}

