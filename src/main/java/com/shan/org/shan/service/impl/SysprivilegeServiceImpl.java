package com.shan.org.shan.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shan.org.shan.dao.sys.GroupPrivilegeDao;
import com.shan.org.shan.dao.sys.SysprivilegeDao;
import com.shan.org.shan.pojo.sys.GroupPrivilege;
import com.shan.org.shan.pojo.sys.Sysprivilege;
import com.shan.org.shan.pojo.sys.SysprivilegeQuery;
import com.shan.org.shan.service.SysprivilegeService;

@Service
@Transactional
public class SysprivilegeServiceImpl implements SysprivilegeService{

	@Autowired
	private SysprivilegeDao sysprivilegeDao;
	
	@Autowired
	private GroupPrivilegeDao groupPrivilegeDao;
	
	@Override
	public int countByExample(SysprivilegeQuery example) {
		return 0;
	}

	@Override
	public int deleteByExample(SysprivilegeQuery example) {
		return 0;
	}

	@Override
	public int deleteByPrimaryKey(Long id) {
		return 0;
	}

	@Override
	public int insert(Sysprivilege record) {
		return 0;
	}

	@Override
	public int insertSelective(Sysprivilege record) {
		return 0;
	}

	@Override
	public List<Sysprivilege> selectByExample(SysprivilegeQuery example) {
		return sysprivilegeDao.selectByExample(example);
	}

	@Override
	public Sysprivilege selectByPrimaryKey(Long id) {
		return null;
	}

	@Override
	public int updateByExampleSelective(Sysprivilege record, SysprivilegeQuery example) {
		return 0;
	}

	@Override
	public int updateByExample(Sysprivilege record, SysprivilegeQuery example) {
		return 0;
	}

	@Override
	public int updateByPrimaryKeySelective(Sysprivilege record) {
		return 0;
	}

	@Override
	public int updateByPrimaryKey(Sysprivilege record) {
		return 0;
	}

}
