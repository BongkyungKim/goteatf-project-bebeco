package com.goteatfproject.appgot.web;

import com.goteatfproject.appgot.service.MemberService;
import com.goteatfproject.appgot.service.PartyService;
import com.goteatfproject.appgot.service.VolunteerService;
import com.goteatfproject.appgot.vo.Member;
import com.goteatfproject.appgot.vo.Party;
import com.goteatfproject.appgot.vo.Volunteer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/volunteer/")
public class VolunteerController {

  MemberService memberService;
  PartyService partyService;
  VolunteerService volunteerService;

  public VolunteerController(MemberService memberService, PartyService partyService,
      VolunteerService volunteerService) {
    System.out.println("VolunteerController() 호출됨!!");
    this.memberService = memberService;
    this.partyService = partyService;
    this.volunteerService = volunteerService;
  }

  // 파티 참여
  @PostMapping("add")
  @ResponseBody
  public String volunteerAdd(Model model,
      HttpSession session, @RequestBody Volunteer volunteer) throws Exception {
    System.out.println("state값 = " + volunteer.getState());
    System.out.println("url값 = " + volunteer.getUrlNo());

    // 파티 번호를 가져와서 해당 번호로 게시물 상세조회 실행 => 리밋, 맥스 가져와서 if문 돌리기
    Party parties = (Party) partyService.get(volunteer.getUrlNo());
    System.out.println("parLi ========> " + parties.getLimit());
    System.out.println("parNa ========> " + parties.getMax());

    int lit = parties.getLimit();
    int max = parties.getMax();

    // 게시물 넘버의 리밋 맥스 가져와서 비교
    // vno=pno, mno, date 는 nn ,, state, etc는 null
    if (lit != max) {
      checkOwner(volunteer.getUrlNo(), session); // 작성자는 신청불가
      volunteer.setWriter((Member) session.getAttribute("loginMember"));
      System.out.println("sessionId = " + volunteer.getWriter());
      // 파티 참여
      volunteerService.partyJoin(volunteer);
      // 파티 참여 카운트
      volunteerService.partyJoinCount(volunteer);
      boolean state = volunteer.getState();
      if (state) {
        return "1"; // ajax 미정
      }
      return "0"; // ajax 성공
    }
      return "3"; // ajax 인원초과
  }

  // 모든 게시물 파티참여자 조회
  @GetMapping("list")
  public String volunteerList(Model model, Volunteer volunteer) throws Exception {

    model.addAttribute("volList", volunteerService.list());
//    System.out.println("model.getAttribute(\"volList\") =  " + model.getAttribute("volList"));
    // 히든으로 no - 당겨오기 - 디테일처럼 출력
    return "volunteer/volunteerList";
  }

//  // 특정 게시물 파티참여자 조회
//  @GetMapping("detail")
//  public Map detail(int no) throws Exception {
//    // <button data-th-if="${session.loginMember != null}" type="button" class="btn btn-outline-success"><a th:href="@{../volunteer/detail(no=${party.no})}">참여자정보</a></button>
//    // th:href="@{../volunteer/detail(no=${party.no})}" 게시물 번호를 가져와서 참여자 정보 조회
//    List<Volunteer> volunteers = volunteerService.get(no);
//
//    if (volunteers == null) {
//      throw new Exception("해당 파티의 참여자가 아닙니다!");
//    }
//
//    Map map = new HashMap<>();
//    map.put("volunteers", volunteers);
//    System.out.println("mapvolunteer = " + map);
//    return map;
//  }

  private void checkOwner(int partyNo, HttpSession session) throws Exception {
    Member loginMember = (Member) session.getAttribute("loginMember");
    // 개인이해메모
    // getWriter().getNo() != loginMember.getNo() // 로그인 멤버no 꺼내서 party에 있는 Member writer 이용해서 일치여부 확인
    // 방향 ----->
    System.out.println("partyNo = " + partyNo);

    if (partyService.get(partyNo).getWriter().getNo() == loginMember.getNo()) {
      throw new Exception("작성자는 신청 불가");
    }
    //volunteer에서 no를 꺼내와 겟라이터.겟넘버 == 로그인멤버.넘버와 일치하면 중복 신청 불가능
  }

  // 특정 게시물 파티참여자 조회
  @PostMapping("detail")
  @ResponseBody
  public Map<String, Object> detail(@RequestBody Volunteer volunteer) throws Exception {
    // <button data-th-if="${session.loginMember != null}" type="button" class="btn btn-outline-success"><a th:href="@{../volunteer/detail(no=${party.no})}">참여자정보</a></button>
    // th:href="@{../volunteer/detail(no=${party.no})}" 게시물 번호를 가져와서 참여자 정보 조회
    List<Volunteer> volunteers = volunteerService.get(volunteer.getUrlNo());

    if (volunteers == null) {
      throw new Exception("해당 파티의 참여자가 아닙니다!");
    }

    Map<String, Object> map = new HashMap<>();
    map.put("volunteers", volunteers);
    System.out.println("mapvolunteer = " + map);
    return map;
  }

  // 파티나가기 + 파티나가기 카운트
  @PostMapping("partyOut")
  @ResponseBody
  public String partyOut(@RequestBody Volunteer volunteer) throws Exception {
    System.out.println("volunteerOUTDATE===> " + volunteer.getState());
    System.out.println("volunteerURLNO====> " + volunteer.getUrlNo());
    System.out.println("volunteer = " + volunteer.getMemberNo()); // 로그인 사용자 번호가 나옴

    // 참여자 멤버 번호 추출
    List<Volunteer> vol = volunteerService.get(volunteer.getUrlNo());
    for (Volunteer value : vol) {
      System.out.println("i = " + value.getMemberNo());

      // 참여자 멤버와 로그인 멤버 비교해서 있으면 파티나가기 성공
      // 참여자 멤버와 로그인 멤버 비교해서 없으면 for문 벗어나서 "3" 참여자가 아님!
      if (value.getMemberNo() != volunteer.getMemberNo()) { // 51 54 != 52
        continue;
      }
      // 파티나가기
      volunteerService.partyOut(volunteer);
      // 파티나가기 카운트
      volunteerService.partyOutCount(volunteer);
      boolean state = volunteer.getState();
      if (state) {
        return "1"; // ajax 미정
      }
      return "0"; // ajax 성공
    }
return "3"; // ajax 참여자가 아님!
  }
}