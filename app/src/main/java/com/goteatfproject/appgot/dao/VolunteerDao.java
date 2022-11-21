package com.goteatfproject.appgot.dao;

import com.goteatfproject.appgot.vo.Party;
import com.goteatfproject.appgot.vo.Volunteer;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface VolunteerDao {

  // 파티 참여
  int partyJoin(Volunteer volunteer);

  // 모든 게시물 파티참여자 조회
  List<Volunteer> findAll();

  // 특정 게시물 파티참여자 조회
  List<Volunteer> findByNo(int no);

  // 테스트
   Volunteer findByNo2(int no);

   // 파티 참여자 카운트
  int partyJoinCount(Volunteer volunteer);

  // 파티나가기
  int partyOut(Volunteer volunteer);

  // 파티나가기 카운트
  int partyOutCount(Volunteer volunteer);
}
