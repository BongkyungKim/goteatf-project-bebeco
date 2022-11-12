package com.goteatfproject.appgot.dao;

import com.goteatfproject.appgot.vo.Party;
import com.goteatfproject.appgot.vo.Volunteer;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface VolunteerDao {

  // 파티 참여
  int partyJoin(Volunteer volunteer);

  List<Volunteer> findAll();

  List<Volunteer> findByNo(int no);
}
