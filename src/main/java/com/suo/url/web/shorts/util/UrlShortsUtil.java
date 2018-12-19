package com.suo.url.web.shorts.util;


/**
 * @author changle
 *         time 18/12/6.
 *         url 压缩工具
 */
public class UrlShortsUtil {
    /*public static void main(String[] args) {
        // 长连接： http://tech.sina.com.cn/i/2011-03-23/11285321288.shtml
        // 新浪解析后的短链接为： http://t.cn/h1jGSC
        String sLongUrl = "http://tech.sina.com.cn/i/2011-03-23/11285321288.shtml" ;
        // 3BD768E58042156E54626860E241E999
        String aResult = shortenCodeUrl(sLongUrl, 10);
        System.out.println(aResult);
    }*/

    public static String shortenCodeUrl(String longUrl, int urlLength) {
        if (urlLength < 4 ) {
            urlLength = 8;// defalut length
        }
        StringBuilder sbBuilder = new StringBuilder(urlLength + 2);
        String md5Hex = "";
        int nLen = 0;
        while (nLen < urlLength) {
            // 这个方法是先 md5 再 base64编码 参见
            // https://github.com/ndxt/centit-commons/blob/master/centit-utils/src/main/java/com/centit/support/security/Md5Encoder.java
            md5Hex = Md5Encoder.encodeBase64(md5Hex + longUrl);
            for(int i=0;i<md5Hex.length();i++){
                char c = md5Hex.charAt(i);
                if(c != '/' && c != '+'){
                    sbBuilder.append(c);
                    nLen ++;
                }
                if(nLen == urlLength){
                    break;
                }
            }
        }
        return sbBuilder.toString();
    }

}
