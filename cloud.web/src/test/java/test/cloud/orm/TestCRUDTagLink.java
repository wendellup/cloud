package test.cloud.orm;

import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.cloud.dao.ParameterTagLinkDao;
import com.cloud.valueobject.entity.ParameterTagLink;

public class TestCRUDTagLink {
	private static SqlSessionFactory sqlSessionFactory;
	private static Reader reader;

	static {
		try {
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
	
//	@Test
	public void testAddParameterTagLinkList(){
        SqlSession session = sqlSessionFactory.openSession();
        try {

        	ParameterTagLinkDao parameterTagLinkDao = session.getMapper(ParameterTagLinkDao.class);
        	List<ParameterTagLink> parameterTagLinks = new ArrayList<ParameterTagLink>();
        	for(int i=0; i<5; i++){
        		ParameterTagLink parameterTagLink = new ParameterTagLink();
        		parameterTagLink.setBusinessId(-1);
        		parameterTagLink.setBusinessSortNo(-1);
        		parameterTagLink.setEnable(false);
        		parameterTagLink.setEndTime(System.currentTimeMillis());
        		parameterTagLink.setStartTime(System.currentTimeMillis());
        		parameterTagLink.setTagId(i);
        		parameterTagLink.setTagSortNo(-1);
        		parameterTagLinks.add(parameterTagLink);
        	}
        	parameterTagLinkDao.addParameterTagLinkList(parameterTagLinks);
        } finally {
            session.close();
        }
	}
	
	@Test
	public void deleteParameterTagLinkByBusinessId(){
        SqlSession session = sqlSessionFactory.openSession();
        try {

        	ParameterTagLinkDao parameterTagLinkDao = session.getMapper(ParameterTagLinkDao.class);
        	parameterTagLinkDao.deleteParameterTagLinkByBusinessId(-1);
        	session.commit();
        } finally {
            session.close();
        }
	}
	
	@Test
	public void testHelloWorld(){
		System.out.println("helloWorld...");
	}
}
