package com.goteatfproject.appgot.service;

import com.goteatfproject.appgot.vo.Follower;
import java.util.List;

public interface FollowerService {

  void follow(Follower follower) throws Exception;

  void unfollow(Follower follower) throws Exception;

  int isFollow(Follower follower) throws Exception;

  List<Follower> selectFollowList(int follow) throws Exception;

  List<Follower> selectFollowingList(int following) throws Exception;

}
