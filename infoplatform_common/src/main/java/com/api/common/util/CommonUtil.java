package com.api.common.util;

import com.api.common.constant.CommonConstant;
import org.apache.commons.lang3.StringUtils;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.List;

/**
 * 公共类
 *
 * @author 15293
 * @version 1.0
 * @since 2019/8/5 10:23
 **/
public class CommonUtil {

    /**
     * 获取SHA1加密值
     **/
    public static String sha1Encoder(String str) {
        if (str == null || str.length() == 0) return null;

        char[] hexDigits = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
        try {
            MessageDigest mdTemp = MessageDigest.getInstance("SHA1");
            mdTemp.update(str.getBytes(StandardCharsets.UTF_8));

            byte[] md = mdTemp.digest();
            int j = md.length;
            char[] buf = new char[j * 2];
            int k = 0;
            for (byte byte0 : md) {
                buf[k++] = hexDigits[byte0 >>> 4 & 0xf];
                buf[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(buf);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * List转换成string
     *
     * @param list List集合
     * @return String
     **/
    public static String transferListToString(List<String> list) {
        return StringUtils.join(list, CommonConstant.COMMA_SEPARATOR);
    }
}
