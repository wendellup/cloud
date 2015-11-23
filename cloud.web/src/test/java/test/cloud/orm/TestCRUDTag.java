package test.cloud.orm;

import java.io.Reader;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.cloud.dao.AppParameterDao;
import com.cloud.dao.ArticleDao;
import com.cloud.valueobject.entity.AppParameter;
import com.cloud.valueobject.entity.Article;

public class TestCRUDTag {
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
	public void testAddArticle(){
		Article article = new Article();
    	AppParameter appParameter = new AppParameter();
    	appParameter.setId(803);
    	article.setTitle("test");
    	article.setTitle("test3");
    	article.setContent("这是一段内容");
    	article.setAppParameter(appParameter);
        SqlSession session = sqlSessionFactory.openSession();
        try {
        	ArticleDao articleDao = session.getMapper(ArticleDao.class);
        	
        	int id = articleDao.addArticle(article);
			session.commit();
			System.out.println("当前增加的用户 id为:" + id);
			System.out.println("当前增加的用户 id为:" + article.getId());
        } finally {
            session.close();
        }
	}

	@Test
	public void listArticle(){
        SqlSession session = sqlSessionFactory.openSession();
        try {
        	ArticleDao articleDao = session.getMapper(ArticleDao.class);
			List<Article> articleList = articleDao.listArticle();
			System.out.println(articleList.size());
			System.out.println("###########");
			for(Article article : articleList){
				System.out.println(article.getId());
				System.out.println(article.getTitle());
				System.out.println(article.getContent());
//				System.out.println(article.getAppParameter().getName());
				System.out.println("---------------");
			}
//			session.commit();
        } finally {
            session.close();
        }
	}
	
	@Test
	public void listArticlesByAppParameterId(){
        SqlSession session = sqlSessionFactory.openSession();
        try {
        	ArticleDao articleDao = session.getMapper(ArticleDao.class);
			List<Article> articleList = articleDao.listArticleByAppParameterId(1);
			System.out.println(articleList.size());
			System.out.println("###########");
			for(Article article : articleList){
				System.out.println(article.getId());
				System.out.println(article.getTitle());
				System.out.println(article.getContent());
				System.out.println(article.getAppParameter().getName());
				System.out.println("---------------");
			}
//			session.commit();
        } finally {
            session.close();
        }
	}
}
