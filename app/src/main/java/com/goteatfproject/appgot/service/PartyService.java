package com.goteatfproject.appgot.service;

import com.goteatfproject.appgot.vo.AttachedFile;
import com.goteatfproject.appgot.vo.Board;
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
  Party get2(int no) throws Exception;

  // 마이페이지 파티게시글 수정
  boolean update(Party party) throws Exception;

  // 파티게시판 파티게시글 수정
  boolean update2(Party party) throws Exception;
  boolean delete(int no) throws Exception;
  AttachedFile getAttachedFile(int fileNo) throws Exception;
  boolean deleteAttachedFile(int fileNo) throws Exception;

  // 관리자 페이지 이벤트+피드+파티 게시글 조회
  List<Board> listAll() throws Exception;

  // 관리자 페이지 이벤트+피드+파티 오늘 등록된 게시글 개수
  List<Board> newBoardCount() throws Exception;

  // 마이페이지 파티게시글 본인 작성 글 리스트
  List<Map<String, Object>> selectPartyListByNo(Map<String, Object> map);

  // 마이페이지 파티게시글 본인 작성 글 상세보기
  // 관리자페이지 파티게시글 회원 작성 글 상세보기
  Party getMyPartyListDetail(int no) throws Exception;

  // 관리자페이지 파티게시글 비활성화
  boolean partyBlock(int no) throws Exception;

  // 댓글 등록
  public void insertComment(Comment comment) throws Exception;

  // 댓글 리스트 출력
  public List<Comment> getCommentList(Comment comment) throws Exception;

  // 댓글 수정
  boolean updateComment(Comment comment) throws Exception;

  // 댓글 삭제
  boolean deleteComment(int no) throws Exception;

  //메인페이지 게시글 노출
  List<Party> mainList() throws Exception;

  //메인페이지 게시글 노출(String 으로 값받기)
  List<Party> mainList(String meal, String food) throws Exception;

  //마이페이지 파티게시글 연쇄삭제
  boolean allDelete(int no);

  // 검색페이지 결과
  List<Party> searchList(String keywordAll) throws Exception;
}
