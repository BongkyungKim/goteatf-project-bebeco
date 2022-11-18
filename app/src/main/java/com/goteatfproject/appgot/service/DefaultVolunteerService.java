package com.goteatfproject.appgot.service;

import com.goteatfproject.appgot.dao.VolunteerDao;
import com.goteatfproject.appgot.vo.Volunteer;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DefaultVolunteerService implements VolunteerService {

  @Autowired
  VolunteerDao volunteerDao;

  // 파티 참여
  @Override
  public void partyJoin(Volunteer volunteer) throws Exception {

    if (volunteerDao.partyJoin(volunteer) == 0) {
      throw new Exception("참여 실패!");
    }
  }

  // 모든 게시물 파티참여자 조회
  @Override
  public List<Volunteer> list() throws Exception {
    return volunteerDao.findAll();
  }

  // 특정 게시물 파티참여자 조회
  @Override
  public List<Volunteer> get(int no) throws Exception {
    return volunteerDao.findByNo(no);
  }

  // 테스트
  @Override
  public Volunteer get2(int no) throws Exception {
    return volunteerDao.findByNo2(no);
  }

  // 파티 참여자 카운트
  @Override
  public boolean partyJoinCount(Volunteer volunteer) throws Exception {
    if (volunteerDao.partyJoinCount(volunteer) == 0) {
      return false;
    }
    return true;
  }
}
