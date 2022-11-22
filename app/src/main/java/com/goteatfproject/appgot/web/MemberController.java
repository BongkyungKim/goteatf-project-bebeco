package com.goteatfproject.appgot.web;

import com.goteatfproject.appgot.service.MemberService;
import com.goteatfproject.appgot.vo.Member;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/member")
public class MemberController {

  MemberService memberService;

  public MemberController(MemberService memberService) {
    System.out.println("MemberController() 호출됨!");
    this.memberService = memberService;
  }

  @GetMapping("/add")
  public String add() throws Exception {
    return "member/memberForm";
  }

  @PostMapping("/add")
  public String add(Member member) throws Exception {
    System.out.println("member = " + member);
    memberService.add(member);
    return "redirect:list";
  }

  @GetMapping("/list")
  public String list(Model model) throws Exception {
    model.addAttribute("members", memberService.list());
    return "member/memberList";
  }

  // 아이디 중복체크
  @PostMapping("/idCheck")
  @ResponseBody
  public int idCheck(@RequestParam("id") String id) {
    int cnt = memberService.idCheck(id);
    return cnt;
  }

  // 닉네임 중복체크
  @PostMapping("/nickCheck")
  @ResponseBody
  public int nickCheck(@RequestParam("nick") String nick) {
    int cntNick = memberService.nickCheck(nick);
    return cntNick;
  }

}
