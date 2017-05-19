package com.shan.org.shan.service;

import java.util.List;

import com.shan.org.shan.pojo.TreeAge;
import com.shan.org.shan.pojo.User;

public interface UserService {

	public void insertUser(User user);

	public List<User> findByQuery(String username,String userGrade,String taBegin,String taEnd,Integer pageNo);

	public Integer totalPage(String username, String userGrade, String taBegin, String taEnd, Integer pageNo);

	public List<Object> findGradesAll();

	public List<TreeAge> findAgesAll();

	public List<User> importUser(String username, String userGrade, String taBegin, String taEnd);
	
}
