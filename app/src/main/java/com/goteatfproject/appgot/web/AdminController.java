package com.goteatfproject.appgot.web;

import com.goteatfproject.appgot.service.FeedService;
import com.goteatfproject.appgot.service.MemberService;
import com.goteatfproject.appgot.service.PartyService;
import com.goteatfproject.appgot.vo.Criteria;
import com.goteatfproject.appgot.vo.PageMaker;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/admin")
public class AdminController {

  @Autowired
  MemberService memberService;
  PartyService partyService;
  FeedService feedService;

  public AdminController(PartyService partyService, FeedService feedService, MemberService memberService) {

    this.partyService = partyService;
    this.feedService = feedService;
    this.memberService = memberService;

  }

  // 관리자페이지 - 메인
  @GetMapping("/main")
  public String admin(Model model) throws Exception {
    model.addAttribute("parties", partyService.list());
    model.addAttribute("feeds", feedService.list());
    model.addAttribute("members", memberService.list());
    return "admin/adminMain";
  }

  // 관리자페이지 - 파티게시글 관리
  //  @GetMapping("/adminPartyList")
  //  public String AdminPartylist(Model model) throws Exception {
  //    model.addAttribute("parties", partyService.list());
  //    //    model.getAttribute("parties");
  //    //    System.out.println("parties data:" + model.getAttribute("parties")); // data 값 확인용
  //    return "admin/adminPartyList";
  //  }

  @GetMapping("/adminPartyList")
  public ModelAndView adminPartyList(Criteria cri) throws Exception {

    ModelAndView mv = new ModelAndView();

    PageMaker pageMaker = new PageMaker();
    pageMaker.setCri(cri);
    pageMaker.setTotalCount(50);

    List<Map<String, Object>> adminPartyList = partyService.selectPartyList(cri);
    mv.addObject("adminPartyList",adminPartyList);
    mv.addObject("pageMaker", pageMaker);

    System.out.println("adminPartyList = " + adminPartyList);

    mv.setViewName("admin/adminPartyList");
    //    model.addAttribute("parties", partyService.list());
    //    return "party/partyList";
    return mv;
  }

  // 마이페이지-피드게시글 관리
  @GetMapping("/adminFeedList")
  public String adminFeedList(Model model) throws Exception {
    model.addAttribute("adminFeedList", feedService.list());
    //    model.getAttribute("feeds");
    //    System.out.println("data:" + model.getAttribute("feeds"));
    return "admin/adminFeedList";
  }

}
