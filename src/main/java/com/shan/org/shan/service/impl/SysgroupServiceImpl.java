package com.shan.org.shan.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shan.org.shan.dao.sys.GroupPrivilegeDao;
import com.shan.org.shan.dao.sys.SysgroupDao;
import com.shan.org.shan.pojo.sys.GroupPrivilege;
import com.shan.org.shan.pojo.sys.GroupPrivilegeQuery;
import com.shan.org.shan.pojo.sys.Sysgroup;
import com.shan.org.shan.pojo.sys.SysgroupQuery;
import com.shan.org.shan.service.SysgroupService;

import net.sf.ehcache.store.disk.ods.AATreeSet;

@Service
@Transactional
public class SysgroupServiceImpl implements SysgroupService{
	
	@Autowired
	private SysgroupDao sysgroupDao;
	
	@Autowired
	private GroupPrivilegeDao groupPrivilegeDao;

	@Override
	public int countByExample(SysgroupQuery example) {
		return 0;
	}

	@Override
	public int deleteByExample(SysgroupQuery example) {
		return 0;
	}

	@Override
	public int deleteByPrimaryKey(Long groupId) {
		return 0;
	}

	/**
	 * ADD
	 */
	@Override
	public void insert(Sysgroup group,Long[] sysp) {
		//直接获取返回的自增主键获取不到，先存再查再存
		sysgroupDao.insert(group);
		SysgroupQuery query = new SysgroupQuery();
		query.createCriteria().andGroupNameEqualTo(group.getGroupName());
		List<Sysgroup> list = sysgroupDao.selectByExample(query);
		if(sysp != null && sysp.length > 0){
			for (Long sid : sysp) {
				GroupPrivilege groupPrivilege = new GroupPrivilege();
				groupPrivilege.setSysgroupId(list.get(0).getGid());
				groupPrivilege.setSysprivilegeId(sid);
				groupPrivilegeDao.insert(groupPrivilege);
			}
		} 
	}

	@Override
	public int insertSelective(Sysgroup record) {
		return 0;
	}

	@Override
	public List<Sysgroup> selectByExample(SysgroupQuery example) {
		return null;
	}

	/**
	 * 根据群组ID查询群组信息
	 */
	@Override
	public Sysgroup selectByPrimaryKey(Long groupId) {
		return sysgroupDao.selectByPrimaryKey(groupId);
	}

	@Override
	public int updateByExampleSelective(Sysgroup record, SysgroupQuery example) {
		return 0;
	}

	@Override
	public int updateByExample(Sysgroup record, SysgroupQuery example) {
		return 0;
	}

	@Override
	public void updateByPrimaryKeySelective(Sysgroup group) {
		sysgroupDao.updateByPrimaryKeySelective(group);
	}

	@Override
	public int updateByPrimaryKey(Sysgroup record) {
		return 0;
	}

	/**
	 * 查询所有用户组
	 */
	@Override
	public List<Sysgroup> groupList() {
		return sysgroupDao.groupList();
	}

	@Override
	public List<Sysgroup> groupPage() {
		List<Sysgroup> groups = sysgroupDao.groupPage();
		for (Sysgroup sysgroup : groups) {
			List<GroupPrivilege> groupPrivilege = groupPrivilegeDao.selectByGid(sysgroup.getGid());
			List<String> groupPrivilegeIds = new ArrayList<>();
			for (GroupPrivilege g : groupPrivilege) {
				groupPrivilegeIds.add(String.valueOf(g.getSysprivilegeId()));
			}
			sysgroup.setGroupPrivilegeIds(groupPrivilegeIds);
		}
		return groups;
	}

	@Override
	public Sysgroup queryByName(String groupName) {
		return sysgroupDao.queryByName(groupName);
	}

	@Override
	public List<Sysgroup> groupNumber() {
		return sysgroupDao.groupNumber();
	}

	@Override
	public void updateByPrimaryKeySelective(Sysgroup group, Long[] sysp) {
		sysgroupDao.updateByPrimaryKeySelective(group);
		GroupPrivilegeQuery query = new GroupPrivilegeQuery();
		query.createCriteria().andSysgroupIdEqualTo(group.getGid());
		groupPrivilegeDao.deleteByExample(query);
		if(sysp != null && sysp.length>0){
			for (Long pid : sysp) {
				GroupPrivilege gp = new GroupPrivilege();
				gp.setSysgroupId(group.getGid());
				gp.setSysprivilegeId(pid);
				groupPrivilegeDao.insert(gp);
			}
		}
	}

}
