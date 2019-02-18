import com.alibaba.fastjson.JSON;
import com.share.log.common.AppBaseLog;
import com.share.log.common.AppEventLog;
import com.share.log.util.DictUtil;
import org.junit.Test;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

/**
 * @user: share
 * @date: 2019/2/18
 * @description: 测试生成Json格式的日志数据
 */
public class TestGenJson {

    @Test
    public void test1() throws Exception {
        Map<String, List<String>> map = DictUtil.map;

        AppEventLog eventLog = new AppEventLog();

        //如果Event不是baseLog的子类，那么直接设置Event的所有字段
        Class clazz = AppEventLog.class;
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            //设置访问权限
            field.setAccessible(true);
            //对象设置字段值
            String value = DictUtil.randomValue(map, field.getName().toLowerCase());
            field.set(eventLog,value);
        }

        //如果是子类，把base的字段也要加上
        if (eventLog instanceof AppBaseLog){
            //获取所有字段baseLog+EventLog

            Class clazz2 = AppBaseLog.class;

            Field[] fields2 = clazz2.getDeclaredFields();
            for (Field field : fields2) {
                //设置访问权限
                field.setAccessible(true);
                if(!(field.getModifiers()+"").contains("5")){
                    String value = DictUtil.randomValue(map, field.getName().toLowerCase());
                    field.set(eventLog,value);
                }
            }

        }
        String s = JSON.toJSONString(eventLog);
        System.out.println(s);

    }
}

