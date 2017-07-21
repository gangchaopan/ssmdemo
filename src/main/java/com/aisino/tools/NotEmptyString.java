package com.aisino.tools;

/**
 * Created by gangchaopan on 2017/7/17.
 */
public class NotEmptyString {
    /**
     * isNotBlank
     * 等价于StringUtils.isNotBlank
     * @param value
     * @return boolean
     */
    public static boolean isNotBlank(String value)
    {
        return null != value && !"".equals(value);
    }

    /**
     * isNotEmpty
     * 等价于StringUtils.isNotEmpty
     * @param value
     * @return boolean
     */
    public static boolean isNotEmpty(String value)
    {
        return null != value && !"".equals(value);
    }

    public static void main(String[] args) {
        String num = null;
        System.out.println(isNotBlank(num));
    }
}
