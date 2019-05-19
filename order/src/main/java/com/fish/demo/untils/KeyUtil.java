package com.fish.demo.untils;

import java.util.Random;

/**
 * Created by mingkunyu on 2019-05-19
 */
public class KeyUtil {
    /**
     * 生成唯一主键
     * 格式：时间+随机数
     */
    public  static  synchronized String genUniqueKey(){
        Random random=new Random();
        Integer number=random.nextInt(900000)+100000; //0+100000-900000+100000的随机数
        return System.currentTimeMillis()+String.valueOf(number);
    }
}
