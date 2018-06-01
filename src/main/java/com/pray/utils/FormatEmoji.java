package com.pray.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Package Name: com.pray.utils
 * Created by Liu xi on 2018/6/1.
 * Version: V1.0
 * Des:
 */
public class FormatEmoji {
    public static String filterEmoji(String nick_name) {
        //nick_name 所获取的用户昵称
        if (nick_name == null) {
            return nick_name;
        }
        Pattern emoji = Pattern.compile("[\ud83c\udc00-\ud83c\udfff]|[\ud83d\udc00-\ud83d\udfff]|[\u2600-\u27ff]",
                Pattern.UNICODE_CASE | Pattern.CASE_INSENSITIVE);
        Matcher emojiMatcher = emoji.matcher(nick_name);
        if (emojiMatcher.find()) {
            //将所获取的表情转换为*
            nick_name = emojiMatcher.replaceAll("*");
            return nick_name;
        }
        return nick_name;
    }
}
