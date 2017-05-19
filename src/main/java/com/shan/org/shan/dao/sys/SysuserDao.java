package com.shan.org.shan.dao.sys;

import com.shan.org.shan.pojo.sys.Sysuser;
import com.shan.org.shan.pojo.sys.SysuserQuery;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository("sysuserDao")
public interface SysuserDao {
	
    int countByExample(SysuserQuery example);

    int deleteByExample(SysuserQuery example);

    int deleteByPrimaryKey(Long sysId);

    int insert(Sysuser record);
    
    void updateLastLoginTime(Long id);

    /**
     * 
     * @param user
     * add添加
     */
    void addSysuser(Sysuser user);

    List<Sysuser> selectByExample(SysuserQuery example);
    
    /**
     * 组合条件查询
     * @param user
     * @return
     */
    List<Sysuser> combinedQuery(@Param("username") String username,@Param("name") String name,@Param("status") Long status,@Param("beginTime") Long beginTime,@Param("endTime") Long endTime,@Param("group") Long group,@Param("pagenumber") int pagenumber ,@Param("pageTiao") int pageTiao);
    
    int queryCount();
    
    /**
     * 根据ID查询用户
     * @param sysId
     * @return
     */
    Sysuser selectByPrimaryKey(Long sysId);

    int updateByExampleSelective(@Param("record") Sysuser record, @Param("example") SysuserQuery example);

    int updateByExample(@Param("record") Sysuser record, @Param("example") SysuserQuery example);

    /**
     * update修改用户信息
     * @param user
     * @return
     */
    void updateByPrimaryKeySelective(Sysuser user);

    int updateByPrimaryKey(Sysuser user);
    
    /**
     * 获取后台用户集合
     */
    List<Sysuser> findSysuserList();
    
    List<Sysuser> querySysuserPage();
    
    /**
     * 根据用户名查询
     * @param username
     * @return
     */
    Sysuser findSysuserByName(String username);
    
    Sysuser findSysuserByNameAndGid(String username);
    
    /**
     * 根据群组ID查询用户
     * @param groupId
     * @return
     */
    List<Sysuser> findSysUsersByGroupId(Long groupId);

	Sysuser getLoginUser(SysuserQuery query);


}