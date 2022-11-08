package com.goteatfproject.appgot.service;

import com.goteatfproject.appgot.vo.Criteria;
import com.goteatfproject.appgot.vo.Feed;
import com.goteatfproject.appgot.vo.Party;

import java.util.List;
import java.util.Map;

public interface FeedService {

  List<Feed> list() throws Exception;

  void add(Feed feed) throws Exception;


  List<Map<String, Object>> selectFeedList(Criteria criteria);
}
