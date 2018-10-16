package com.hongguaninfo.hgdf.core.utils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

import com.hongguaninfo.hgdf.core.utils.logging.Log;
import com.hongguaninfo.hgdf.core.utils.logging.LogFactory;

/**
 * 功能:zip压缩、解压 说明:本程序通过ZipOutputStream和ZipInputStream实现了zip压缩和解压功能.
 * 问题:由于java.util.zip包并不支持汉字,当zip文件中有名字为中文的文件时,
 * 就会出现异常:"Exception  in thread "main " java.lang.IllegalArgumentException at
 * java.util.zip.ZipInputStream.getUTF8String(ZipInputStream.java:285) 解决:
 * 　　方法1、修改import java.util.zip.ZipInputStream和ZipOutputStream.
 * java.util.zip只支持UTF-8,Ant里面可以指定编码. 　　方法2、使用Apache Ant里提供的zip工具。
 * 不使用java.util.zip的包,把ant.jar放到classpath中. 程序中使用import org.apache.tools.zip.*;
 * 
 * 仅供编程学习参考.
 * 
 * @author henry
 * @date 2014-02-5
 * @Usage: 压缩:java Zip -zip "directoryName" 解压:java Zip -unzip "fileName.zip"
 */

public class ZipUtil {
    public static final Log LOG = LogFactory.getLog(ZipUtil.class);
    // 解压Zip
    private ZipInputStream zipIn;
    // 压缩Zip
    private ZipOutputStream zipOut;
    private ZipEntry zipEntry;
    // size of bytes
    private static int bufSize;
    private byte[] buf;
    private int readedBytes;

    public ZipUtil() {
        this(512);
    }

    public ZipUtil(int bufSize) {
        this.bufSize = bufSize;
        this.buf = new byte[this.bufSize];
    }

    // 压缩文件夹内的文件
    // zipDirectoryPath:需要压缩的文件夹名
    public void doZip(String zipDirectory) {
        File zipDir;

        zipDir = new File(zipDirectory);
        // 压缩后生成的zip文件名
        String zipFileName = zipDir.getName() + ".zip";

        try {
            this.zipOut = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(zipFileName)));
            handleDir(zipDir, this.zipOut);
            this.zipOut.close();
        } catch (IOException ioe) {
            LOG.error("doZip fail", ioe);
        }
    }

    // 由doZip调用,递归完成目录文件读取
    private void handleDir(File dir, ZipOutputStream zipOut) throws IOException {
        FileInputStream fileIn;
        File[] files;

        files = dir.listFiles();
        // 如果目录为空,则单独创建之.
        if (files.length == 0) {
            // ZipEntry的isDirectory()方法中,目录以"/"结尾.
            this.zipOut.putNextEntry(new ZipEntry(dir.toString() + "/"));
            this.zipOut.closeEntry();
        } else {// 如果目录不为空,则分别处理目录和文件.
            for (File fileName : files) {
                if (fileName.isDirectory()) {
                    handleDir(fileName, this.zipOut);
                } else {
                    fileIn = new FileInputStream(fileName);
                    this.zipOut.putNextEntry(new ZipEntry(fileName.toString()));

                    while ((this.readedBytes = fileIn.read(this.buf)) > 0) {
                        this.zipOut.write(this.buf, 0, this.readedBytes);
                    }

                    this.zipOut.closeEntry();
                }
            }
        }
    }

    // 解压指定zip文件
    // unZipfileName需要解压的zip文件名
    public void unZip(String unZipfileName) {
        FileOutputStream fileOut;
        File file;

        try {
            this.zipIn = new ZipInputStream(new BufferedInputStream(new FileInputStream(unZipfileName)));

            while ((this.zipEntry = this.zipIn.getNextEntry()) != null) {
                file = new File(this.zipEntry.getName());
                if (this.zipEntry.isDirectory()) {
                    file.mkdirs();
                } else {
                    // 如果指定文件的目录不存在,则创建之.
                    File parent = file.getParentFile();
                    if (!parent.exists()) {
                        parent.mkdirs();
                    }

                    fileOut = new FileOutputStream(file);
                    while ((this.readedBytes = this.zipIn.read(this.buf)) > 0) {
                        fileOut.write(this.buf, 0, this.readedBytes);
                    }
                    fileOut.close();
                }
                this.zipIn.closeEntry();
            }
        } catch (IOException ioe) {
            LOG.error("unZip fail", ioe);
        }
    }

    // 设置缓冲区大小
    public void setBufSize(int bufSize) {
        this.bufSize = bufSize;
    }

    // 测试Zip类
    public static void main(String[] args) throws Exception {
        if (args.length == 2) {
            String name = args[1];
            ZipUtil zipUtil = new ZipUtil();

            if (args[0].equals("-zip")) {
                zipUtil.doZip(name);
            } else if (args[0].equals("-unzip")) {
                zipUtil.unZip(name);
            }
        } else {
            throw new Exception("Arguments error!");
        }
    }
}