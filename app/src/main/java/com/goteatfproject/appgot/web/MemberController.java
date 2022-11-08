package com.goteatfproject.appgot.web;

import com.goteatfproject.appgot.service.MemberService;
import com.goteatfproject.appgot.vo.Member;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/member")
public class MemberController {

  MemberService memberService;

  public MemberController(MemberService memberService) {
    System.out.println("MemberController() 호출됨!");
    this.memberService = memberService;
  }

  @GetMapping ("/add")
  public String add() throws Exception {
    return "member/form";
  }

//  @PostMapping("/save")
//  public String save(User user) throws Exception {
//    return "memberInfo";
//  }

  @PostMapping("/add")
  public String add(Member member) throws Exception {
    memberService.add(member);
    return "redirect:list";
  }

  @GetMapping("/list")
  public String list(Model model) throws Exception {
    model.addAttribute("members", memberService.list());
    return "member/list";
  }

//  @GetMapping("/list")
//  public ModelAndView memberList() {
//    ModelAndView mv = new ModelAndView("/member/list");
//
//    List<Map<String, Object>> members = memberService.list();
//    mv.addObject("members", members);
//
//    return mv;
//  }
}
