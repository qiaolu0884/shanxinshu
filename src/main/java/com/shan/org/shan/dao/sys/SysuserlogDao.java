package com.shan.org.shan.dao.sys;

import com.shan.org.shan.pojo.sys.Sysuserlog;
import com.shan.org.shan.pojo.sys.SysuserlogQuery;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SysuserlogDao {
    int countByExample(SysuserlogQuery example);

    int deleteByExample(SysuserlogQuery example);

    int deleteByPrimaryKey(Long id);

    void insert(Sysuserlog userlog);
    
    List<Sysuserlog> userlogcount();
    
    List<Sysuserlog> userlogpage();
    
    List<Sysuserlog> zuhequery(@Param("username") String username,@Param("name") String name,@Param("beginTime") Long beginTime,@Param("endTime") Long endTime,@Param("pagenumber") int pagenumber ,@Param("pageTiao") int pageTiao);

    /**
     * 添加操作日志方法
     * @param userlog
     */
    void insertSelective(Sysuserlog userlog);

    List<Sysuserlog> selectByExample(SysuserlogQuery example);

    Sysuserlog selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Sysuserlog record, @Param("example") SysuserlogQuery example);

    int updateByExample(@Param("record") Sysuserlog record, @Param("example") SysuserlogQuery example);

    int updateByPrimaryKeySelective(Sysuserlog record);

    int updateByPrimaryKey(Sysuserlog record);
}