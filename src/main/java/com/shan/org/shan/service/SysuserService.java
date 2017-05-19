package com.shan.org.shan.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.shan.org.shan.pojo.sys.Sysuser;
import com.shan.org.shan.pojo.sys.SysuserQuery;

public interface SysuserService {

	int countByExample(SysuserQuery example);

    int deleteByExample(SysuserQuery example);

    int deleteByPrimaryKey(Long sysId);

    int insert(Sysuser record);

    void addSysuser(Sysuser user);

    List<Sysuser> selectByExample(SysuserQuery example);
    
    List<Sysuser> querySysuserPage();

    Sysuser selectByPrimaryKey(Long sysId);
    
    /**
     * 组合查询
     * @param username
     * @param status
     * @param beginTime
     * @param endTime
     * @return
     */
    List<Sysuser> combinedQuery(String username,String name,Long status,Long beginTime,Long endTime,Long group,int pagenumber , int pageTiao);

    int updateByExampleSelective(@Param("record") Sysuser record, @Param("example") SysuserQuery example);

    int updateByExample(@Param("record") Sysuser record, @Param("example") SysuserQuery example);

    void updateByPrimaryKeySelective(Sysuser user);

    int updateByPrimaryKey(Sysuser record);
    
    List<Sysuser> findSysuserList();
    
    /**
     * 根据用户名查询
     * @param username
     * @return
     */
    Sysuser findSysuserByName(String username);
    
    /**
     * 根据群组ID查询用户
     * @param groupId
     * @return
     */
    List<Sysuser> findSysUsersByGroupId(Long groupId);

	Sysuser getLoginUser(String username, String encode);
}
