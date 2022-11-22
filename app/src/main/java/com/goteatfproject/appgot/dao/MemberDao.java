package com.goteatfproject.appgot.dao;

import com.goteatfproject.appgot.vo.Member;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface MemberDao {

  int insert(Member member);

  Member findByNo(int no);

  List<Member> findAll();

  Member findByEmailPassword(@Param("id") String id, @Param("password") String password);

  // 밑에부터는 피드에 필요한 기능들
  // 로그인하지 않았을 시 랜덤으로 출력되는 팔로우박스
  List<Member> userList();

  // 아이디로 회원 정보 조회 기능 (후에 필요없으면 뺄수도 )
  Member profileByNick(String nick);

  // 유저 번호로 유저 프로필 조회 기능
  Member profileByNo(int no);
  // 밑에부터는 피드에 필요한 기능들 마지막

  // 마이페이지 현재 패스워드 확인
  int currentPasswordCheck(@Param("no") int no, @Param("password") String password);

  // 마이페이지 회원정보 수정
  int update(Member member);

  // 마이페이지 개인 정보 수정(새 패스워드 없을때) -- 추가: 1120
  int update2(Member member);

  // 마이페이지 회원 삭제
  int delete(int no);

  // 마이페이지 회원 정보 프로필 사진 수정
  int updateProfile(Member member);

  // 마이페이지 회원 정보 자기소개 수정
  int updateIntro(Member member);

  // 관리자페이지 전체회원 limit 10;
  List<Member> findByMember();

  // 관리자페이지 신규회원 limit 10;
  List<Member> findByNewMember();

  // 관리자페이지 회원 관리 게시판 상세정보
  Member findByMemberDetail(int no);

  // 관리자페이지 회원리스트 정보검색
  List<Member> searchMember(String keyword);

  // 관리자페이지 비활성화
  int memberBlock(int no);

  // 관리자페이지 활성화
  int memberActive(int no);

  // 회원가입 아이디 중복체크
  public int idCheck(String id);

  // 회원가입 닉네임 중복체크
  public int nickCheck(String nick);


}
