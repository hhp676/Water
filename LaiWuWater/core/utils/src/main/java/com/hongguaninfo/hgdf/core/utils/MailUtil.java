package com.hongguaninfo.hgdf.core.utils;

import java.util.Calendar;
import java.util.Date;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import com.hongguaninfo.hgdf.core.utils.logging.Log;
import com.hongguaninfo.hgdf.core.utils.logging.LogFactory;

/**
 * 
 * @ClassName: EmailUtils
 * @Description: 发送邮件工具类
 * @author henry
 * @date 2014-2-10 下午6:50:13
 * 
 */
public class MailUtil {

    public static final Log LOG = LogFactory.getLog(MailUtil.class);
    
    private static String emailSmtp;
    private static String loginName;
    private static String loginPasswd;
    private static String server;

    public MailUtil(String emailSmtp, String loginName, String loginPasswd, String server) {
        setEmailSmtp(emailSmtp);
        setLoginName(loginName);
        setLoginPasswd(loginPasswd);
        setServer(server);
    }

    /**
     * @param reply
     *            回复地址
     * @param to
     *            收信人地址
     * @param subject
     *            邮件标题
     * @param email
     *            邮件正文
     */
    public static boolean sendHtmlEmail(String reply, String to, String subject, String email) {

        Properties props = new Properties();
        Session sendMailSession;
        Transport transport;
        sendMailSession = Session.getInstance(props);

        /*
         * props.put("mail.transport.protocol", "smtp");
         * props.put("mail.smtp.starttls.enable","true");
         * props.put("mail.smtp.host", "gmail-smtp.l.google.com");
         * props.put("mail.smtp.auth", "true");
         */
        props.put("mail.smtp.host", emailSmtp);
        props.put("mail.smtp.port", 465);
        props.put("mail.smtp.auth", "true");

        MimeMessage message = new MimeMessage(sendMailSession);

        try {
            message.setFrom(new InternetAddress("Mingoe<" + server + ">"));
            /*
             * InternetAddress[] ias = new InternetAddress[1]; ias[0] = new
             * InternetAddress(reply); message.setReplyTo(ias);
             */
            InternetAddress[] address = InternetAddress.parse(to);
            message.setRecipients(Message.RecipientType.TO, address);
            message.setSubject(subject);

            // 设置邮件发送时间，将来要和服务器时间互转
            Calendar clientCal = Calendar.getInstance();
            message.setSentDate(clientCal.getTime());

            // 新建一个存放信件内容的BodyPart对象
            BodyPart mdp = new MimeBodyPart();
            // 给BodyPart对象设置内容和格式/编码方式
            mdp.setContent(email, "text/html;charset=UTF-8");
            // 新建一个MimeMultipart对象用来存放BodyPart对象
            Multipart mp = new MimeMultipart();
            // 将BodyPart加入到MimeMultipart对象中
            mp.addBodyPart(mdp);
            // 把mm作为消息对象的内容
            message.setContent(mp);

            transport = sendMailSession.getTransport("smtp");
            transport.connect(emailSmtp, loginName, loginPasswd);
            message.saveChanges();
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();

            return true;
        } catch (AddressException addressException) {
            LOG.error("sendHtmlEmail fail !", addressException);
            return false;
        } catch (MessagingException messagingException) {
            LOG.error("sendHtmlEmail fail !", messagingException);
            return false;
        }
    }

    /**
     * @param from
     *            发信人地址
     * @param to
     *            收信人地址
     * @param subject
     *            邮件标题
     * @param email
     *            邮件正文
     */
    public static boolean sendSimpleEmail(String from, String to, String subject, String email) {

        String smtpServer = emailSmtp; // SMTP服务器名
        try {
            // 创建properties对象
            Properties props = new Properties();
            // 创建邮件服务器
            props.put("mail.smtp.host", smtpServer);
            // 取得默认的Session
            Session session = Session.getDefaultInstance(props, null);
            // 创建一条信息，并定义发信人地址和收信人地址
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            InternetAddress[] address = InternetAddress.parse(to);

            message.setRecipients(Message.RecipientType.TO, address);
            // 设定主题
            message.setSubject(subject);
            // 设定发送时间
            message.setSentDate(new Date());

            MimeBodyPart mbp1 = new MimeBodyPart();
            mbp1.setText(email);
            // 创建Multipart
            Multipart mp = new MimeMultipart();
            mp.addBodyPart(mbp1);
            // 添加 Multipart到Message中
            message.setContent(mp);
            // 发送邮件
            Transport.send(message);

            return true;
        } catch (AddressException addressException) {
            return false;
        } catch (MessagingException messagingException) {
            return false;
        }
    }

    /**
     * 判断Email格式是否正确
     * 
     * @author 2008.10.14
     * @param email
     *            判断的Email
     * @return true or false
     */
    public static boolean isEmail(String email) {
        String regex = "//w+([-+.]//w+)*@//w+([-.]//w+)*//.//w+([-.]//w+)*";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(email);
        return m.find();
    }

    /**
     * 获取Email前缀（@之前的字符串）
     * 
     * @author 2008.10.29
     * @param email
     * @return @之前的字符串
     */
    public static String getEmailName(String email) {
        if (email == null || email.trim().equals("")) {
            return "";
        } else {
            int index = email.indexOf("@");
            if (index == -1) {
                return email;
            }
            return email.substring(0, index);
        }
    }

    public String getEmailSmtp() {
        return emailSmtp;
    }

    public void setEmailSmtp(String emailSmtp) {
        this.emailSmtp = emailSmtp;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getLoginPasswd() {
        return loginPasswd;
    }

    public void setLoginPasswd(String loginPasswd) {
        loginPasswd = loginPasswd;
    }

    public String getServer() {
        return server;
    }

    public void setServer(String server) {
        this.server = server;
    }
}