package com.shan.org.shan.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.shan.org.shan.pojo.sys.Sysgroup;
import com.shan.org.shan.pojo.sys.SysgroupQuery;

public interface SysgroupService {

	int countByExample(SysgroupQuery example);

    int deleteByExample(SysgroupQuery example);

    int deleteByPrimaryKey(Long groupId);
    
    List<Sysgroup> groupPage();
    
    List<Sysgroup> groupNumber();
    
    /**
     * 根据名字查询群组
     * @param groupName
     * @return
     */
    Sysgroup queryByName(String groupName);

    /**
     * ADD
     * @param group
     * @param sysp 
     */
    void insert(Sysgroup group, Long[] sysp);

    /**
     * 查询所有用户群组
     * @return
     */
    List<Sysgroup> groupList();

    List<Sysgroup> selectByExample(SysgroupQuery example);

    /**
     * 查询群组信息和权限
     * @param groupId
     * @return
     */
    Sysgroup selectByPrimaryKey(Long groupId);

    int updateByExampleSelective(@Param("record") Sysgroup record, @Param("example") SysgroupQuery example);

    int updateByExample(@Param("record") Sysgroup record, @Param("example") SysgroupQuery example);

    /**
     * 根据群组ID修改
     * @param group
     */
    void updateByPrimaryKeySelective(Sysgroup group);

    int updateByPrimaryKey(Sysgroup record);

	int insertSelective(Sysgroup record);

	void updateByPrimaryKeySelective(Sysgroup group, Long[] sysp);

}
