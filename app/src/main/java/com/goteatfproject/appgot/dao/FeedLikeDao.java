package com.goteatfproject.appgot.dao;

import com.goteatfproject.appgot.vo.Feed;
import com.goteatfproject.appgot.vo.FeedLike;
import com.goteatfproject.appgot.vo.Member;
import org.apache.ibatis.annotations.Mapper;

@Mapper

public interface FeedLikeDao {

  Integer getLike(Member member, Feed feed);

  void insertLike(Member member, Feed feed);

  void deleteLike(Member member, Feed feed);

  void updateLike(int fno);

  int isLike(FeedLike feedLike);


}