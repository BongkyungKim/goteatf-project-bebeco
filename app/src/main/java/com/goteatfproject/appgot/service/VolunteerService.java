package com.goteatfproject.appgot.service;

import com.goteatfproject.appgot.vo.Party;
import com.goteatfproject.appgot.vo.Volunteer;
import java.util.List;

public interface VolunteerService {

  // 파티 참여
  void partyJoin(Volunteer volunteer) throws Exception;

  // 모든 게시물 파티참여자 조회
  List<Volunteer> list() throws Exception;

  // 특정 게시물 파티참여자 조회
  List<Volunteer> get(int no) throws Exception;

  // 테스트
  Volunteer get2(int no) throws Exception;

  // 파티 참여자 카운트
  boolean partyJoinCount(Volunteer volunteer) throws Exception;



}
