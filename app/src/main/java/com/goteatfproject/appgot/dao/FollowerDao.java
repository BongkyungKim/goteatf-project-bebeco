package com.goteatfproject.appgot.dao;

import com.goteatfproject.appgot.vo.Follower;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

@Mapper

public interface FollowerDao {

  void follow(Follower follower);

  void unfollow(Follower follower);

  int isFollow(Follower follower);

//  List<Follower> userList(int follow);

  List<Follower> selectFollowList(int follow);

  List<Follower> selectFollowingList(int following);


}