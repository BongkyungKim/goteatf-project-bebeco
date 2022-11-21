package com.goteatfproject.appgot.service;

import com.goteatfproject.appgot.vo.Feed;
import com.goteatfproject.appgot.vo.FeedLike;
import com.goteatfproject.appgot.vo.Member;

public interface FeedLikeService {

  Integer getLike(Member member, Feed feed) throws Exception;
  void insertLike(Member member, Feed feed) throws Exception;

  void deleteLike(Member member, Feed feed) throws Exception;

  int isLike(FeedLike feedLike) throws Exception;

}
