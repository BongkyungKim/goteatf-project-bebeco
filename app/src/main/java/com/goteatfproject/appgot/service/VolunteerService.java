package com.goteatfproject.appgot.service;

import com.goteatfproject.appgot.vo.Party;
import com.goteatfproject.appgot.vo.Volunteer;
import java.util.List;

public interface VolunteerService {

  // 파티 참여
  void partyJoin(Volunteer volunteer) throws Exception;

  List<Volunteer> list() throws Exception;

  List<Volunteer> get(int no) throws Exception;

}
