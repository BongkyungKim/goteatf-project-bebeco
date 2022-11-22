package com.goteatfproject.appgot.service;

import com.goteatfproject.appgot.dao.EventDao;
import com.goteatfproject.appgot.vo.AttachedFile;
import com.goteatfproject.appgot.vo.Criteria;
import com.goteatfproject.appgot.vo.Event;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DefaultEventService implements EventService {

  @Autowired
  EventDao eventDao;

  @Override
  @Transactional
  public void add(Event event) throws Exception {

    if (eventDao.insert(event) == 0) {
      throw new Exception("게시글 등록 실패!");
    }

    if (event.getAttachedFiles().size() > 0) {
      eventDao.insertFiles(event);
    }
  }

  @Override
  public List<Event> list() throws Exception {
    return eventDao.findAll();
  }

  //페이징
  @Override
  public List<Map<String, Object>> selectEventList(Criteria cri) {
    return eventDao.selectEventList(cri);
  }

  @Override
  public Event get(int no) throws Exception {
    return eventDao.findByNo(no);
  }

  @Transactional
  @Override
  public boolean update(Event event) throws Exception {

    if (eventDao.update(event) == 0) {
      return false;
    }

    if (event.getAttachedFiles().size() > 0) {
      eventDao.insertFiles(event);
    }
    return true;
  }

  @Transactional
  @Override
  public boolean delete(int no) throws Exception {
    eventDao.deleteFiles(no);
    return eventDao.delete(no) > 0;
  }

  @Override
  public AttachedFile getAttachedFile(int fileNo) throws Exception {
    return eventDao.findFileByNo(fileNo);
  }

  @Override
  public boolean deleteAttachedFile(int fileNo) throws Exception {
    return eventDao.deleteFile(fileNo) > 0;
  }

  // 마이페이지 본인 작성 글 리스트
  @Override
  public List<Map<String, Object>> selectEventList2(Map<String, Object> map) {
    return eventDao.selectEventList2(map);
  }

  // 관리자페이지 이벤트게시글 전체 출력
  @Override
  public List<Map<String, Object>> selectEventList3(Criteria criteria) {
    return eventDao.selectEventList3(criteria);
  }

  // 관리자페이지 이벤트게시글 비활성화
  @Override
  public boolean eventBlock(int no) {
    return eventDao.eventBlock(no) > 0;

  }

  //메인페이지에서 뽑는 파티리스트
  @Override
  public List<Event> mainList() throws Exception {
    return eventDao.findAllMain();
  }

  // 관리자페이지 이벤트게시글 상세보기
  // 사용안함
  @Override
  public Event getAdminEventListDetail(int no) {
    return eventDao.findByAdminEventListDetail(no);
  }

  //결제 수량
  public int getPayCnt(){
    return eventDao.payCnt();
  }
  //결제 여부
  public boolean ticketing(Map<String, Object> ticket){
    return eventDao.ticketing(ticket);
  }
}

