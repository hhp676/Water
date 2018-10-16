/**
* Project Name:hutils
* File Name:EncodeUtil.java
* Package Name:com.hginfo.hutils
* Date:2016年9月5日下午1:53:14
* Copyright (c) 2016, hongguaninfo.com All Rights Reserved.
*
*/

package com.hongguaninfo.hgdf.core.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.lang3.StringEscapeUtils;


/**
 * 编码转换工具类。
* ClassName:EncodeUtil <br/>
* Date: 2016年9月5日 下午1:53:14 <br/>
* @author licheng
*/
public class EncodeUtil extends StringEscapeUtils {
    
    /**
     * UTF-8编码。
     */
    public static final String  ENCODING_UTF8      = "UTF-8";
    
    /**
     * ISO8859-1编码。
     */
    public static final String  ENCODING_ISO8859_1 = "ISO8859-1";
    
    /**
     * GBK编码。
     */
    public static final String  ENCODING_GBK       = "GBK";
    
    /**
     * 
     */
    private static final char[] BASE62             = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz"
        .toCharArray();
    
    /**
     * Hex编码。
    * @author licheng
    * @param input 输入数据
    * @return 编码结果
    * @since V1.0.0
     */
    public static String encodeHex(byte[] input) {
        return Hex.encodeHexString(input);
    }
    
    /**
     * Hex解码。
    * @author licheng
    * @param input 输入数据
    * @return 解码结果
    * @since V1.0.0
     */
    public static byte[] decodeHex(String input) {
        try {
            return Hex.decodeHex(input.toCharArray());
        } catch (DecoderException e) {
        }
        return null;
    }
    
    
    
    
    /**
     * URL编码, Encode默认为UTF-8。
    * @author licheng
    * @param part 输入数据
    * @return 编码结果
    * @since V1.0.0
     */
    public static String urlEncode(String part) {
        try {
            return URLEncoder.encode(part, ENCODING_UTF8);
        } catch (UnsupportedEncodingException e) {
        }
        return null;
    }
    
    /**
     * URL解码, Encode默认为UTF-8。
    * @author licheng
    * @param part 输入数据
    * @return 解码结果
    * @since V1.0.0
     */
    public static String urlDecode(String part) {
        
        try {
            return URLDecoder.decode(part, ENCODING_UTF8);
        } catch (UnsupportedEncodingException e) {
        }
        return null;
    }
    
    /**
     * 将字符串从源编码转换为目的编码。
    * @author licheng
    * @param sourceStr 字符串
    * @param sourceEncoding 源编码
    * @param desEncoding 目标编码
    * @return 结果
    * @since V1.0.0
     */
    public static String convertString(String sourceStr, String sourceEncoding,
        String desEncoding) {
        if (sourceStr == null) {
            return null;
        }
        String outString = "";
        try {
            outString = new String(sourceStr.getBytes(sourceEncoding), desEncoding);
        } catch (java.io.UnsupportedEncodingException e) {
            
        }
        return outString;
    }
    
    /**
     * UTF8转GBK。
    * @author licheng
    * @param sourceStr 字符串
    * @return 转码结果
    * @since V1.0.0
     */
    public static String utf82Gbk(String sourceStr) {
        return convertString(sourceStr, ENCODING_UTF8, ENCODING_GBK);
    }
    
    /**
     * GBK转UTF8。
    * @author licheng
    * @param sourceStr 字符串
    * @return 转码结果
    * @since V1.0.0
     */
    public static String gbk2utf8(String sourceStr) {
        return convertString(sourceStr, ENCODING_GBK, ENCODING_UTF8);
    }
    
    /**
     * UTF8转ISO8859-1。
    * @author licheng
    * @param sourceStr 字符串
    * @return 转码结果
    * @since V1.0.0
     */
    public static String utf82iso(String sourceStr) {
        return convertString(sourceStr, ENCODING_UTF8, ENCODING_ISO8859_1);
    }
    
    /**
     * ISO8859-1转UTF8。
    * @author licheng
    * @param sourceStr 字符串
    * @return 转码结果
    * @since V1.0.0
     */
    public static String iso2utf8(String sourceStr) {
        return convertString(sourceStr, ENCODING_ISO8859_1, ENCODING_UTF8);
    }
}
