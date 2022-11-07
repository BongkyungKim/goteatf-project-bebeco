package com.goteatfproject.appgot.service;

import com.goteatfproject.appgot.vo.Member;
import java.util.List;

public interface MemberService {

  void add(Member member) throws Exception;

  Member get(int no) throws Exception;

  Member get(String id, String password) throws Exception;

  List<Member> list() throws Exception;

}
