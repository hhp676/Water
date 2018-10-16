package com.hongguaninfo.hgdf.adp.core.utils.email;

import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import com.hongguaninfo.hgdf.core.utils.logging.Log;
import com.hongguaninfo.hgdf.core.utils.logging.LogFactory;
import com.hongguaninfo.hgdf.core.utils.props.config.Config;

/**
 * 纯文本邮件服务类.
 * 
 * @author henry
 */
public class SimpleMailService {
	private static Log log = LogFactory.getLog(SimpleMailService.class);

	private JavaMailSender mailSender;
	private String textTemplate;

	@Config("mail.from")
	private String mailFrom;
	
	/**
	 * 发送纯文本的用户修改通知邮件.
	 */
	public void sendNotificationMail(String mailTo, String userName) {
		SimpleMailMessage msg = new SimpleMailMessage();
		msg.setFrom(mailFrom);
		msg.setTo("suiguangshuai@hongguaninfo.com");
		msg.setSubject("测试文本邮件");

		// 将用户名与当期日期格式化到邮件内容的字符串模板
		String content = String.format(textTemplate, userName, new Date());
		msg.setText(content);

		try {
			mailSender.send(msg);
			log.info("纯文本邮件已发送至 "+StringUtils.join(msg.getTo()+"."));
		} catch (Exception e) {
			log.error("发送邮件失败", e);
		}
	}

	/**
	 * Spring的MailSender.
	 */
	public void setMailSender(JavaMailSender mailSender) {
		this.mailSender = mailSender;
	}

	/**
	 * 邮件内容的字符串模板.
	 */
	public void setTextTemplate(String textTemplate) {
		this.textTemplate = textTemplate;
	}
}
