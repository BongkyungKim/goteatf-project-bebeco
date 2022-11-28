package com.goteatfproject.appgot.service;

import com.goteatfproject.appgot.dao.MemberDao;
import com.goteatfproject.appgot.vo.Member;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
  // 밑에는 피드의 사용 기능 마지막

  // 마이페이지 현재 패스워드 확인
  @Override
  public int getCurrentPasswordCheck(int no, String password) {
    return memberDao.currentPasswordCheck(no, password);
  }

  // 마이페이지 회원정보 수정
  @Override
  public boolean update(Member member) throws Exception {
    return memberDao.update(member) > 0;
  }

  //마이페이지 개인 정보 수정(새 패스워드 없을때) -- 1120 추가
  @Override
  public boolean update2(Member member) throws Exception {
    return memberDao.update2(member) > 0;
  }

  // 마이페이지 회원 삭제
  @Transactional
  @Override
  public boolean delete(int no) throws Exception {
    return memberDao.delete(no) > 0;
  }

  // 마이페이지 회원 정보 프로필 사진 수정
  @Override
  public boolean updateProfile(Member member) throws Exception {
    return memberDao.updateProfile(member) > 0;
  }

  // 마이페이지 회원 정보 자기소개 수정
  @Override
  public boolean updateIntro(Member member) throws Exception {
    return memberDao.updateIntro(member) > 0;
  }

  // 관리자페이지 전체회원 limit 10;
  @Override
  public List<Member> MemberList() throws Exception {
    return memberDao.findByMember();
  }

  // 관리자페이지 신규회원 limit 10;
  @Override
  public List<Member> NewMemberList() throws Exception {
    return memberDao.findByNewMember();
  }

  // 관리자페이지 회원 상세정보
  @Override
  public Member getMemberDetail(int no) throws Exception {
    return memberDao.findByMemberDetail(no);
  }

  // 관리자페이지 회원정보 검색
  @Override
  public List<Member> getSearchMember(String keyword) throws Exception {
    return memberDao.searchMember(keyword);
  }

  @Override
  public boolean memberBlock(int no) throws Exception {
    return memberDao.memberBlock(no) > 0;
  }

  @Override
  public boolean memberActive(int no) throws Exception {
    return memberDao.memberActive(no) > 0;
  }

  //아이디 중복체크 mapper 접근
  @Override
  public int idCheck(String id) throws Exception {
    int cnt = memberDao.idCheck(id);
    System.out.println("cnt: " + cnt);
    return cnt;
  }

  // 닉네임 중복체크 mapper 접근
  @Override
  public int nickCheck(String nick) throws Exception {
    int cntNick = memberDao.nickCheck(nick);
    System.out.println("cntNick: " + cntNick);
    return cntNick;
  }

}
