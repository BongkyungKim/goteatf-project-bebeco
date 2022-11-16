package com.goteatfproject.appgot.service;

import com.goteatfproject.appgot.vo.AttachedFile;
import com.goteatfproject.appgot.vo.Comment;
import com.goteatfproject.appgot.vo.Criteria;
import com.goteatfproject.appgot.vo.Party;
import java.util.List;
import java.util.Map;

public interface PartyService {

  void add(Party party) throws Exception;
  List<Party> list() throws Exception;

  // 파티게시판 - 카테고리
  List<Party> list2(String meal, String food) throws Exception;

  //페이징
  List<Map<String, Object>> selectPartyList(Criteria criteria);
  Party get(int no) throws Exception;
  boolean update(Party party) throws Exception;
  boolean delete(int no) throws Exception;
  AttachedFile getAttachedFile(int fileNo) throws Exception;
  boolean deleteAttachedFile(int fileNo) throws Exception;

  // 댓글 등록
  public void insertComment(Comment comment) throws Exception;

  // 댓글 리스트 출력
  public List<Comment> getCommentList(Comment comment) throws Exception;

  // 댓글 수정
  boolean updateComment(Comment comment) throws Exception;

  // 댓글 삭제
  boolean deleteComment(int no) throws Exception;
}
