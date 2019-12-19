package cn.wzhospital.demo.utils;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.date.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;
@Slf4j
public class DbUserUtil {
    /// <summary>
    /// 将字符转换为ＡＳＣ码
    /// </summary>
    /// <param name="asciiCode">字符</param>
    /// <returns>对应的ＡＳＣ码</returns>
    private static int Asc(String character) throws Exception {
        if (character.length() == 1)
        {
            byte[] bytes = character.getBytes();
            int intAsciiCode = (int)bytes[0];
            return (intAsciiCode);
        }
        else
        {
            throw new Exception("字符的长度不对");
        }

    }
    /// <summary>
    /// 返回字符串的长度（包括汉字），汉字长度为2
    /// </summary>
    /// <param name="yourstring">源字符串</param>
    /// <returns>字符串</returns>
    private static int f_length(String yourstring)
    {
        if (StringUtils.isEmpty(yourstring)) return 0;
        int len = yourstring.length();
        byte[] sarr =yourstring.getBytes();
        return sarr.length;
    }

    /// <summary>
    /// 从左边开始取len长个几个字符串
    /// </summary>
    /// <param name="yourstring">源字符串</param>
    /// <param name="len">指定长度</param>
    /// <returns>字符串</returns>
    private static String f_left(String yourstring, int ai_len) throws Exception {
        if (ai_len <= 0) return "";
        if (StringUtils.isEmpty(yourstring)) return "";
        int length = f_length(yourstring);
        if (length <= ai_len)
            return yourstring;
        else
        {
            int tmp = 0;
            int len = 0;
            int okLen = 0;
            int li_asc;
            String ls_return = "";
            for (int i = 0; i < length; i++)
            {
                //获取asc码
                li_asc = Asc(yourstring.substring(i, 1));
                if (li_asc > 127)
                    tmp += 2;
                else
                    len += 1;
                okLen += 1;
                if (tmp + len == ai_len)
                {
                    ls_return = yourstring.substring(0, okLen);
                    break;
                }
                else if (tmp + len > ai_len)
                {
                    ls_return = yourstring.substring(0, okLen - 1);
                    break;
                }
            }
            return ls_return;
        }
    }
    /// <summary>
    /// 判断字符串是否未数字
    /// </summary>
    /// <param name="as_source">源字符串</param>
    /// <returns>true 是数字型 ，false 非数字型</returns>
    private static Boolean IsNumber(String as_source)
    {
        for (Character lc_char:as_source.toCharArray())
        {
            if (!Character.isDigit(lc_char) && lc_char != '.') return false;
        }
        return true;
    }
    /// <summary>
    /// 从指定文字开始取len长的字符串
    /// </summary>
    /// <param name="yourstring">源字符串</param>
    /// <param name="start">开始的位置</param>
    /// <param name="len">指定长度</param>
    /// <returns>字符串</returns>
    private static String f_mid(String yourstring, int start, int len)
    {
        if (len <= 0 || start < 0) return "";
        if (StringUtils.isEmpty(yourstring)) return "";
        if (yourstring.length() < start) return "";
        if (yourstring.length() <= start + len - 1) {
            return yourstring.substring(start - 1);
        }
        else {
            return yourstring.substring(start - 1, start - 1+len);
        }
    }

    /// <summary>
    /// 从指定文字开始取len长的字符串
    /// </summary>
    /// <param name="yourstring">源字符串</param>
    /// <param name="start">开始的位置</param>
    /// <param name="len">指定长度</param>
    /// <returns>字符串</returns>
    private static String f_mid(String yourstring, int start)
    {
        int len = yourstring.length();
        return f_mid(yourstring, start, len);
    }

    /// <summary>
    /// 从右边开始取指定len长的字符串
    /// </summary>
    /// <param name="yourstring">源字符串</param>
    /// <param name="len">指定长度</param>
    /// <returns>字符串</returns>
    private static String f_right(String yourstring, int len)
    {
        if (len <= 0) return "";
        if (StringUtils.isEmpty(yourstring)) return "";
        if (yourstring.length() <= len)
            return yourstring;
        else
            return yourstring.substring(yourstring.length() - len, len);
    }
    /// <summary>
    /// 按固定数\随机数加密或解密
    /// </summary>
    /// <param name="as_pass"></param>
    /// <param name="ai_mod">ai_mod = 0 固定数加密,ai_mod = 1 随机数加密,ai_mod = 2 解密</param>
    /// <returns>加／解密后的字符串</returns>
    public static String desCiphertext(String as_pass, int ai_mod) throws Exception {
        if (as_pass.length() > 24)
            return "";
        int li_i, li_len, li_pos;
        String ls_cpu = "", ls_tmp = "", ls_ret = "";
        String[][] ls_pass =new String[14][2] ;
        if (ai_mod == 0 || ai_mod == 1)
        {
            if (StringUtils.isEmpty(as_pass)) return "";
            as_pass = f_left(as_pass, 10);
            if (ai_mod == 1)
            {
              ls_cpu = DateUtil.format(DateUtil.date(System.currentTimeMillis()), "mmss");
            }
            else
            {
                ls_cpu = f_left(Convert.toStr((f_length(as_pass)) / 0.000412), 4);     //不能改变
            }
            li_len = f_length(as_pass);
            ls_ret = as_pass + ls_cpu;
            for (li_i = 1; li_i <= 4; li_i++)
            {
                li_pos =Convert.toInt(f_mid(ls_cpu, li_i, 1));
                li_pos = li_pos % (li_len + 4);
               // li_pos =mod(li_pos ,(li_len + 4));
                if (li_pos == 0) li_pos = 1;
                ls_tmp = ls_tmp + f_mid(ls_ret, li_pos, 1);
                ls_pass[li_pos - 1][1] = "1";
                li_pos = (9 - li_pos) % (li_len + 4);
               // li_pos = mod((9 - li_pos) , (li_len + 4));
                if (li_pos == 0) li_pos = 1;
                ls_pass[li_pos - 1][1] = "1";
                ls_tmp = ls_tmp + f_mid(ls_ret, li_pos, 1);
            }
            for (li_i = 1; li_i <= li_len + 4; li_i++)
            {
                if (ls_pass[li_i - 1] [1].equals("1")) continue;
                ls_tmp = ls_tmp + f_mid(ls_ret, li_i, 1);
            }
            ls_ret = Convert.toStr(li_len - 1) + ls_cpu + ls_tmp;
            return ls_ret;
        }
        else if (ai_mod == 2)
        {
            if (StringUtils.isEmpty(as_pass)) return "";
            li_len =Convert.toInt(f_mid(as_pass, 1, 1)) + 1;
            ls_cpu = f_mid(as_pass, 2, 4);
            ls_ret = f_mid(as_pass, 6, f_length(as_pass));
            for (li_i = 1; li_i <= 4; li_i++)
            {
                li_pos = Convert.toInt(f_mid(ls_cpu, li_i, 1));
                li_pos = li_pos % (li_len + 4);
               // li_pos = mod(li_pos ,(li_len + 4));
                if (li_pos == 0) li_pos = 1;
                ls_pass[li_pos - 1][1] = "1";
                ls_pass[li_pos - 1][0] = f_mid(ls_ret, 2 * li_i - 1, 1);
                li_pos = (9 - li_pos) % (li_len + 4);
               // li_pos = mod((9 - li_pos) , (li_len + 4));
                li_pos = li_pos % (li_len + 4);
               // li_pos =mod(li_pos , (li_len + 4));
                if (li_pos == 0) li_pos = 1;
                ls_pass[li_pos - 1][1] = "1";
                ls_pass[li_pos - 1][0] = f_mid(ls_ret, 2 * li_i, 1);
            }
            li_pos = 9;
            for (li_i = 1; li_i <= li_len; li_i++)
            {
                if (!"1".equals(ls_pass[li_i - 1][1]))
                {
                    ls_pass[li_i - 1][0] = f_mid(ls_ret, li_pos, 1);
                    li_pos++;
                }
                ls_tmp = ls_tmp + ls_pass[li_i - 1][0];
            }
            return ls_tmp;
        }
        else
        {
            return as_pass;
        }
    }
    private static int mod(int x,int y){
       return Math.floorMod(x,y);
    }
}
