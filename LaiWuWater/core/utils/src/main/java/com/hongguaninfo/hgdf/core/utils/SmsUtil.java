package com.hongguaninfo.hgdf.core.utils;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.hongguaninfo.hgdf.core.utils.logging.Log;
import com.hongguaninfo.hgdf.core.utils.logging.LogFactory;

public class SmsUtil {
    
    public static final Log LOG = LogFactory.getLog(SmsUtil.class);
    
    /**
     * @Title: 判断是否为手机号码
     * @Description:
     * @since 1.0.0
     */
    public static boolean isMobileNO(String mobiles) {
        Pattern p = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(14[0-9])|(18[0-9]))\\d{8}$");
        Matcher m = p.matcher(mobiles);
        LOG.debug(m.matches() + "---");
        return m.matches();
    }

    /**
     * @Title: 获取token
     * @Description:
     * @since 1.0.0
     */
    public static String getToken() {
        /*return UUID.randomUUID().toString();*/
        return String.valueOf(System.currentTimeMillis());
    }

    /**
     * @Title: 获取动态验证码
     * @Description:
     * @since 1.0.0
     */
    public static String getDyVfCode() {
        return getSixNum();
    }

    /**
     * @Title: getSixChar
     * @Description: 生成6位字符
     * @since 1.0.0
     */
    private static String getSixChar() {
        String str = "";
        Random rd = new Random();
        for (int i = 0; i < 6; i++) {
            int itmp = rd.nextInt(0) + 65;
            char ctmp = (char) itmp;
            str += ctmp;
        }
        return str;
    }

    /**
     * @Title: getSixChar
     * @Description: 生成6位数字
     * @since 1.0.0
     */
    private static String getSixNum() {
        int[] array = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 };
        Random rand = new Random();
        for (int i = 10; i > 1; i--) {
            int index = rand.nextInt(i);
            int tmp = array[index];
            array[index] = array[i - 1];
            array[i - 1] = tmp;
        }
        StringBuffer result = new StringBuffer();
        for (int i = 0; i < 6; i++){
            result = result.append(String.valueOf(array[i]));
        }
        return String.valueOf(result);
    }

    private static String generateWord() {
        String[] beforeShuffle = new String[] { "2", "3", "4", "5", "6", "7", "8", "9", "A", "B", "C", "D", "E", "F",
                "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z" };
        List list = Arrays.asList(beforeShuffle);
        Collections.shuffle(list);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            sb.append(list.get(i));
        }
        String afterShuffle = sb.toString();
        String result = afterShuffle.substring(5, 9);
        return result;
    }

    public static void main(String[] args) throws IOException {
        LOG.debug(String.valueOf(SmsUtil.isMobileNO("11064353393")));
    }
}
