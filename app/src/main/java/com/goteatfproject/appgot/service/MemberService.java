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

  //페이징
//List<Map>
}
