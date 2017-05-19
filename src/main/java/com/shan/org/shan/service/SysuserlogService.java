package com.shan.org.shan.service;

import java.util.List;

import com.shan.org.shan.pojo.sys.Sysuser;
import com.shan.org.shan.pojo.sys.Sysuserlog;

public interface SysuserlogService {

    /**
     * 添加操作日志方法
     * @param userlog
     */
    void insertSelective(Sysuserlog userlog);
    
    List<Sysuserlog> userlogcount();
    
    List<Sysuserlog> userlogpage();
    
    List<Sysuserlog> zuhequery(String username,String name,Long beginTime,Long endTime,int pagenumber , int pageTiao);
}
