package com.shan.org.shan.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.shan.org.shan.pojo.sys.Sysprivilege;
import com.shan.org.shan.pojo.sys.SysprivilegeQuery;

public interface SysprivilegeService {

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

}
