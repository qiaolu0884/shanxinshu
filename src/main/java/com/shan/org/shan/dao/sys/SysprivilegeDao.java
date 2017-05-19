package com.shan.org.shan.dao.sys;

import com.shan.org.shan.pojo.sys.Sysprivilege;
import com.shan.org.shan.pojo.sys.SysprivilegeQuery;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SysprivilegeDao {
    int countByExample(SysprivilegeQuery example);

    int deleteByExample(SysprivilegeQuery example);

    int deleteByPrimaryKey(Long id);

    int insert(Sysprivilege record);

    int insertSelective(Sysprivilege record);

    List<Sysprivilege> selectByExample(SysprivilegeQuery example);

    Sysprivilege selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Sysprivilege record, @Param("example") SysprivilegeQuery example);

    int updateByExample(@Param("record") Sysprivilege record, @Param("example") SysprivilegeQuery example);

    int updateByPrimaryKeySelective(Sysprivilege record);

    int updateByPrimaryKey(Sysprivilege record);

	void insertGPS(Long sysId);
}