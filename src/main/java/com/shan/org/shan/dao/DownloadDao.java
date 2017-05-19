package com.shan.org.shan.dao;

import com.shan.org.shan.pojo.Download;
import com.shan.org.shan.pojo.DownloadQuery;
import com.shan.org.shan.pojo.Order;
import com.shan.org.shan.pojo.OrderT;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.junit.runners.Parameterized.Parameters;

public interface DownloadDao {
    int countByExample(DownloadQuery example);

    int deleteByExample(DownloadQuery example);

    int deleteByPrimaryKey(Long id);

    int insert(Download dl);

    int insertSelective(Download record);

    List<Download> selectByExample(DownloadQuery example);

    Download selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Download record, @Param("example") DownloadQuery example);

    int updateByExample(@Param("record") Download record, @Param("example") DownloadQuery example);

    int updateByPrimaryKeySelective(Download record);

    int updateByPrimaryKey(Download record);
    
    List<Download> selcetByDataandStatus(@Param("username") String username,@Param("pagenumber") int pagenumber,@Param("pageTiao") int pageTiao);
    
    List<Download> queryDownload(int pagenumber,int pageTiao);
}