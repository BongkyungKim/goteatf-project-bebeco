package com.goteatfproject.appgot.service;

import com.goteatfproject.appgot.vo.Criteria;
import com.goteatfproject.appgot.vo.Feed;
import com.goteatfproject.appgot.vo.FeedAttachedFile;
import com.goteatfproject.appgot.vo.Party;

import java.util.List;
import java.util.Map;

public interface FeedService {

  List<Feed> list() throws Exception;
  void add(Feed feed) throws Exception;

  // 페이징 관리자페이지 피드게시글 관리
  List<Map<String, Object>> selectFeedList(Criteria cri);

  // 추가
  List<Feed> simpleProfile(int no) throws Exception;
  List<Feed> followFindAll(int no) throws Exception;
  List<Feed> selectListByNick(String nick) throws Exception;
  List<Feed> randomlist() throws Exception;
  Feed get(int no) throws Exception;
  boolean update(Feed feed) throws Exception;
  boolean delete(int no) throws Exception;
  FeedAttachedFile getFeedAttachedFile(int fileNo) throws Exception;
  boolean deleteFeedAttachedFile(int fileNo) throws Exception;

  // 마이페이지 피드게시글 본인 작성 글 리스트
  List<Map<String, Object>> selectFeedListByNo(Map<String, Object> map);

  // 마이페이지 피드게시글 본인 작성 글 상세보기
  // 관리자페이지 피드게시글 회원 작성 글 상세보기
  Feed getMyFeedListDetail(int no) throws Exception;

  // 관리자페이지 피드게시글 비활성화
  boolean feedBlock(int no);

  //메인페이지 노출
  List<Feed> mainList() throws Exception;

  // 마이페이지 피드게시글 강제삭제 — 1120 추가
  boolean allDelete2(int no);

  // 검색페이지 결과
  List<Party> searchList(String keywordAll) throws Exception;

}
