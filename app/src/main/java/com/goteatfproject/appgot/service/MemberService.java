package com.goteatfproject.appgot.service;

import com.goteatfproject.appgot.vo.Criteria;
import com.goteatfproject.appgot.vo.Member;
import java.util.List;
import java.util.Map;

public interface MemberService {

  void add(Member member) throws Exception;

  Member get(int no) throws Exception;

  Member get(String id, String password) throws Exception;

  List<Member> list() throws Exception;


  // 밑에는 피드의 사용 기능

  // 팔로우 박스 랜덤 리스트 출력
  List<Member> randomList() throws Exception;

  // 유저 번호로 유저 프로필 정보 조회
  Member profileByNo(int no) throws Exception;

  Member profileByNick(String nick) throws Exception;
}
