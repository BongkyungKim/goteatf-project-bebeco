package com.goteatfproject.appgot.dao;

import com.goteatfproject.appgot.vo.Feed;
import com.goteatfproject.appgot.vo.FeedLike;
import com.goteatfproject.appgot.vo.Member;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper

public interface FeedLikeDao {

  Integer getLike(@Param("member") Member member, @Param("feed") Feed feed);

  void insertLike(@Param("member") Member member, @Param("feed") Feed feed);

  void deleteLike(@Param("member") Member member, @Param("feed") Feed feed);

  void updateLike(int fno);

  int isLike(FeedLike feedLike);




}