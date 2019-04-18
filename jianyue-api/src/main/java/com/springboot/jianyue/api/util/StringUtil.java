package com.springboot.jianyue.api.util;

import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;
import java.util.Random;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtil {
    //获得系统时间
    public static String getDateString(Date date) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return df.format(date);
    }
    //将字符串转为Base64编码
    public static String getBase64Encoder(String srcString) {
        String resultStr = "";
        try {
            resultStr = Base64.getEncoder().encodeToString(srcString.getBytes("utf-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return resultStr;
    }
    //将Base64编码解码
    public static String getBase64Decoder(String srcString) {
        byte[] bytes = Base64.getDecoder().decode(srcString);
        return new String(bytes);
    }

    public static String getUUIDString() {
        return UUID.randomUUID().toString();
    }

    //生成首位不为 0 的六位验证码
    public static String getVerifyCode() {
        Random randomHead = new Random();
        Random random = new Random();
        //生成一个 1 - 9 的随机数，作为第一位
        String head = String.valueOf(randomHead.nextInt(9) + 1);
        //生成一个 0 - 9 的随机数，作为后五位
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < 5; i++) {
            stringBuilder.append(String.valueOf(random.nextInt(10)));
        }
        return head + stringBuilder.toString();
    }

    public static String getUUID32() {
        String uuid = UUID.randomUUID().toString().replace("-", "").toLowerCase();
        return uuid;
    }

    //过滤html和markdown标签成纯文本
    public static String handleContent(String str) {

        // 只允许字母和数字 // String regEx ="[^a-zA-Z0-9]";
        String regEx = "!\\[\\]\\((.*?)\\)";
        String regEx1 = "[\\\\\\`\\*\\_\\[\\]\\#\\+\\-\\!\\>\n\t]";
        Pattern p = Pattern.compile(regEx);
        Pattern q = Pattern.compile(regEx1);
        Matcher m = p.matcher(str);
        String str1 = m.replaceAll("").trim();
        Matcher n = q.matcher(str1);
        String content = n.replaceAll("").trim();
        if (content.length() > 80) {
            return content.substring(0, 80) + "...";
        } else {
            return content;
        }
    }

    //过滤html和markdown标签成纯文本
    public static String handleThumbnailContent(String str) {
        // 只允许字母和数字 // String regEx ="[^a-zA-Z0-9]";
        String regEx = "!\\[\\]\\((.*?)\\)";
        String regEx1 = "[\\\\\\`\\*\\_\\[\\]\\#\\+\\-\\!\\>\n\t]";
        Pattern p = Pattern.compile(regEx);
        Pattern q = Pattern.compile(regEx1);
        Matcher m = p.matcher(str);
        String str1 = m.replaceAll("").trim();
        Matcher n = q.matcher(str1);
        String content = n.replaceAll("").trim();
        if (content.length() > 25) {
            return content.substring(0, 25) + "...";
        } else {
            return content;
        }
    }
//    public static void main(String[] args) {
//        String verifyCode = StringUtil.handleContent("");
//        System.out.println(verifyCode);
//    }
}
