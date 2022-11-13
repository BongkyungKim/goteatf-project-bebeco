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
  List<Map<String, Object>> selectFeedList(Criteria criteria);

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
}
