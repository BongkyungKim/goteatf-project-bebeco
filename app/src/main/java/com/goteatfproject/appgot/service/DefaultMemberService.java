package com.goteatfproject.appgot.service;

import com.goteatfproject.appgot.dao.MemberDao;
import com.goteatfproject.appgot.vo.Member;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DefaultMemberService implements MemberService {

  @Autowired
  MemberDao memberDao;

  @Override
  public void add(Member member) throws Exception {
    memberDao.insert(member);
  }

  @Override
  public Member get(int no) throws Exception {
    return memberDao.findByNo(no);
  }

  @Override
  public Member get(String id, String password) throws Exception {
    return memberDao.findByEmailPassword(id, password);
  }

  public List<Member> list() throws Exception {
    return memberDao.findAll();
  }

  // 밑에는 피드의 사용 기능

  public List<Member> randomList() throws Exception {
    return memberDao.userList();
  }

  public Member profileByNo(int no) throws Exception {
    return memberDao.profileByNo(no);
  }

  public Member profileByNick(String nick) throws Exception {
    return memberDao.profileByNick(nick);
  }
}
