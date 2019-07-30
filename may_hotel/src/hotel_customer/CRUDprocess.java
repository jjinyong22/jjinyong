package hotel_customer;

import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class CRUDprocess {

	private SqlSession getSession() {
		String path = "hotel_customer/hotelbatis.xml";
		InputStream i = null;
		try {
			i = Resources.getResourceAsStream(path);
		}catch(Exception e) {
			System.out.println("경로가 다릅니다.");
		}
		SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
		SqlSessionFactory factory = builder.build(i);
		SqlSession session = factory.openSession();
		return session;
	}
	
	public int insertCustomerInfo(customer_info info) {
		SqlSession s = getSession();
		int result = 0;
		try {
			result = s.insert("hotelmapper.insertCustomerInfo",info);
			if(result > 0) s.commit();
			else s.rollback();
			return result;
		}finally {
			s.close();
		}
	}

	public customer_info selectCustomerInfo(String id) {
		SqlSession s = getSession();
		try {
			customer_info info = s.selectOne("hotelmapper.selectCustomerInfo",id);
			return info;
		}finally {
			s.close();
		}
	}
	public List<event_info> selectEvent() {
		SqlSession s = getSession();
		try {
			List<event_info> list = s.selectList("hotelmapper.selectEventInfo");
			return list;
		}finally {
			s.close();
		}
	}

	public int updateCus_info(customer_info info) {
		SqlSession s = getSession();
		try {
			int i = s.update("hotelmapper.updateCustomerInfo",info);
			if(i > 0) s.commit();
			else s.rollback();
			return i;
		}finally {
			s.close();
		}
	}

	public int insertyoya(Yoya_batis yoya) {
		SqlSession s = getSession();
		int result = 0;
		try {
			result = s.insert("hotelmapper.insertyoya",yoya);
			if(result > 0) s.commit();
			else s.rollback();
			return result;
		}finally {
			s.close();
		}
	}

	public int max_yoya() {
		SqlSession s = getSession();
		//Yoya_batis yoya = null;
		int result = 0;
		try {
			result = s.selectOne("hotelmapper.max_yoya");
			return result;
		}finally {
			s.close();
		}
	}
	
	public List<Yoya_batis> selectyoya(){
		SqlSession s = getSession();
		List<Yoya_batis> list = null;
		try {
			list = s.selectList("hotelmapper.selectyoya");
			return list;
		}finally {
			s.close();
		}
	}
	
	public int updateyoya(Yoya_batis yoya) {
		SqlSession s = getSession();
		int result = 0;
		try {
			result = s.update("hotelmapper.updateyoya",yoya);
			if(result > 0) s.commit();
			else s.rollback();
			return result;
		}finally {
			s.close();
		}
			
	}
	
	public ManagerInfo selectManIdAndPw(ManagerInfo info) {
		SqlSession s = getSession();
		ManagerInfo result_info = null;
		try {
			result_info = s.selectOne("hotelmapper.selectManIdAndPw",info);
			return result_info;
		}finally {
			s.close();
		}
	}
}
