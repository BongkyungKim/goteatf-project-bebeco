package com.goteatfproject.appgot.service;


import com.goteatfproject.appgot.dao.FollowerDao;
import com.goteatfproject.appgot.vo.Follower;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DefaultFollowerService implements FollowerService {

  @Autowired
  FollowerDao followerDao;

  @Override
  public void follow(Follower follower) {
  followerDao.follow(follower);
  }

  @Override
  public void unfollow(Follower follower) {
  followerDao.unfollow(follower);
  }

  @Override
  public int isFollow(Follower follower) {
    return followerDao.isFollow(follower);
  }


  @Override
  public List<Follower> selectFollowList(int follow) {
    List<Follower> selectfollowList = followerDao.selectFollowList(follow);
    return selectfollowList;
  }

  @Override
  public List<Follower> selectFollowingList(int following) {
    List<Follower> selectfollowingList = followerDao.selectFollowingList(following);
    return selectfollowingList;
  }

  //마이페이지 팔로워 강제 삭제
  @Override
  public boolean allDelete3(int no) {
    return followerDao.allDelete3(no) > 0;
  }


}
