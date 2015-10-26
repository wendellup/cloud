package test.cloud.orm;

import java.io.Reader;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import com.cloud.dao.AppParameterDao;
import com.cloud.dao.ArticleDao;
import com.cloud.valueobject.entity.AppParameter;
import com.cloud.valueobject.entity.Article;

public class TestCRUDArticle {
	private static SqlSessionFactory sqlSessionFactory;
	private static Reader reader;

	static {
		try {
			reader = Resources.getResourceAsReader("Configuration.xml");
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static SqlSessionFactory getSession() {
		return sqlSessionFactory;
	}

	@Test
	public void listArticle(){
        SqlSession session = sqlSessionFactory.openSession();
        try {
        	ArticleDao articleDao = session.getMapper(ArticleDao.class);
			List<Article> articleList = articleDao.listArticle(1);
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
