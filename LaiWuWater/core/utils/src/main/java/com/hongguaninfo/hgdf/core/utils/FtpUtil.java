package com.hongguaninfo.hgdf.core.utils;

import com.hongguaninfo.hgdf.core.utils.logging.Log;
import com.hongguaninfo.hgdf.core.utils.logging.LogFactory;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPClientConfig;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;

import java.io.*;
import java.net.SocketException;

public class FtpUtil {
    private FTPClient ftpClient = null;
    private String hostname;
    private int port;
    private String username;
    private String password;
    private String remoteDir;
    private static final Log LOG = LogFactory.getLog(FtpUtil.class);

    // 构造方法

    public FtpUtil(String hostname, int port, String username, String password, String remoteDir) {
        this.hostname = hostname;
        this.port = port;
        this.username = username;
        this.password = password;
        this.remoteDir = remoteDir;
        if (remoteDir == null) {
            remoteDir = File.pathSeparator;
        }
    }

    // 登录

    /**
     * FTP登陆
     * 
     * @throws IOException
     */
    public void login() throws Exception {
        try {

            ftpClient = new FTPClient();
            ftpClient.configure(getFTPClientConfig());
            ftpClient.setDefaultPort(port);
            ftpClient.setControlEncoding("GBK");
            ftpClient.connect(hostname);
            if (!ftpClient.login(username, password)) {
                throw new IOException("FTP登陆失败，请检测登陆用户名和密码是否正确!");
            }
            ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
            ftpClient.changeWorkingDirectory(remoteDir);
        } catch (SocketException e) {
        e.printStackTrace();
    } catch (IOException e) {
        e.printStackTrace();
    } catch (Exception e) {
        e.printStackTrace();
    }
    }

    /**
     * 得到配置
     * 
     * @return
     */
    private FTPClientConfig getFTPClientConfig() {
        // 创建配置对象
        FTPClientConfig conf = new FTPClientConfig(FTPClientConfig.SYST_NT);
        conf.setServerLanguageCode("zh");
        return conf;
    }

    /**
     * 关闭FTP服务器
     */
    public void closeServer() {
        try {
            if (ftpClient != null) {
                ftpClient.logout();
                ftpClient.disconnect();
            }
        } catch (IOException e) {
            LOG.error("closeServer fail!", e);
        }
    }

    /**
     * 链接是否已经打开
     * 
     * @return
     */
    public boolean serverIsOpen() {
        if (ftpClient == null) {
            return false;
        }
        return ftpClient.isConnected();
    }

    /**
     * 列表FTP文件
     * 
     * @param regEx
     * @return
     */
    public String[] listFiles(String regEx) {
        String[] names;
        try {
            names = ftpClient.listNames(regEx);
            if (names == null) {
                return new String[0];
            }
            return names;
        } catch (IOException e) {
            LOG.error("list Files fail!", e);
        }
        return new String[0];
    }

    /**
     * 取得FTP操作类的句柄
     * 
     * @return
     */
    public FTPClient getFtpClient() {
        return ftpClient;
    }

    /**
     * 上传
     * 
     * @throws Exception
     */
    public boolean upload(String localFilePath, String remoteFilePath) throws Exception {
        boolean state = false;
        File localFile = new File(localFilePath);
        if (!localFile.isFile() || localFile.length() == 0) {
            return state;
        }
        FileInputStream localIn = new FileInputStream(localFile);
        state = this.upload(localIn, remoteFilePath);
        localIn.close();
        return state;
    }

    /**
     * 上传
     * 
     * @throws Exception
     */
    public boolean upload(InputStream localIn, String remoteFilePath) throws Exception {
        /* this.createDir(new File(remoteFilePath).getParent()); */
        return ftpClient.storeFile(remoteFilePath, localIn);

      /*  boolean result = false;

        ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
        ftpClient.enterLocalPassiveMode();
        ftpClient.setFileTransferMode(FTP.STREAM_TRANSFER_MODE);
//        ftpClient.setBufferSize(BUF_SIZE);
        //此处可以解释 为什么把file装换成了InputStream流
        InputStream local = null;
        try {
            local = localIn;
            result = ftpClient.storeFile("汇总.xls", local);
        } finally {
            if (local != null) {
                local.close();
            }
        }

        return result;
*/
    }

    /**
     * 是否存在FTP目录
     * 
     * @param dir
     * @return
     */
    public boolean isDirExist(String dir) {
        try {
            int retCode = ftpClient.cwd(dir);
            return FTPReply.isPositiveCompletion(retCode);
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 创建FTP多级目录
     * 
     * @throws IOException
     */
    public void createDir(String dir) throws IOException {
        if (!isDirExist(dir)) {
            File file = new File(dir);
            this.createDir(file.getParent());
            ftpClient.makeDirectory(dir);
        }
    }

    /**
     * 删除文件
     * 
     * @param remoteFilePath
     */
    public boolean delFile(String remoteFilePath) {
        try {
            return ftpClient.deleteFile(remoteFilePath);
        } catch (IOException e) {
            LOG.error("delFile fail!", e);
        }
        return false;
    }

    /**
     * 下载
     * @param localFilePath 本地路径
     * @param remoteFilePath 远程路径
     * @throws Exception
     */
    public void download(String localFilePath, String remoteFilePath) throws Exception {
        File file = new File(localFilePath);
        OutputStream localOut = new FileOutputStream(file);
        ftpClient.enterLocalPassiveMode();
        ftpClient.retrieveFile(remoteFilePath, localOut);
        localOut.flush();
        if (localOut != null) {
            localOut.close();
        }
    }

    /**
     * 下载
     * 
     * @throws Exception
     */
    public void download(OutputStream localOut, String remoteFilePath) throws Exception {
        boolean result = ftpClient.retrieveFile(remoteFilePath, localOut);
        if (!result) {
            throw new IOException("文件下载失败!");
        }
    }

    /**
     * 文件移动
     * 
     * @param fromPath
     * @param toPath
     * @throws Exception
     */
    public void reName(String fromPath, String toPath) throws Exception {
        ftpClient.rename(fromPath, toPath);
    }

    public String getRemoteDir() {
        return remoteDir;
    }

    /**
     * 下载文件
     * 返回byte[]
     * @param fileName 需要下载的文件名
     * @return
     * @throws Exception
     */
    public byte[] downFileByte(String fileName){
        byte[] return_arraybyte=null;
        if(ftpClient!=null){
            try{
                FTPFile[] files= ftpClient.listFiles();
                for(FTPFile file:files){
                    if(file.getName().equals(fileName)){
                        InputStream ins=ftpClient.retrieveFileStream(file.getName());
                        ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
                        byte[] buf = new byte[204800];
                        int bufsize = 0;
                        while ((bufsize = ins.read(buf, 0, buf.length)) != -1) {
                            byteOut.write(buf, 0, bufsize);
                        }
                        return_arraybyte = byteOut.toByteArray();
                        byteOut.close();
                        ins.close();
                        break;
                    }
                }
            }catch(Exception e){
                e.printStackTrace();
            }finally{
//                closeConnect();
            }
        }
        return return_arraybyte;
    }
}
