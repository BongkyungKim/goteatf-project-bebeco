package com.goteatfproject.appgot.dao;

import com.goteatfproject.appgot.vo.AttachedFile;
import com.goteatfproject.appgot.vo.Criteria;
import com.goteatfproject.appgot.vo.Event;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface EventDao {

  int insert(Event event);

  List<Event> findAll();

  // 페이징 처리하는 리스트
  List<Map<String, Object>> selectEventList(Criteria cri);

  Event findByNo(int no);

  int update(Event event);
  int delete(int no);
  int insertFiles(Event event);

  AttachedFile findFileByNo(int fileNo);

  List<AttachedFile> findFilesByEvent(int eventNo);

  int deleteFile(int fileNo);

  int deleteFiles(int eventNo);

  int deleteFilesByMemberEvents(int memberNo);

  // 마이페이지 본인 작성 글 리스트
  List<Map<String, Object>> selectEventList2(Map<String, Object> map);

  //관리자페이지 본인 작성 글 리스트
  List<Map<String, Object>> selectEventList3(Criteria cri);

  //관리자페이지 이벤트게시글 비활성화
  int eventBlock(int no);

  //메인페이지 파티게시물 조회
  List<Event> findAllMain();

// 결제 수량
  int payCnt();

  boolean ticketing(Map<String, Object> ticket);

  // 마이페이지 이벤트 게시글 본인 글 상세보기
  // 사용안함
  Event findByAdminEventListDetail(int no);
}