package com.goteatfproject.appgot.service;

import com.goteatfproject.appgot.dao.NoticeDao;
import com.goteatfproject.appgot.vo.Criteria;
import com.goteatfproject.appgot.vo.Notice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
public class DefaultNoticeService implements NoticeService {

  @Autowired
  NoticeDao noticeDao;
  @Transactional
  @Override
  public void add(Notice notice) throws Exception {
    // 1) 게시글 등록
    if (noticeDao.insert(notice) == 0) {
      throw new Exception("게시글 등록 실패!");
    }
  }

  @Transactional
  @Override
  public boolean update(Notice notice) throws Exception {
    // 1) 게시글 변경
    if (noticeDao.update(notice) == 0) {
    }
    return false;
  }
  @Override
  public Notice get(int no) throws Exception {
    return noticeDao.findByNo(no); // 첨부파일 데이터까지 조인하여 select를 한 번만 실행한다.
  }

  @Transactional
  @Override
  public boolean delete(int no) throws Exception {

    return noticeDao.delete(no) > 0;
  }

  //페이징
  @Override
  public List<Map<String, Object>> selectNoticeList(Criteria cri) {
    return noticeDao.selectNoticeList(cri);
  }
}