package com.shan.org.shan.dao.sys;

import com.shan.org.shan.pojo.sys.Sysgroup;
import com.shan.org.shan.pojo.sys.SysgroupQuery;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SysgroupDao {
    int countByExample(SysgroupQuery example);

    int deleteByExample(SysgroupQuery example);

    int deleteByPrimaryKey(Long groupId);
    
    /**
     * ADD
     * @param group
     */
    Long insert(Sysgroup group);

    int insertSelective(Sysgroup record);

    List<Sysgroup> selectByExample(SysgroupQuery example);
    
    /**
     * 查询所有用户群组
     * @return
     */
    List<Sysgroup> groupList();
    
    List<Sysgroup> groupPage();
    
    List<Sysgroup> groupNumber();

    /**
     * 根据群组ID查询该群组信息
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
    /**
     * 根据名字查询群组
     * @param groupName
     * @return
     */
    Sysgroup queryByName(String groupName);
}