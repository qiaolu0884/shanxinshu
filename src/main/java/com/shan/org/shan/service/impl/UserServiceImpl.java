package com.shan.org.shan.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shan.org.shan.dao.UserDao;
import com.shan.org.shan.dao.sys.SysgroupDao;
import com.shan.org.shan.dao.sys.SysuserDao;
import com.shan.org.shan.pojo.TreeAge;
import com.shan.org.shan.pojo.User;
import com.shan.org.shan.pojo.UserQuery;
import com.shan.org.shan.pojo.UserQuery.Criteria;
import com.shan.org.shan.service.UserService;

@Service
@Transactional
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserDao userDao;
	@Autowired
	private SysuserDao sysuserDao;
	@Autowired
	private SysgroupDao sysgroupDao;
	
	public List<User> selectUsersByUsername(String username){
		UserQuery query = new UserQuery();
		Criteria criteria = query.createCriteria();
		criteria.andUsernameEqualTo(username);
		return userDao.selectByExample(query);
	}
	
	/**
	 * 添加新用户
	 */
	public void insertUser(User user){
		userDao.insert(user);
	}

	@Override
	public List<User> findByQuery(String username,String userGrade,String taBegin,String taEnd,Integer pageNo) {
		UserQuery query = new UserQuery();
		if(username != null){
			query.setUsername(username);
		}
		if(userGrade != null && !"undefined".equals(userGrade) && !"全部".equals(userGrade)){
			query.setUserGrade(userGrade);
		}
		if(taBegin != null){
			if(Integer.valueOf(taBegin)!=0){
				query.setBeginAge(Long.valueOf(taBegin));
			}
		}
		if(taEnd != null ){
			if(Integer.valueOf(taEnd)!=0){
				query.setEndAge(Long.valueOf(taEnd));
			}
		}
		if(pageNo != null){
			query.setPageNo(pageNo);
		}
		return userDao.selectByUserQuery(query);
	}
	@Override
	public Integer totalPage(String username, String userGrade, String taBegin, String taEnd, Integer pageNo) {
		UserQuery query = new UserQuery();
		if(username != null){
			query.setUsername(username);
		}
		if(userGrade != null && !"undefined".equals(userGrade) && !"全部".equals(userGrade)){
			query.setUserGrade(userGrade);
		}
		if(taBegin != null){
			if(Integer.valueOf(taBegin)!=0){
				query.setBeginAge(Long.valueOf(taBegin));
			}
		}
		if(taEnd != null ){
			if(Integer.valueOf(taEnd)!=0){
				query.setEndAge(Long.valueOf(taEnd));
			}
		}
		if(pageNo != null){
			query.setPageNo(pageNo);
		}
		 int totalCount = userDao.countByUserQuery(query);
		 int totalPage = 1;
		 if(totalCount > query.getPageSize() ){
			 if(totalCount%query.getPageSize() == 0){
				 totalPage = totalCount/query.getPageSize();
			 } else {
				 totalPage = (totalCount/query.getPageSize())+1;
			 }
		 }
		 return totalPage;
	}

	@Override
	public List<Object> findGradesAll() {
		//写死的用户分组，后期可以考虑放入数据库
		List<Object> grades = new ArrayList<>();
		grades.add("A轮服务中心");
		grades.add("B轮服务中心");
		grades.add("C轮服务中心");
		grades.add("A轮功德主");
		grades.add("B轮功德主");
		grades.add("C轮功德主");
		return grades;
	}

	@Override
	public List<TreeAge> findAgesAll() {
		//写死的善心树龄，后期可以考虑放入数据库
		List<TreeAge> ages = new ArrayList<>();
		ages.add(new TreeAge("1","0个月"));
		ages.add(new TreeAge("2","一个月"));
		ages.add(new TreeAge("3","半年"));
		ages.add(new TreeAge("4","一年"));
		ages.add(new TreeAge("5","两年"));
		ages.add(new TreeAge("6","三年"));
		ages.add(new TreeAge("7","四年"));
		ages.add(new TreeAge("8","五年"));
		ages.add(new TreeAge("9","六年"));
		ages.add(new TreeAge("10","七年"));
		ages.add(new TreeAge("11","八年"));
		ages.add(new TreeAge("12","九年"));
		ages.add(new TreeAge("13","十年"));
		return ages;
	}

	@Override
	public List<User> importUser(String username, String userGrade, String taBegin, String taEnd) {
		UserQuery query = new UserQuery();
		if(username != null){
			query.setUsername(username);
		}
		if(userGrade != null && !"undefined".equals(userGrade) && !"全部".equals(userGrade)){
			query.setUserGrade(userGrade);
		}
		if(taBegin != null){
			if(Integer.valueOf(taBegin)!=0){
				query.setBeginAge(Long.valueOf(taBegin));
			}
		}
		if(taEnd != null ){
			if(Integer.valueOf(taEnd)!=0){
				query.setEndAge(Long.valueOf(taEnd));
			}
		}
		return userDao.importUser(query);
	}
	
/*	@Test
	public void testName() throws Exception {
		Sysuser sysuser = new Sysuser();
		sysuser.setCreater("e");
		sysuser.setCreateTime(1l);
		sysuser.setLastloginTime(1l);
		sysuser.setPassword("s");
		sysuser.setPhone(8l);
		sysuser.setRemarks("r");
		sysuser.setStatus(0l);
		sysuser.setName("a");
		sysuser.setUsername("a");
		sysuserDao.insert(sysuser);
		
	}*/

}
