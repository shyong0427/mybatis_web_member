package pack.business;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import pack.mybatis.SqlMapConfig;

public class ProcessDao {
	private SqlSessionFactory factory = SqlMapConfig.getSqlSession();
	
	public List<DataDto> selectDataAll() {
		SqlSession sqlSession = factory.openSession();
		List<DataDto> list = null;
		try {
			list = sqlSession.selectList("selectDataAll");
		} catch (Exception e) {
			System.out.println("selectDataAll err : " + e);
		} finally {
			try {
				if(sqlSession != null) sqlSession.close();
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
		
		return list;
	}
	
	public DataDto selectDataPart(String id) {
		SqlSession sqlSession = factory.openSession();
		DataDto dto = null;
		try {
			dto = sqlSession.selectOne("selectDataPart", id);
		} catch (Exception e) {
			System.out.println("selectDataPart err : " + e);
		} finally {
			try {
				if(sqlSession != null) sqlSession.close();
			} catch (Exception e2) {
			}
		}
		
		return dto;
	}
	
	public boolean insertData(DataFormBean bean) {
		boolean isSuccess = false;
		SqlSession sqlSession = factory.openSession();
		try {
			if(sqlSession.insert("insertData", bean) > 0) isSuccess = true;
			sqlSession.commit();
		} catch (Exception e) {
			System.out.println("insertData err : " + e);
			sqlSession.rollback();
		} finally {
			try {
				if(sqlSession != null) sqlSession.close();
			} catch (Exception e2) {
			}
		}
		
		return isSuccess;
	}
	
	public boolean updateData(DataFormBean bean) {
		boolean isSuccess = false;
		SqlSession sqlSession = factory.openSession();
		try {
			// 비밀번호 비교 후 수정 여부 판단
			DataDto dto = selectDataPart(bean.getId());
			if(dto.getPasswd().equalsIgnoreCase(bean.getPasswd())) {
				if(sqlSession.update("updateData", bean) > 0) isSuccess = true;
				
				sqlSession.commit();
			}
		} catch (Exception e) {
			System.out.println("updateData err : " + e);
			sqlSession.rollback();
		} finally {
			try {
				if(sqlSession != null) sqlSession.close();
			} catch (Exception e2) {
			}
		}
		
		return isSuccess;
	}
	
	public boolean deleteData(String id) {
		boolean isSuccess = false;
		SqlSession sqlSession = factory.openSession();
		try {
			int cou = sqlSession.delete("deleteData", id);
			
			if(cou > 0) isSuccess = true;
			sqlSession.commit();
		} catch (Exception e) {
			System.out.println("deleteData err : " + e);
			sqlSession.rollback();
		} finally {
			try {
				if(sqlSession != null) sqlSession.close();
			} catch (Exception e2) {
			}
		}
		
		return isSuccess;
	}
}
