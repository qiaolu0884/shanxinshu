package com.shan.org.shan.service;

import java.util.List;

import com.shan.org.shan.pojo.Download;

public interface DownloadService {

	int insert(Download record);
	
	List<Download> selcetByDataandStatus(String username,int pagenumber,int pageTiao);
}
