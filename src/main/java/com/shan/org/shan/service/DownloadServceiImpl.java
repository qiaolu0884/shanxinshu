package com.shan.org.shan.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shan.org.shan.dao.DownloadDao;
import com.shan.org.shan.pojo.Download;

@Service
public class DownloadServceiImpl implements DownloadService {

	@Autowired
	private DownloadDao downloadDao;
	@Override
	public int insert(Download record) {
		return downloadDao.insert(record);
	}
	@Override
	public List<Download> selcetByDataandStatus(String username,int pagenumber,int pageTiao) {
		return downloadDao.selcetByDataandStatus(username,pagenumber,pageTiao);
	}

	
}
