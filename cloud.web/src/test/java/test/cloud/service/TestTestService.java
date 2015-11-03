package test.cloud.service;

import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.cloud.service.TestService;

public class TestTestService {
	private static SqlSessionFactory sqlSessionFactory;
	// private static Reader reader;

	static {
		try {
			ApplicationContext factory = new ClassPathXmlApplicationContext(
					"classpath:applicationContext.xml");
			TestService testService = (TestService) factory.getBean(TestService.class);
			System.out.println("----->" + testService);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		System.out.println("test...");
	}

}
