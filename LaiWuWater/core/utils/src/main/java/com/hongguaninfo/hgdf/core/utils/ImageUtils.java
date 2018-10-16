package com.hongguaninfo.hgdf.core.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import com.hongguaninfo.hgdf.core.utils.logging.Log;
import com.hongguaninfo.hgdf.core.utils.logging.LogFactory;
import com.hongguaninfo.hgdf.core.utils.props.PropertyUtils;

public class ImageUtils {

    public static final Log LOG = LogFactory.getLog(ImageUtils.class);
    
    /***
     * 图片转换成二进制流
     * 
     * @author tangchen
     * @since 2012-11-09
     */
    public static String imageToStream(String imageUrl) {
        String content = "";
        try {
            java.io.ByteArrayOutputStream bos = new java.io.ByteArrayOutputStream();
            File file = new File(imageUrl);
            InputStream in = new FileInputStream(file);
            byte[] buff = new byte[1024];
            int len = 0;
            while ((len = in.read(buff)) != -1) {
                bos.write(buff, 0, len);
            }
            byte[] result = bos.toByteArray();// 这就是jpg的字节数组。
            content = byte2hex(result);
            in.close();
            bos.close();
        } catch (FileNotFoundException e) {
            LOG.error("imageToStream fail !", e);
        } catch (IOException e) {
            LOG.error("imageToStream fail !", e);
        }
        return content;
    }

    /**
     * 格式化byte
     * 
     * @param b
     * @return
     */
    public static String byte2hex(byte[] b) {
        char[] Digit = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };
        char[] out = new char[b.length * 2];
        for (int i = 0; i < b.length; i++) {
            byte c = b[i];
            out[i * 2] = Digit[(c >>> 4) & 0X0F];
            out[i * 2 + 1] = Digit[c & 0X0F];
        }
        return new String(out);
    }
}
