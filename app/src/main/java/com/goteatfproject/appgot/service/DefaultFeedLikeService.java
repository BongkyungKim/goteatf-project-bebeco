package com.goteatfproject.appgot.service;


import com.goteatfproject.appgot.dao.FeedLikeDao;
import com.goteatfproject.appgot.vo.Feed;
import com.goteatfproject.appgot.vo.FeedLike;
import com.goteatfproject.appgot.vo.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DefaultFeedLikeService implements FeedLikeService {

  @Autowired
  FeedLikeDao feedLikeDao;

  @Transactional
  @Override
  public void insertLike(Member member, Feed feed) {
    feedLikeDao.insertLike(member, feed);
    feedLikeDao.updateLike(feed.getNo());
  }

  @Transactional
  @Override
  public void deleteLike(Member member, Feed feed) {
    feedLikeDao.deleteLike(member, feed);
    feedLikeDao.updateLike(feed.getNo());
  }

  @Override
  public Integer getLike(Member member, Feed feed) {
    return feedLikeDao.getLike(member, feed);
  }

  public  int isLike(FeedLike feedLike) {
    return feedLikeDao.isLike(feedLike);
  }
}


