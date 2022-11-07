package com.goteatfproject.appgot.service;

import com.goteatfproject.appgot.dao.NoticeDao;
import com.goteatfproject.appgot.vo.Notice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DefaultNoticeService implements NoticeService {

  @Autowired
  NoticeDao noticeDao;

  @Override
  public List<Notice> list() throws Exception {
    return noticeDao.findAll();
  }

  @Override
  public Notice get(int no) throws Exception {
    return noticeDao.findByNo(no);
  }


}
