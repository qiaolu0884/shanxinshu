package com.shan.org.shan.dao.sys;

import com.shan.org.shan.pojo.sys.SysuserGroup;
import com.shan.org.shan.pojo.sys.SysuserGroupQuery;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SysuserGroupDao {
    int countByExample(SysuserGroupQuery example);

    int deleteByExample(SysuserGroupQuery example);

    int insert(SysuserGroup record);

    int insertSelective(SysuserGroup record);

    List<SysuserGroup> selectByExample(SysuserGroupQuery example);

    int updateByExampleSelective(@Param("record") SysuserGroup record, @Param("example") SysuserGroupQuery example);

    int updateByExample(@Param("record") SysuserGroup record, @Param("example") SysuserGroupQuery example);
    
}