package com.goteatfproject.appgot.service;

import com.goteatfproject.appgot.vo.Feed;
import com.goteatfproject.appgot.vo.Party;

import java.util.List;

public interface FeedService {

  List<Feed> list() throws Exception;

  void add(Feed feed) throws Exception;

}
