package test.cloud.orm;

import java.io.Reader;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import com.cloud.dao.AppParameterDao;
import com.cloud.valueobject.entity.AppParameter;

public class TestCRUD {
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

	public static void main(String[] args) {
		SqlSession session = sqlSessionFactory.openSession();
		try {
//			AppParameter appParameter = (AppParameter) session.selectOne(
//					"com.cloud.dao.AppParameterDaoMapper.getAppParameterById", 1);
			
			// get
			AppParameterDao appParameterDao = session.getMapper(AppParameterDao.class);
//			AppParameter appParameter = appParameterDao.getAppParameterById(1);
//			System.out.println(appParameter.getName());
//			System.out.println(appParameter.getRemark());
			
			// list
			List<AppParameter> appParameterList = appParameterDao.listAppParameterByParentId(1);
			for(AppParameter app : appParameterList){
				System.out.println(app.getName());
				System.out.println(app.getRemark());
				System.out.println("------");
			}
			
			//add
			
		} finally {
			session.close();
		}
	}
	
	@Test
	public void addAppParameter(){
		AppParameter appParameter = new AppParameter();
		appParameter.setName("羊山公园");
		appParameter.setRemark("栖园");
        SqlSession session = sqlSessionFactory.openSession();
        try {
        	
        	AppParameterDao dao = session.getMapper(AppParameterDao.class);
        	int id = dao.addAppParameter(appParameter);
            session.commit();
            System.out.println("当前增加的用户 id为:"+id);
            System.out.println("当前增加的用户 id为:"+appParameter.getId());
        } finally {
            session.close();
        }
	}
	
	@Test
	public void updateAppParameter(){
        SqlSession session = sqlSessionFactory.openSession();
        try {
        	
        	AppParameterDao appParameterDao = session.getMapper(AppParameterDao.class);
			AppParameter appParameter = appParameterDao.getAppParameterById(2);
			System.out.println(appParameter.getName());
			System.out.println(appParameter.getRemark());
			appParameter.setName("update"+appParameter.getName());
			appParameterDao.updateAppParameter(appParameter);
			session.commit();
        } finally {
            session.close();
        }
	}
	
	@Test
	public void deleteAppParameter(){
        SqlSession session = sqlSessionFactory.openSession();
        try {
        	
        	AppParameterDao appParameterDao = session.getMapper(AppParameterDao.class);
			AppParameter appParameter = appParameterDao.getAppParameterById(2);
			System.out.println(appParameter.getName());
			System.out.println(appParameter.getRemark());
			appParameterDao.deleteAppParameter(appParameter);
			session.commit();
        } finally {
            session.close();
        }
	}
}
