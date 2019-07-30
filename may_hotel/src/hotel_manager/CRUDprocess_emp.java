package hotel_manager;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class CRUDprocess_emp {
	private SqlSession getSession() {
		String path = "hotel_manager/mybatis_config.xml";
		InputStream is = null;
		try {
			is = Resources.getResourceAsStream(path);
		}catch(Exception e) {
			System.out.println("환경설정 파일을 읽는 중 예외발생");
		}
		SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
		SqlSessionFactory factory = builder.build(is);
		SqlSession session = factory.openSession();
		return session;
	}
	
	public List<ReservationInfo> selectReservationInfo(){
		SqlSession s = getSession();
		List<ReservationInfo> info = null;
		try {
			info = s.selectList("hotelmapper.selectReservationInfo");
			return info;
		}finally {
			s.close();
		}
	}
	
	public Integer selectReservationInfo_resState_resOK(String date){
		SqlSession s = getSession();
		int countState = 0;
		try {
			countState = s.selectOne("hotelmapper.selectReservationInfo_resState_resOK",date);
			return countState;
		}finally {
			s.close();
		}
	}
	
	public Integer selectReservationInfo_resState_checkIn(String date) {
		SqlSession s = getSession();
		int countState = 0;
		try {
			countState = s.selectOne("hotelmapper.selectReservationInfo_resState_checkIn",date);
			return countState;
		}finally {
			s.close();
		}
	}
	
	public Integer selectReservationInfo_resState_checkOut(String date) {
		SqlSession s = getSession();
		int countState = 0;
		try {
			countState = s.selectOne("hotelmapper.selectReservationInfo_resState_checkOut",date);
			return countState;
		}finally{
			s.close();
		}
	}
	
	public Integer selectReservationInfo_resState_cancel(String date) {
		SqlSession s = getSession();
		int countState = 0;
		try {
			countState = s.selectOne("hotelmapper.selectReservationInfo_resState_cancel",date);
			return countState;
		}finally {
			s.close();
		}
	}
	
	public List<ReservationInfo> selectReservationInfoByCus(String res_id){
		SqlSession s = getSession();
		List<ReservationInfo> info = null;
		try {
			info = s.selectList("hotelmapper.selectReservationInfoByCus",res_id);
			return info;
		}finally {
			s.close();
		}
	}
	
	public Integer selectRoomPriceByRoomId(String room_id) {
		SqlSession s =getSession();
		int price = 0;
		try {
			price = s.selectOne("hotelmapper.selectRoomPriceByRoomId",room_id);
			return price;
		}finally {
			s.close();
		}
	}
	
	public Integer updateReservationInfoByresNum(ReservationInfo info) {
		SqlSession s = getSession();
		int result = 0;
		try {
			result = s.update("hotelmapper.updateReservationInfoByresNum",info);
			if(result > 0) {
				s.commit();
			}else {
				s.rollback();
			}
			return result;
		}finally {
			s.close();
		}
	}
	
	public String selectCusID(String id) {
		SqlSession s = getSession();
		String cus_id = null;
		try {
			cus_id = s.selectOne("hotelmapper.selectCusID",id);
			return cus_id;
		}finally {
			s.close();
		}
	}
	
	public Integer selectMaxResNum() {
		SqlSession s = getSession();
		int maxNum = 0;
		try {
			maxNum = s.selectOne("hotelmapper.selectMaxResNum");
			return maxNum;
		}finally {
			s.close();
		}
	}
	
	public Integer insertIntoReservationInfo(ReservationInfo info) {
		SqlSession s = getSession();
		int result = -1;
		try {
			result = s.insert("hotelmapper.insertIntoReservationInfo",info);
			if(result > 0) {
				s.commit();
			}else {
				s.rollback();
			}
			return result;
		}finally {
			s.close();
		}
	}
	
	public Integer updateReservationInfoResCancel(ReservationInfo info) {
		SqlSession s = getSession();
		int result = -1;
		try {
			result = s.update("hotelmapper.updateReservationInfoResCancel",info);
			if(result > 0) {
				s.commit();
			}else {
				s.rollback();
			}
			return result;
		}finally {
			s.close();
		}
	}
	
	public List<CustomerInfo> selectCustomerInfo(){
		SqlSession s = getSession();
		List<CustomerInfo> list = null;
		try {
			list = s.selectList("hotelmapper.selectCustomerInfo");
			return list;
		}finally {
			s.close();
		}
	}
	
	public List<CustomerInfo> selectCustomerInfoByCusID(String id){
		SqlSession s = getSession();
		List<CustomerInfo> list = null;
		try {
			list = s.selectList("hotelmapper.selectCustomerInfoByCusID",id);
			return list;
		}finally {
			s.close();
		}
	}
	
	public List<CustomerInfo> selectCustomerInfoByCusEmail(String email){
		SqlSession s = getSession();
		List<CustomerInfo> list = null;
		try {
			list = s.selectList("hotelmapper.selectCustomerInfoByCusEmail",email);
			return list;
		}finally {
			s.close();
		}
	}
	
	public Integer deleteCustomerInfoByCusId(String id) {
		SqlSession s = getSession();
		int result = -1;
		try {
			result = s.delete("hotelmapper.deleteCustomerInfoByCusId",id);
			if(result > 0) {
				s.commit();
			}else {
				s.rollback();
			}
			return result;
		}finally {
			s.close();
		}
	}
	
	public List<EventInfo> selectEventInfoByDate(String date){
		SqlSession s = getSession();
		List<EventInfo> list = null;
		try {
			list = s.selectList("hotelmapper.selectEventInfoByDate",date);
			return list;
		}finally {
			s.close();
		}
	}
	
	public Integer selectMaxEventNum() {
		SqlSession s = getSession();
		int num = 0;
		try {
			num = s.selectOne("hotelmapper.selectMaxEventNum");
			return num;
		}finally {
			s.close();
		}
	}
	
	public Integer insertIntoEventInfo(EventInfo info) {
		SqlSession s = getSession();
		int result = -1;
		try {
			result = s.insert("hotelmapper.insertIntoEventInfo",info);
			if(result > 0) {
				s.commit();
			}else {
				s.rollback();
			}
			return result;
		}finally {
			s.close();
		}
	}
	
	public Integer updateEventInfo(EventInfo info) {
		SqlSession s = getSession();
		int result = -1;
		try {
			result = s.update("hotelmapper.updateEventInfo",info);
			if(result > 0) {
				s.commit();
			}else {
				s.rollback();
			}
			return result;
		}finally {
			s.close();
		}
	}
	
	public Integer deleteEventInfoByEventId(Integer id) {
		SqlSession s = getSession();
		int result = -1;
		try {
			result = s.delete("hotelmapper.deleteEventInfoByEventId",id);
			if(result > 0) {
				s.commit();
			}else {
				s.rollback();
			}
			return result;
		}finally {
			s.close();
		}
	}
	
	public List<RoomInfo> selectRoomInfoByRoomType (String type){
		SqlSession s = getSession();
		List<RoomInfo> info = null;
		try {
			info = s.selectList("hotelmapper.selectRoomInfoByRoomType",type);
			return info;
		}finally {
			s.close();
		}
	}
	
	public RoomInfo selectRoomInfoByRoomId (RoomInfo2 info2){
		SqlSession s = getSession();
		RoomInfo info = null;
		try {
			info = s.selectOne("hotelmapper.selectRoomInfoByRoomId",info2);
			return info;
		}finally {
			s.close();
		}
	}
	
	public Integer insertIntoRoomInfo(RoomInfo info) {
		SqlSession s = getSession();
		int result = -1;
		try {
			result = s.insert("hotelmapper.insertIntoRoomInfo",info);
			if(result > 0) {
				s.commit();
			}else {
				s.rollback();
			}
			return result;
		}finally {
			s.close();
		}
	}
	
	public Integer updateRoomInfo(RoomInfo info) {
		SqlSession s = getSession();
		int result = -1;
		try {
			result = s.insert("hotelmapper.updateRoomInfo",info);
			if(result > 0) {
				s.commit();
			}else {
				s.rollback();
			}
			return result;
		}finally {
			s.close();
		}
	}
	
	public Integer deleteRoomInfoByRoomId(int room_id) {
		SqlSession s = getSession();
		int result = -1;
		try {
			result = s.delete("hotelmapper.deleteRoomInfoByRoomId",room_id);
			if(result > 0) {
				s.commit();
			}else {
				s.rollback();
			}
			return result;
		}finally {
			s.close();
		}
	}
	
	public List<EmployeeInfo> selectmanager_con(HashMap<String,Object> copy){
		SqlSession s = getSession();
		List<EmployeeInfo> list = null;
		try {
			list = s.selectList("hotelmapper.selectmanager_con",copy);
			return list;
		}finally {
			s.close();
		}
	}
	
	public List<EmployeeInfo> selectmanager(){
		SqlSession s = getSession();
		List<EmployeeInfo> list = null;
		try {
			list = s.selectList("hotelmapper.selectmanager");
			return list;
		}finally {
			s.close();
		}
	}
	
	public EmployeeInfo selectmanager_in(String id) {
		SqlSession s = getSession();
		try {
			EmployeeInfo info = s.selectOne("hotelmapper.selectmanager_in",id);
		return info;
		}finally {
			s.close();
		}
	}
	
	public int insertmanager(EmployeeInfo info) {
		SqlSession s = getSession();
		int result = 0;
		try {
			result = s.insert("hotelmapper.insertmanager",info);
			if(result > 0) s.commit();
			else s.rollback();
			return result;
		}finally {
			s.close();
		}
	}

	public int updatemanager(EmployeeInfo info) {
		SqlSession s = getSession();
		try {
			int i = s.update("hotelmapper.updatemanager",info);
			if(i > 0) s.commit();
			else s.rollback();
			return i;
		}finally {
			s.close();
		}
	}
	
	public int deletemanager(String id) {
		SqlSession s = getSession();
		int result = 0;
		try {
			result = s.delete("hotelmapper.deletemanager",id);
			if(result > 0) s.commit();
			else s.rollback();
			return result;
		}finally {
			s.close();
		}
	}
}
