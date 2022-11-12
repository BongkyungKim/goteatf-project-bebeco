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

  @Override
  public void partyJoin(Volunteer volunteer) throws Exception {

    if (volunteerDao.partyJoin(volunteer) == 0) {
      throw new Exception("참여 실패!");
    }
  }

  @Override
  public List<Volunteer> list() throws Exception {
    return volunteerDao.findAll();
  }

  @Override
  public List<Volunteer> get(int no) throws Exception {
    return volunteerDao.findByNo(no);
  }
}
