package com.shan.org.shan.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shan.org.shan.dao.sys.SysuserlogDao;
import com.shan.org.shan.pojo.sys.Sysuser;
import com.shan.org.shan.pojo.sys.Sysuserlog;
import com.shan.org.shan.service.SysuserlogService;
import com.shan.org.shan.utils.BatchDateUtil;

@Service
@Transactional
public class SysuserlogServiceImpl implements SysuserlogService{

	@Autowired
	private SysuserlogDao sysuserlogDao;
	
	private BatchDateUtil batchDateUtil;
	
	/**
	 * 获得集合的大小
	 */
	@Override
	public List<Sysuserlog> userlogcount() {
		// TODO Auto-generated method stub
		return sysuserlogDao.userlogcount();
	}

	/**
	 * 首次进行分页
	 */
	@Override
	public List<Sysuserlog> userlogpage() {
		// TODO Auto-generated method stub
		return sysuserlogDao.userlogpage();
	}

	@Override
	public List<Sysuserlog> zuhequery(String username, String name, Long beginTime, Long endTime, int pagenumber,
			int pageTiao) {
		// TODO Auto-generated method stub
		return sysuserlogDao.zuhequery(username, name, beginTime, endTime, pagenumber, pageTiao);
	}

	@Override
	public void insertSelective(Sysuserlog userlog) {
		// TODO Auto-generated method stub
		//转为Unix时间戳
		userlog.setCreateTime(BatchDateUtil.getBatch(new Date()));
		sysuserlogDao.insertSelective(userlog);
	}

}
