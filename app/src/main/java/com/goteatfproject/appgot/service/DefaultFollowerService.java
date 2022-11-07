//package com.goteatfproject.appgot.service;
//
//
//import com.goteatfproject.appgot.dao.FollowerDao;
//import com.goteatfproject.appgot.vo.Follower;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//public class DefaultFollowerService implements FollowerService {
//
//  @Autowired
//  FollowerDao followerDao;
//
//  @Override
//  public void follow(Follower follower) {
//
//  }
//
//  @Override
//  public void unfollow(Follower follower) {
//
//  }
//
//  @Override
//  public int isFollow(Follower follower) {
//    return 0;
//  }
//
//  @Override
//  public List<Follower> selectFollowList(int follow) {
//    List<Follower> selectfollowList = followerDao.selectFollowList(follow);
//    return selectfollowList;
//  }
//
//  @Override
//  public List<Follower> selectFollowingList(int following) {
//    return null;
//  }
//}
