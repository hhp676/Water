package com.hongguaninfo.hgdf.drools.test;

import org.drools.KnowledgeBase;
import org.drools.KnowledgeBaseFactory;
import org.drools.builder.KnowledgeBuilder;
import org.drools.builder.KnowledgeBuilderFactory;
import org.drools.builder.ResourceType;
import org.drools.io.ResourceFactory;
import org.drools.runtime.StatefulKnowledgeSession;
import org.junit.Test;

/**
 * This is a sample class to launch a rule.
 */
public class DroolsTest {

	public static final void main(String[] args) {
		try {
			// load up the knowledge base
			// KieServices ks = KieServices.Factory.get();
			// KieContainer kContainer = ks.getKieClasspathContainer();
			// KieSession kSession = kContainer.newKieSession("ksession-rules");

			KnowledgeBuilder kBuilder = KnowledgeBuilderFactory
					.newKnowledgeBuilder();
			// 第一步：调用规则和规则流文件
			kBuilder.add(ResourceFactory.newInputStreamResource(Test.class
					.getResourceAsStream("/rules/Sample.drl")), ResourceType.DRL);

			if (kBuilder.hasErrors()) {
				System.out.println(kBuilder.getErrors().toString());
			}
			// 第二步：得到knowledgeBase对象，并向其中添加规则包
			KnowledgeBase kbaBase = KnowledgeBaseFactory.newKnowledgeBase();
			kbaBase.addKnowledgePackages(kBuilder.getKnowledgePackages());
			// 第三步：得到当前工作内存对象
			StatefulKnowledgeSession kSession = kbaBase
					.newStatefulKnowledgeSession();

			// go !
			Message message = new Message();
			message.setMessage("Hello World");
			message.setStatus(Message.HELLO);
			kSession.insert(message);
			kSession.fireAllRules();
		} catch (Throwable t) {
			t.printStackTrace();
		}
	}

	public static class Message {

		public static final int HELLO = 0;
		public static final int GOODBYE = 1;

		private String message;

		private int status;

		public String getMessage() {
			return this.message;
		}

		public void setMessage(String message) {
			this.message = message;
		}

		public int getStatus() {
			return this.status;
		}

		public void setStatus(int status) {
			this.status = status;
		}

	}

}
