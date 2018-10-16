package com.hongguaninfo.hgdf.adp.core.utils.email;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import com.hongguaninfo.hgdf.core.utils.logging.Log;
import com.hongguaninfo.hgdf.core.utils.logging.LogFactory;
import com.hongguaninfo.hgdf.core.utils.props.config.Config;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

/**
 * MIME邮件服务类.
 * 
 * 演示由Freemarker引擎生成的的html格式邮件, 并带有附件.
 * 
 * @author henry
 */
public class MimeMailService {

	private static final String DEFAULT_ENCODING = "utf-8";

	private static Log log = LogFactory.getLog(MimeMailService.class);

	private JavaMailSender mailSender;

	private Template template;

	@Config("mail.from")
	private String mailFrom;
	
	/**
	 * 发送MIME格式的用户修改通知邮件.
	 */
	public void sendNotificationMail(String userName) {

		try {
			MimeMessage msg = mailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(msg, true, DEFAULT_ENCODING);

			helper.setTo("suiguangshuai@hongguaninfo.com");
			helper.setFrom(mailFrom);
			helper.setSubject("测试mine邮件");

			String content = generateContent(userName);
			helper.setText(content, true);

			File attachment = generateAttachment();
			helper.addAttachment("mailAttachment.txt", attachment);

			mailSender.send(msg);
			log.info("HTML版邮件已发送至suiguangshuai@hongguaninfo.com");
		} catch (MessagingException e) {
			log.error("构造邮件失败", e);
		} catch (Exception e) {
			log.error("发送邮件失败", e);
		}
	}

	/**
	 * 使用Freemarker生成html格式内容.
	 */
	private String generateContent(String userName) throws MessagingException {
		try {
			Map context = Collections.singletonMap("userName", userName);
			return FreeMarkerTemplateUtils.processTemplateIntoString(template, context);
		} catch (IOException e) {
			log.error("生成邮件内容失败, FreeMarker模板不存在", e);
			throw new MessagingException("FreeMarker模板不存在", e);
		} catch (TemplateException e) {
			log.error("生成邮件内容失败, FreeMarker处理失败", e);
			throw new MessagingException("FreeMarker处理失败", e);
		}
	}

	/**
	 * 获取classpath中的附件.
	 */
	private File generateAttachment() throws MessagingException {
		try {
			Resource resource = new ClassPathResource("/email/mailAttachment.txt");
			return resource.getFile();
		} catch (IOException e) {
			log.error("构造邮件失败,附件文件不存在", e);
			throw new MessagingException("附件文件不存在", e);
		}
	}

	/**
	 * Spring的MailSender.
	 */
	public void setMailSender(JavaMailSender mailSender) {
		this.mailSender = mailSender;
	}

	/**
	 * 注入Freemarker引擎配置,构造Freemarker 邮件内容模板.
	 */
	public void setFreemarkerConfiguration(Configuration freemarkerConfiguration) throws IOException {
		// 根据freemarkerConfiguration的templateLoaderPath载入文件.
		template = freemarkerConfiguration.getTemplate("mailTemplate.ftl", DEFAULT_ENCODING);
	}
}
