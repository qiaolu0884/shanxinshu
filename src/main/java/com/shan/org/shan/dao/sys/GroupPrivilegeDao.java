package com.shan.org.shan.dao.sys;

import com.shan.org.shan.pojo.sys.GroupPrivilege;
import com.shan.org.shan.pojo.sys.GroupPrivilegeQuery;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface GroupPrivilegeDao {
    int countByExample(GroupPrivilegeQuery example);

    int deleteByExample(GroupPrivilegeQuery example);

    int insert(GroupPrivilege record);

    int insertSelective(GroupPrivilege record);

    List<GroupPrivilege> selectByExample(GroupPrivilegeQuery example);

    int updateByExampleSelective(@Param("record") GroupPrivilege record, @Param("example") GroupPrivilegeQuery example);

    int updateByExample(@Param("record") GroupPrivilege record, @Param("example") GroupPrivilegeQuery example);

    List<GroupPrivilege> selectByGid(Long gid);

}