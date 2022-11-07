//package com.goteatfproject.appgot.dao;
//
//import com.goteatfproject.appgot.vo.Follower;
//import org.apache.ibatis.annotations.Mapper;
//
//import java.util.List;
//
//@Mapper
//
//public interface FollowerDao {
//
//  void follow(Follower follower);
//
//  void unfollow(Follower follower);
//
//  int isFollow(Follower follower);
//
//  List<Follower> selectFollowList(int follow);
//
//  List<Follower> selectFollowingList(int following);
//
//  void deleteAllFollow(int follow);
//
//}