package test.cloud.orm;

import java.io.Reader;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.cloud.dao.ParameterTagDao;
import com.cloud.dao.UserDao;
import com.cloud.valueobject.entity.ParameterTag;
import com.cloud.valueobject.entity.User;

public class TestCRUDUser {
	private static SqlSessionFactory sqlSessionFactory;
	private static Reader reader;

	static {
		try {
//			reader = Resources.getResourceAsReader("TestConfiguration.xml");
//			sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
			try {
				ApplicationContext factory = new ClassPathXmlApplicationContext(
						"classpath:applicationContext.xml");
//				ApplicationContext factory = new ClassPathXmlApplicationContext(
//						"classpath:TestConfiguration.xml");
//				ApplicationContext factory = new ClassPathXmlApplicationContext(
//						"classpath:spring-mybatis.xml");
				sqlSessionFactory = (SqlSessionFactory) factory.getBean(
						"sqlSessionFactory", SqlSessionFactory.class);
				System.out.println("----->" + sqlSessionFactory);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static SqlSessionFactory getSession() {
		return sqlSessionFactory;
	}
	
//	AppParameter appParameter = new AppParameter();
//	appParameter.setName("羊山公园");
//	appParameter.setRemark("栖园");
//	SqlSession session = sqlSessionFactory.openSession();
//	try {
//
//		AppParameterDao dao = session.getMapper(AppParameterDao.class);
//		int id = dao.addAppParameter(appParameter);
//		session.commit();
//		System.out.println("当前增加的用户 id为:" + id);
//		System.out.println("当前增加的用户 id为:" + appParameter.getId());
//	} finally {
//		session.close();
//	}
	
	@Test
	public void getUserByUserNameAndPwd(){
        SqlSession session = sqlSessionFactory.openSession();
        try {
        	User user = new User();
        	user.setUserName("admin");
        	user.setPassword("pwd2");
        	UserDao userDao = session.getMapper(UserDao.class);
        	User retUser = userDao.getUserByUserNameAndPwd(user);
			System.out.println(retUser);
        } finally {
            session.close();
        }
	}
	
	@Test
	public void testHelloWorld(){
		System.out.println("helloWorld...");
	}
}
