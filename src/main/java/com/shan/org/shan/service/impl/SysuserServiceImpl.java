package com.shan.org.shan.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.codec.net.QCodec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shan.org.shan.dao.sys.SysuserDao;
import com.shan.org.shan.pojo.sys.Sysuser;
import com.shan.org.shan.pojo.sys.SysuserQuery;
import com.shan.org.shan.service.SysuserService;
import com.shan.org.shan.utils.BatchDateUtil;
import com.shan.org.shan.utils.MD5Util;

@Service
@Transactional
public class SysuserServiceImpl implements SysuserService{
	
	@Autowired
	private SysuserDao sysuserDao;

	private BatchDateUtil batchDateUtil;
	
	private MD5Util md5Util;

	@Override
	public int countByExample(SysuserQuery example) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteByExample(SysuserQuery example) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteByPrimaryKey(Long sysId) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insert(Sysuser record) {
		// TODO Auto-generated method stub
		return 0;
	}

	/**
	 * 分页查询所有用户
	 */
	@Override
	public List<Sysuser> selectByExample(SysuserQuery example) {
		// TODO Auto-generated method stub
		return sysuserDao.selectByExample(example);
	}

	@Override
	public Sysuser selectByPrimaryKey(Long sysId) {
		// TODO Auto-generated method stub
		return sysuserDao.selectByPrimaryKey(sysId);
	}

	@Override
	public int updateByExampleSelective(Sysuser record, SysuserQuery example) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateByExample(Sysuser record, SysuserQuery example) {
		// TODO Auto-generated method stub
		return 0;
	}

	/**
	 * update
	 * @return 
	 */
	@Override
	public void updateByPrimaryKeySelective(Sysuser user) {
		// TODO Auto-generated method stub
		if(user.getPassword()!=null){
			String password=MD5Util.encode(user.getPassword());
			user.setPassword(password);
		}
		//获取当前修改时间
		user.setUpdateTime(BatchDateUtil.getBatch(new Date()));
		sysuserDao.updateByPrimaryKeySelective(user);
	}

	@Override
	public int updateByPrimaryKey(Sysuser record) {
		// TODO Auto-generated method stub
		return 0;
	}

	/**
	 * 查询所有后台用户
	 */
	@Override
	public List<Sysuser> findSysuserList() {
		return sysuserDao.findSysuserList();
	}

	/**
	 * ADD
	 */
	@Override
	public void addSysuser(Sysuser user) {
		// TODO Auto-generated method stub
		//MD5密码加密
		user.setPassword(MD5Util.encode(MD5Util.INIT_PWD));
		user.setCreateTime(BatchDateUtil.getBatch(new Date()));
		//user.setUpdateTime(BatchDateUtil.getBatch(new Date()));
		sysuserDao.addSysuser(user);
	}

	/**
	 * 根据用户名查询
	 */
	@Override
	public Sysuser findSysuserByName(String username) {
		// TODO Auto-generated method stub
		return sysuserDao.findSysuserByName(username);
	}

	/**
	 * 组合查询
	 */
	/*@Override
	public List<Sysuser> combinedQuery(String username, Long status, Long beginTime, Long endTime) {
		// TODO Auto-generated method stub
		if(beginTime!=null || endTime!=null){
			//beginTime=BatchDateUtil.getBatch(beginTime);
		}
		//return sysuserDao.combinedQuery(username, status, beginTime, endTime);
	}*/

	/**
	 * 组合搜索
	 */
	@Override
	public List<Sysuser> combinedQuery(String username, String name,Long status, Long beginTime, Long endTime,Long group,int pagenumber , int pageTiao) {
		// TODO Auto-generated method stub
		return sysuserDao.combinedQuery(username,name, status, beginTime, endTime,group,pagenumber,pageTiao);
	}

	/**
	 * 根据群组id查询
	 */
	@Override
	public List<Sysuser> findSysUsersByGroupId(Long groupId) {
		// TODO Auto-generated method stub
		return sysuserDao.findSysUsersByGroupId(groupId);
	}

	@Override
	public List<Sysuser> querySysuserPage() {
		// TODO Auto-generated method stub
		
		return sysuserDao.querySysuserPage();
	}

	@Override
	public Sysuser getLoginUser(String username, String password) {
		SysuserQuery query = new SysuserQuery();
		query.createCriteria().andUsernameEqualTo(username).andPasswordEqualTo(password);
		return sysuserDao.getLoginUser(query);
	}

}
